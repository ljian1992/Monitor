package com.monitor.dao;

import java.util.List;

import android.database.Cursor;

import com.monitor.domain.CarInfo;


public interface CarDao {
	

	/**
	 * 添加纪录
	 * @param car
	 */
	public void save(CarInfo car);
	
	/**
	 * 根据id号删除记录
	 * @param id
	 */
	public void deleteById(Integer id);
	
	/**
	 * 更新记录
	 * @param car
	 */
	public void update(CarInfo car);
	
	/**
	 * 根据id号查找车辆信息
	 * @param id
	 * @return
	 */
	public CarInfo findById(Integer id);
	
	/**
	 * 根据司机名查找车辆信息
	 * @param driverName
	 * @return
	 */
	public CarInfo findByDriverName(String driverName);
	
	/**
	 * 根据司机名和车辆号码查找车辆信息
	 * @param driverName
	 * @param number
	 * @return
	 */
	public CarInfo findByDriverNameAndNumber(String driverName, String number);
	
	/**
	 * 根据车牌号查找车辆信息
	 * @param number
	 * @return
	 */
	public CarInfo findByNumber(String number);
	
		
	/**
	 * 获取基类总数
	 * @return
	 */
	public long getCount();
	
	/**
	 * 根据str进行模糊搜索
	 * @param name
	 * @return
	 */
	public Cursor query(String str);
	
	
}
