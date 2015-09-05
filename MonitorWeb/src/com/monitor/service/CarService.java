package com.monitor.service;

import java.util.List;

import com.monitor.dao.CarDao;
import com.monitor.domain.CarInfo;
import com.monitor.factory.DaoFactory;
import com.monitor.util.XMlParserUtils;

public class CarService {
	private CarDao dao = DaoFactory.getFactory().getCarDao();
	
	/**
	 * 获取所有的车辆信息并将车辆信息转为xml字符串
	 * @return 所有车辆信息的xml格式的字符串
	 */
	public String getAllCarInfo(){		
		//获取所有的车辆信息
		List<CarInfo> carList = dao.getAllCarInfo();			
		//返回车辆信息的xml字符串
		return XMlParserUtils.XMLCarParserXtoS(carList);
	}
}
