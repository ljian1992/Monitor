package com.monitor.dao;

import com.monitor.domain.LocationInfo;

public interface LocationDao {
	
	/**
	 * 根据车辆id获取车辆的位置信息
	 * @param car_id
	 * @return LocationInfo对象
	 */
	public LocationInfo getLocationLastInfo(int car_id);
}
