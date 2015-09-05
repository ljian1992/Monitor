package com.monitor.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.monitor.exception.CarMsgException;
import com.monitor.exception.UserMsgException;

public class CarInfo implements Serializable{
	private int car_id;
	private String driver_name;
	private String number;
	
	
	

	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	/**
	 * 检测车辆信息是否正确
	 * @throws CarMsgException 
	 */
	public void checkValue() throws CarMsgException{
		if(null == driver_name || "".equals(driver_name)){
			throw new CarMsgException("司机名不能为空");
		}
		if(null == number || "".equals(number)){
			throw new CarMsgException("车牌不能为空");
		}
		
	}
	
}
