package com.monitor.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {


	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 每一次请求到来的时候都会调用这个方法，对请求参数进行处理
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		//GET方式的请求者将请求的HttpServletRequest对象转换给自定义的EncodingHttpServletRequest对象
		if("GET".equals(req.getMethod())){			
			EncodingHttpServletRequest wrapper = new EncodingHttpServletRequest(req);		
			chain.doFilter(wrapper, response);
		}
		//POST请求方式，则设置服务器的解码格式为utf-8
		else{
			req.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
