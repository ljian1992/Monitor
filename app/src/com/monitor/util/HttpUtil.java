package com.monitor.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class HttpUtil {
	
	
	 /* 通过post的方式发送请求
	 * @param path：请求地址
	 * @param params：请求数据
	 * @param ecoding：编码格式
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendHttpClientPostRequest(String path,
			Map<String, String> params, String ecoding) throws ClientProtocolException, IOException {
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>(); //存放请求参数
		
		//将请求数据放入paris集合当中
		if (null != params && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));		
			}	
		}
		
		//构造实体数据
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, ecoding); 
		
		//构造HttpPost对象
		HttpPost httpPost = new HttpPost(path);
		
		//设置数据
		httpPost.setEntity(entity);
		
		//相当于浏览器
		DefaultHttpClient client = new DefaultHttpClient(); 
		
		//访问服务器
		HttpResponse response = client.execute(httpPost);
		
		//判断访问服务器是否成功
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			
			//获取服务器中返回的数据
			String responseStr = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	
			return responseStr;
		}
		
		return null;
		
	}
	
}



