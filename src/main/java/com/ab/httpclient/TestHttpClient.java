package com.ab.httpclient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.StatusLine;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {

	@Test
	public void testHttpGet() {
		//HttpGet httpGet = new HttpGet("https://www.hao123.com/");
		HttpGet httpGet = new HttpGet("https://openmobile.qq.com/user/get_simple_userinfo");
		httpGet.setHeader("header1", "header1Value");
		httpGet.setHeader("header2", "header2Value");
		HttpClient cilent = new HttpClient();
		String method = httpGet.getMethod();
		URI uri = httpGet.getURI();
		RequestLine line = httpGet.getRequestLine();
		ProtocolVersion protocolVersion = line.getProtocolVersion();
		Header[] headers = httpGet.getAllHeaders();
		for (Header header : headers) {
			System.out.println("header : " + header.getName() + "\t" + header.getValue());
		}
		System.out.println("uri = " + uri + "\tmethod = " + method + "\tprotocolVersion = " + protocolVersion);
		
	}

	@Test
	public void testResponse() throws HttpException {
		HttpResponse httpResponse = new BasicHttpResponse((org.apache.http.StatusLine) new StatusLine(""));
	}

	@Test
	public void testStringEntity() throws IOException {
		StringEntity entity = new StringEntity("xmlHead", ContentType.create("text/plain", "UTF-8"));
		System.out.println("Content = " + entity.getContent());
		System.out.println("ContentLength = " + entity.getContentLength());
		System.out.println("ContentEncoding = " + entity.getContentEncoding());
		System.out.println("ContentType = " + entity.getContentType());
		System.out.println("toString = " + EntityUtils.toString(entity));
	}

	@Test
	public void testCloseableHttp() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://www.hao123.com");
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			if (null != entity) {
				// 将httpEntity的内容缓冲到内存中
				entity = new BufferedHttpEntity(entity);

				InputStream content = entity.getContent();
				int oneByte = content.read();
				int twoByte = content.read();
				System.out.println(oneByte + "\t" + twoByte);
				System.out.println();
			} else {
				System.out.println("entity  is  null");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接释放资源
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 模拟html表单
	 * https://graph.qq.com/user/get_simple_userinfo? 
	 *	access_token=1234ABD1234ABD&
	 *	oauth_consumer_key=12345&
	 *	openid=B08D412EEC4000FFC37CAABBDC1234CC 
	 */
	@Test
	public void testHTMLForm() {

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
//		formParams.add(new BasicNameValuePair("username", "张三"));
//		formParams.add(new BasicNameValuePair("password", "123456"));
		formParams.add(new BasicNameValuePair("access_token", "1234ABD1234ABD"));
		formParams.add(new BasicNameValuePair("oauth_consumer_key", "12345"));
		formParams.add(new BasicNameValuePair("openid", "B08D412EEC4000FFC37CAABBDC1234CC"));
		UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
		//urlEntity.setContentType("multipart/form-data");
		System.out.println("======="+urlEntity.getContentType());
		try {
			System.out.println("urlEntity to  String  :  " + EntityUtils.toString(urlEntity, Consts.UTF_8));
		} catch (ParseException | IOException e1) {
			e1.printStackTrace();
		}
//		HttpPost httpPost = new HttpPost("http://localhost:8080/ec_serv_intf/forec");
		HttpPost httpPost = new HttpPost("https://graph.qq.com/user/get_simple_userinfo");
		httpPost.setEntity(urlEntity);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			System.out.println(response.getStatusLine());
			System.out.println("response entity  :　" + EntityUtils.toString(entity));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void testUpload(){
		HttpPost post = new HttpPost("http://localhost:8080/ec_serv_intf/forec");
//		FileEntity file = new FileEntity(new File("C:/Users/Administrator/Desktop/新建文本文档.txt"));
//		StringEntity string = new StringEntity("bodybody",ContentType.create("application/xml",Consts.UTF_8));

		// 上传文件
		FileBody file = new FileBody(new File("C:/Users/Administrator/Desktop/新建文本文档.txt"));
		StringBody string = new StringBody("bodybody",ContentType.create("application/xml",Consts.UTF_8));
		
		HttpEntity entity = MultipartEntityBuilder.create().addPart("file", file).addPart("string", string).build();
		post.setEntity(entity);
		
		// 4.3版本  设置超时时间
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(360000)
				.setConnectTimeout(360000)   // 设置请求和传输超时 为 360000ms
				.build();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse execute = null;
		try {
			execute = httpClient.execute(post);
			org.apache.http.StatusLine statusLine = execute.getStatusLine();
			System.out.println("statusLine : " + statusLine);
			HttpEntity resEntity = execute.getEntity();
			if(resEntity!=null){
				String resStr = EntityUtils.toString(resEntity, Consts.UTF_8);
				System.out.println("response String : "+resStr);
			}
			EntityUtils.consume(resEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//
//	/**
//	 * http上下文
//	 */
	@Test
	public void testClientContext() {
		HttpClientContext cilentContext = new HttpClientContext();

		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).build();
		HttpPost post = new HttpPost("https://www.hao123.com");
		CloseableHttpResponse response = null;
		post.setConfig(config);
		try {
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String entityStr = EntityUtils.toString(entity);
			System.out.println(entityStr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 自定义异常处理机制
	 */
	@Test
	public void testHttpRequestRetryHander() {
		//自定义异常
		HttpRequestRetryHandler myRetryHander = new HttpRequestRetryHandler() {
			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {

				if (executionCount >= 5) {
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					// Timeout
					return false;
				}
				if (exception instanceof UnknownHostException) {
					// Unknown host
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {
					// Connection refused
					return false;
				}
				if (exception instanceof SSLException) {
					// SSL handshake exception
					return false;
				}
				
				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if(idempotent){
					// Retry if the request is considered idempotent
					return true;
				}
				return false;
			}
		};
		
		CloseableHttpClient httpClient = HttpClients.custom()
											.setRetryHandler(myRetryHander)
											.build();
	}

	/*
	 * 测试重定向
	 */
	@Test
	public void testRedirection(){
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpClientContext context = HttpClientContext.create();
		HttpPost post = new HttpPost("https://www.hao123.com");
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(post,context);
			HttpHost host = context.getTargetHost();
			System.out.println("host  toString : "+host.toHostString());
			List<URI> uriList = context.getRedirectLocations();
			URI location = URIUtils.resolve(post.getURI(), host, uriList);
			System.out.println("Final HTTP location : "+location.toASCIIString());
			System.out.println("Final HTTP location : "+location.toString());
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}finally{
			if(response!=null){
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
