package com.monitor.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.service.CarService;

public class CarServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 设置服务器的编码为utf-8
		req.setCharacterEncoding("utf-8");

		// 设置浏览器的编码为utf-8
		resp.setContentType("text/html;charset=utf-8");
		
		CarService carService = new CarService();
		
		//查找车辆信息
		String carsXml = carService.getAllCarInfo();
		
		resp.getOutputStream().write(carsXml.getBytes("UTF-8"));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		doGet(req,resp);
	}

}
