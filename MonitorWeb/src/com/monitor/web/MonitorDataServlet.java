package com.monitor.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.monitor.service.MonitorService;

public class MonitorDataServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 设置服务器的编码为utf-8
		req.setCharacterEncoding("utf-8");

		// 设置浏览器的编码为utf-8
		resp.setContentType("text/html;charset=utf-8");

		MonitorService monitorService = new MonitorService();
		
		int car_id = Integer.parseInt(req.getParameter("car_id"));
	
		String retXml = null;
		
		retXml = monitorService.getLastMonitorData(car_id);
		resp.getOutputStream().write(retXml.getBytes("UTF-8"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
