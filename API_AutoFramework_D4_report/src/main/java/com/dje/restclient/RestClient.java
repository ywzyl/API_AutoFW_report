package com.dje.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	//1. Get ���󷽷�
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		//����һ���ɹرյ�HttpClient����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//����һ��HttpGet���������
		HttpGet httpGet=new HttpGet(url);
		//ִ������,�൱��postman�ϵ�����Ͱ�ť��Ȼ��ֵ��HttpResponse�������
		CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
		return httpResponse;
	}
	//2. Get ���󷽷���������ͷ��Ϣ��
	public CloseableHttpResponse get(String url,HashMap<String, String> headermap) 
			throws ClientProtocolException, IOException {
		//����һ���ɹرյ�HttpClient����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		 //����һ��HttpGet���������
		HttpGet httpGet=new HttpGet(url);
		//��������ͷ��httpget����
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		//ִ������,�൱��postman�ϵ�����Ͱ�ť��Ȼ��ֵ��HttpResponse�������
		CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
		return httpResponse;
	}	 
	//3. POST����
	public CloseableHttpResponse post(String url,String entityString,HashMap<String, String> headermap) 
			throws ClientProtocolException, IOException {
		//����һ���ɹرյ�HttpClient����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		headermap.put("Content-Type", "application/json");
		//����һ��HttpPost���������
		HttpPost httpPost=new HttpPost(url);
		//����payload
		httpPost.setEntity(new StringEntity(entityString));
		//��������ͷ��httppost����
		for (Map.Entry<String, String>  entry : headermap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		//����post����
		CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
		return httpResponse;
	}
	//4. Put����
	public CloseableHttpResponse put(String url,String entity,HashMap<String, String> headerMap) 
			throws ClientProtocolException, IOException  {
		//����һ���ɹرյ�HttpClient����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		headerMap.put("Content-Type", "application/json");
		//����һ��HttpPut���������
		HttpPut httpPut=new HttpPut(url);
		//����payload
		httpPut.setEntity(new StringEntity(entity));
		//��������ͷ��httpput����
		for(Map.Entry<String, String> entry:headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}
		//����put����
		CloseableHttpResponse httpResponse=httpClient.execute(httpPut);
		return httpResponse;
	}
	//5. Delete����
	public CloseableHttpResponse delete(String url) throws ClientProtocolException, IOException {	
		//����һ���ɹرյ�HttpClient����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		//����һ��HttpDelete���������
		HttpDelete httpDelete=new HttpDelete(url);
		//����delete����
		CloseableHttpResponse httpResponse=httpClient.execute(httpDelete);
		return httpResponse;
	}
}
