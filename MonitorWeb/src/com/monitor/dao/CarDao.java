package com.monitor.dao;

import java.util.List;

import com.monitor.domain.CarInfo;


public interface CarDao {
	
	/**
	 * 返回数据库中所有的车辆信息
	 * @return 车辆信息的List容器
	 */
	public List<CarInfo> getAllCarInfo();
}
