/**
 * @Date 2014年2月11日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.http;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author syd
 */
public class HttpClientDemo43 {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpClientDemo43.class);
	
	private static final String HTTPGETTESTURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=61THfp0Oup1nFz2sKO0qjHjM&secret=QOruGNcmtXvL9H073bnb1eXC8Kj9nlN0";
	private static final String HTTPPOSTTESTURL = "http://www.iteye.com/search";
	
	private static X509TrustManager tm = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};
	/**
	 * 创建HttpClient对象
	 * @return
	 */
	public static HttpClient buildHttpClient() {
		try {
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(sslcontext);
			ClientConnectionManager ccm = new DefaultHttpClient().getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
			HttpParams params = new BasicHttpParams();
			params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 8000);
			params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 8000);
			HttpClient httpclient = new DefaultHttpClient(ccm, params);
			httpclient.getParams().setParameter(HTTP.USER_AGENT,
					"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
			return httpclient;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

	}
	
	@Test
	public void test03(){
		HttpClient httpClient = buildHttpClient();
		HttpGet httpGet = new HttpGet(HTTPGETTESTURL);
		logger.info("httpGet.getRequestLine():{}",httpGet.getRequestLine());
		
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);//执行get请求
			HttpEntity httpEntity = httpResponse.getEntity();//获取响应消息实体
			logger.info("响应状态:{}",httpResponse.getStatusLine());
			if(httpEntity != null){
				logger.info("contentEncoding:{}",httpEntity.getContentEncoding());
				logger.info("response content:{}",EntityUtils.toString(httpEntity));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	/**
	 * HttpClient 4.3 简单入门---HttpGet
	 */
	@Test
	public void test01(){
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();//创建httpClientBuilder
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();//创建httpClient
		HttpGet httpGet = new HttpGet(HTTPGETTESTURL);
		logger.info("httpGet.getRequestLine():{}",httpGet.getRequestLine());
		
		try {
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);//执行get请求
			HttpEntity httpEntity = httpResponse.getEntity();//获取响应消息实体
			logger.info("响应状态:{}",httpResponse.getStatusLine());
			if(httpEntity != null){
				logger.info("contentEncoding:{}",httpEntity.getContentEncoding());
				logger.info("response content:{}",EntityUtils.toString(httpEntity));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				closeableHttpClient.close();//关闭流并释放资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * HttpClient 4.3简单入门---HttpPost
	 */
	@Test
	public void test02(){
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPost httpPost = new HttpPost(HTTPPOSTTESTURL);
//		httpPost.setConfig();
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();//创建参数列表
		formParams.add(new BasicNameValuePair("type", "all"));
		formParams.add(new BasicNameValuePair("query","httpClient"));
		formParams.add(new BasicNameValuePair("sort",""));
		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(entity);
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if(httpEntity != null){
				logger.info("response:{}",EntityUtils.toString(httpEntity, "UTF-8"));
			}
			closeableHttpClient.close();//释放资源
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
