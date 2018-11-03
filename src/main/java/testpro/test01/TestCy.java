package testpro.test01;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Encoder;

public class TestCy {

    public static String url = "http://download.lestorage.com:1213";
    public static String testUr = "test01.cystorage.cycore.cn";
	
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException {
    	prepareUpload();
	}
    
	public static void prepareUpload() throws InvalidKeyException, NoSuchAlgorithmException {
		
		String path = testUr + "/v2/files/prepare";
		
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		String appKey = "test01";
		String token = "";
		Long expires = System.currentTimeMillis() + 1000 * 60;
		String filePath = "";
		String ext = "jpg";

		String encodedPutPolicy = genBase64("http:// "+ path);
		token = getSignature(encodedPutPolicy.getBytes(), "<AppSecret>".getBytes());
		
//		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("appKey", appKey);
//		paramMap.put("token", token);
//		paramMap.put("expires", expires);
//		paramMap.put("ext", ext);
//		
//		String queryString = getQueryString(paramMap);
		
		
		params.add(new BasicNameValuePair("appKey" , appKey));
		params.add(new BasicNameValuePair("token" , token));
		params.add(new BasicNameValuePair("expires" , expires + ""));
		params.add(new BasicNameValuePair("ext" , ext));
		
		HttpPost post = new HttpPost(path);
		
		UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
		urlEntity.setContentType("application/x-www-form-urlencoded");
		
		post.setEntity(urlEntity);
		
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response;
		try {
			response = client.execute(post);
			StatusLine statusLine = response.getStatusLine();
			System.out.println(statusLine);
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			String result = EntityUtils.toString(entity);
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static String getQueryString(Map<String, Object> paramMap) {
		String paramStr = "";
		Set<String> keySet = paramMap.keySet();
		List<String> keyList = new ArrayList<String>();
		for (String key : keySet) {
			keyList.add(key);
		}
		Collections.sort(keyList);
		for (int i = 0; i < keyList.size() ; i++) {
			paramStr = keyList.get(i) + "=" + paramMap.get(keyList.get(i));
			if(i == keyList.size() -1) {
				break;
			}
			paramStr += "&";
		}
		return paramStr;
	}
	
	private static String genBase64(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		String encode = encoder.encode(str.getBytes());
		return encode;
	}
	
	
	private static String getSignature(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
		String HMAC_SHA1 = "HmacSHA1";
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data);
		return encode(rawHmac);
	}  
	
	/** 
     * 获取MD5 结果字符串 
     *  
     * @param source 
     * @return 
     */  
    private static String encode(byte[] source) {  
        String s = null;  
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(source);  
            byte tmp[] = md.digest();   
            char str[] = new char[16 * 2];   
            int k = 0;   
            for (int i = 0; i < 16; i++) {   
                byte byte0 = tmp[i];   
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];   
                str[k++] = hexDigits[byte0 & 0xf];   
            }  
            s = new String(str);   
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return s;  
    }  
}
