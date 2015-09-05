package com.monitor.evenbus.info;

/**
 * 获取最新的监测信息事件，用于infoActivity发送给MonitorInfoFragment3，让其根据carId
 * 不断地从服务器中获取最新的地理位置信息
 * @author jian
 *
 */
public class GetLastLocationInfoEven {

	private int carId;
	
	public GetLastLocationInfoEven(int carId){
		setCarId(carId);
	}
	
	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

}
