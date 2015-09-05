package com.monitor.evenbus.info;

/**
 * 获取最新的监测信息事件，用于infoActivity发送给LocationInfoFragment，让其根据carId
 * 不断地从服务器中获取最新的监测数据
 * @author jian
 *
 */
public class GetLastMonitorInfoEven {

	private int carId;
	
	public GetLastMonitorInfoEven(int carId){
		setCarId(carId);
	}
	
	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

}
