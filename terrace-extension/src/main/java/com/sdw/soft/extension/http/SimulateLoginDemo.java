/**
 * @Date 2014年2月11日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author syd
 */
public class SimulateLoginDemo {
	
	private static final Logger logger = LoggerFactory.getLogger(SimulateLoginDemo.class);
	// the configuration items
	private static String username = "13684513972";
	private static String password = "sydhappy2352315";
	private static String redirectUrl = "http://blog.renren.com/blog/304317577/449470467";
	
	private static String renrenLoginUrl = "http://www.renren.com/SysHome.do";
	//The httpClient is used in one session
	private HttpResponse httpResponse;
	private HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
	private CloseableHttpClient closeableHttpClient;
	
	private boolean simulateLogin(){
		HttpPost httpPost = new HttpPost(renrenLoginUrl);
		//All params post to the web site
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("origURL",redirectUrl));
		params.add(new BasicNameValuePair("domain","renren.com"));
		params.add(new BasicNameValuePair("isplogin","true"));
		params.add(new BasicNameValuePair("formName",""));
		params.add(new BasicNameValuePair("method",""));
		params.add(new BasicNameValuePair("submit","登录"));
		params.add(new BasicNameValuePair("email",username));
		params.add(new BasicNameValuePair("password",password));
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			closeableHttpClient = httpClientBuilder.build();
			httpResponse = closeableHttpClient.execute(httpPost);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return true;
	}
	
	private String getRedirectLocation(){
		Header locationHeader = httpResponse.getFirstHeader("Location");
		if(locationHeader == null){
			return null;
		}
		return locationHeader.getValue();
	}
	
	private String getText(String redirectLocation){
		HttpGet httpGet = new HttpGet(redirectLocation);
		//Create a response handler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try {
			responseBody = closeableHttpClient.execute(httpGet, responseHandler);
			
		} catch (Exception e) {
			e.printStackTrace();
			responseBody = null;
		}finally{
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseBody;
	}
	
	public void printText(){
		if(simulateLogin()){
			String redirectLocation = getRedirectLocation();
			if(redirectLocation != null){
				logger.info("getText:{}",getText(redirectLocation));
			}
		}
	}
	
	@Test
	public void test01(){
		SimulateLoginDemo simulateLoginDemo = new SimulateLoginDemo();
		simulateLoginDemo.printText();
	}
}
