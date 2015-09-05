package com.monitor.service;

import java.util.List;
import com.monitor.dao.MonitorDataDao;
import com.monitor.domain.MonitorInfo;
import com.monitor.factory.DaoFactory;
import com.monitor.util.XMlParserUtils;


public class MonitorService {
	private MonitorDataDao dao = DaoFactory.getFactory().getMonitorDao();
	
	/**
	* 根据车辆id获取该车最新的监测信息，并转换为xml字符串返回
	* @return 根据车辆id返回最新的监测信息的xml字符串
	*/
	public String getLastMonitorData(Integer car_id){	
		MonitorInfo monitorData = dao.getLastMonitorData(car_id);
		return XMlParserUtils.XMLOneMonitorDataParserXtoS(monitorData);
		
	}

	
	/**
	 * 根据车辆id查询车辆所有的监测信息，并转换为xml字符串
	 * @param car_id
	 * @return
	 */
	public String getAllMonitorData(Integer car_id){
		
		List<MonitorInfo> monitorDataList = dao.getAllMonitorDataByCar(car_id);

		return XMlParserUtils.XMLMonitorDataParserXtoS(monitorDataList);
	}
	
}
