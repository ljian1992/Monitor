package com.monitor.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.domain.UserInfo;
import com.monitor.service.UserService;



public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public final static  int LOGIN_NAME_OR_PSW_ERROR = 1;
	public final static  int LOGIN_SUCCESS = 2;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		UserService service = new UserService();
		
		//设置服务器的编码为utf-8
		req.setCharacterEncoding("UTF-8");
		
		//设置浏览器的编码为utf-8
		resp.setContentType("text/html;charset=UTF-8");
				
		//获取客户端提交的用户名和密码
		String username = req.getParameter("username"); //接收用户参数
		String password = req.getParameter("password");
			
		//调用Service中的方法检查用户名密码
		UserInfo user = service.isUser(username, password);
		
		if(null != user){//有则返回3
			resp.getOutputStream().write(String.valueOf(LOGIN_SUCCESS).getBytes("UTF-8"));
		}
		else{//无则返回2
			resp.getOutputStream().write(String.valueOf(LOGIN_NAME_OR_PSW_ERROR).getBytes("UTF-8"));
		}
				
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		doGet(req, resp);
	}
}
