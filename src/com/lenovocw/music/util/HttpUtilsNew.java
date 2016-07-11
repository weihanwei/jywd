package com.lenovocw.music.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

public class HttpUtilsNew {
 
	public static String getParamString(TreeMap<String, String> params) {

		String paramStr = "";
		Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter
					.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			paramStr += paramStr = "&" + key + "=" + val;
		}
		return paramStr;
	}

 
	public static String httpGet(String url, TreeMap<String, String> params) {
		HttpClient client = new DefaultHttpClient();
		try {
			String paramStr = getParamString(params);
			if (!paramStr.equals("")) {
				paramStr = paramStr.replaceFirst("&", "?");
				url += paramStr;
			}
			HttpGet get = new HttpGet(url);
			System.out.println(url);
			HttpResponse response = client.execute(get);
			return IOUtils.toString(response.getEntity().getContent(), "utf-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String httpPost(String url, TreeMap<String, String> params) {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000); 
		HttpPost post = new HttpPost(url);
		try {
			String paramStr = getParamString(params);
			if (!paramStr.equals("")) {
				paramStr = paramStr.replaceFirst("&", "");
			}
			post.setEntity(new StringEntity(paramStr,"application/x-www-form-urlencoded", "utf-8"));
			
			HttpResponse response = client.execute(post);
			return IOUtils.toString(response.getEntity().getContent(), "utf-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}