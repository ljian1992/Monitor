package com.monitor.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingHttpServletRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;
	public EncodingHttpServletRequest(HttpServletRequest request) {
		super(request);	
		this.request = request;
	}
	
	/**
	 * 重载getParameter,返回值就是已经将字符串转码为utf-8的编码
	 */
	@Override
	public String getParameter(String name) {
		
		String value = request.getParameter(name);
		if(null != value){
			try {
				value = new String(value.getBytes("ISO08859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {				
				e.printStackTrace();
				throw new RuntimeException("转换失败");
			}
		}
		
		return value;
	}
	
}
