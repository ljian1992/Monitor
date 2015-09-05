package com.monitor.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.service.LocationService;


public class LocationInfoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public final static int GET_LOCATION_INFO_ERROR = 8;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 设置服务器的编码为utf-8
		req.setCharacterEncoding("utf-8");

		// 设置浏览器的编码为utf-8
		resp.setContentType("text/html;charset=utf-8");

		int car_id = Integer.parseInt(req.getParameter("car_id"));
		
		
		LocationService locationService = new LocationService();
		
		String retXml = locationService.getLastLocationInfo(car_id);
		//String retXml = locationService.getLastLocationInfo(1);
		if(null != retXml){
			resp.getOutputStream().write(retXml.getBytes("UTF-8"));
		}else{			
			resp.getOutputStream().write((GET_LOCATION_INFO_ERROR+"").getBytes("UTF-8"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
	
}
