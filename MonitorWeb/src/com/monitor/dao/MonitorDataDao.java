package com.monitor.dao;

import java.util.List;

import com.monitor.domain.MonitorInfo;

public interface MonitorDataDao {
	
	/**
	 * 根据car_id获取该车所有的监控信息
	 * @return
	 */
	public List<MonitorInfo> getAllMonitorDataByCar(Integer car_id);
	
	/**
	 * 根据car_id获取该车最新的监控信息
	 * @param car_id
	 * @return
	 */
	public MonitorInfo getLastMonitorData(Integer car_id);
}
