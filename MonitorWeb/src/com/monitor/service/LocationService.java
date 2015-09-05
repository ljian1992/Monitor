package com.monitor.service;

import com.monitor.dao.LocationDao;
import com.monitor.domain.LocationInfo;
import com.monitor.factory.DaoFactory;
import com.monitor.util.XMlParserUtils;

public class LocationService {
private LocationDao dao = DaoFactory.getFactory().getLocationDao();
	
	public String getLastLocationInfo(int car_id){
		
		//获取最新的地理位置信息
		LocationInfo locationInfo = dao.getLocationLastInfo(car_id);
			
		//返回地理位置信息的xml字符串
		return XMlParserUtils.XMLLocationDataParserXtoS(locationInfo);
	}
}
