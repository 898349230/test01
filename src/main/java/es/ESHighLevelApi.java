package es;

import entity.People;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.JSONUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESHighLevelApi {

    private RestHighLevelClient client;
    private RestClientBuilder builder;
    @Before
    public void initConnection(){
        builder = RestClient.builder(new HttpHost("aliyun.com", 9200, "http"));
        client = new RestHighLevelClient(builder);
    }

    /**
     * 创建索引
     */
    @Test
    public void IndexTest(){
//                                                  index,        type     documentId
        IndexRequest request = new IndexRequest("sxb02", "doc", "1");
//        使用 jsonString
//        List<String> hobby = new ArrayList<>();
//        hobby.add("足球");
//        hobby.add("篮球");
//        hobby.add("乒乓球");
//        People people = new People("张三", "male", 18, "河北", hobby);
//
//        String json = JSONUtils.obj2json(people);
//        request.source(json, XContentType.JSON);

//        使用map
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("name1", "lisi");
        jsonMap.put("age", "15");
        jsonMap.put("address", "北京");
        request.source(jsonMap, XContentType.JSON);

        try {
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            String status = response.status().toString();
            System.out.println(" status = " + status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeConnection(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
