package com.monitor.factory;


import java.io.FileReader;
import java.util.Properties;

import com.monitor.dao.CarDao;
import com.monitor.dao.LocationDao;
import com.monitor.dao.MonitorDataDao;
import com.monitor.dao.UserDao;

public class DaoFactory {
	private static DaoFactory factory = new DaoFactory();
	private static Properties prop = null;
	
	static {
		prop = new Properties();
		try {
			prop.load(new FileReader(DaoFactory.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	private DaoFactory(){
		
	}
	
	public static DaoFactory getFactory(){
		return factory;
	}
	
	/**
	 * 
	 * @return 配置文件中配置的UserDao类
	 */
	public UserDao getUserDao(){
		
		try {
			String classz = prop.getProperty("UserDao");
			return (UserDao) Class.forName(classz).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
			
	}
	
	/**
	 * 
	 * @return 配置文件中配置的CarDao类
	 */
	public CarDao getCarDao(){
		
		try {
			String classz = prop.getProperty("CarDao");
			return (CarDao)Class.forName(classz).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
			
	}
	
	/**
	 * 
	 * @return 配置文件中配置的CarDao类
	 */
	public MonitorDataDao getMonitorDao(){
		
		try {
			String classz = prop.getProperty("MonitorDao");
			return (MonitorDataDao)Class.forName(classz).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
			
	}
	
	/**
	 * 
	 * @return 配置文件中的LocationDao类
	 */
	public LocationDao getLocationDao(){
		
		try {
			String classz = prop.getProperty("LocationDao");
			return (LocationDao)Class.forName(classz).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
			
	}
	
}
