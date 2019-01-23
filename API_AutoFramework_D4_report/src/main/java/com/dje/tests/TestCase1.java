package com.dje.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.dje.base.TestBase;
import com.dje.parameters.PostParameters;
import com.dje.restclient.RestClient;
import com.dje.util.TestUtil;
import static com.dje.util.TestUtil.dtt;

public class TestCase1 extends TestBase{
	TestBase testBase;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    String host;
    String testCaseExcel;
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        testBase = new TestBase();
        restClient = new RestClient();
        postHeader.put("Content-Type","application/json");
        //���������ļ����ӿ�endpoint
        host = prop.getProperty("HOST");
        //���������ļ���post�ӿڲ���
        testCaseExcel=prop.getProperty("postdata");
    }
 
    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        return dtt(testCaseExcel,0);
 
    }
 
    @Test(dataProvider = "postData")
    public void login(String loginUrl,String username, String passWord) throws Exception {
        //ʹ�ù��캯����������û��������ʼ���ɵ�¼�������
        PostParameters loginParameters = new PostParameters(username,passWord);
        //����¼����������л���json����
        String userJsonString = JSON.toJSONString(loginParameters);
        //���͵�¼����
        closeableHttpResponse = restClient.post(host+loginUrl,userJsonString,postHeader);
        //�ӷ��ؽ���л�ȡ״̬��
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);
 
    }
    @BeforeClass
    public void endTest(){
        System.out.print("���Խ���");
    }
}
