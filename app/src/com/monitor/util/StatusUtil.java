package com.monitor.util;

public class StatusUtil {
	
	//登陆网络异常
	public final static  int LOGIN_NET_ERROR = 0;
	
	//密码或用户名错误
	public final static  int LOGIN_NAME_OR_PSW_ERROR = 1;
	
	//登陆成功
	public final static  int LOGIN_SUCCESS = 2;
	
	//注册用户名已存在
	public final static  int REGISTER_NAME_ERROR = 3;
	
	//注册成功
	public final static  int REGISTER_SUCCESS = 4;
	
	//注册网络异常
	public final static  int REGISTER_NET_ERROR = 5;
	
	//获取某车辆所有的监测信息
	public final static int ALL_MONITOR_DATA = 6;
	
	//获取某车辆的最新的监测信息
	public final static int LAST_MONITOR_DATA = 7;
	
	//获取某车最新的监测信息异常
	public final static int GET_LOCATION_INFO_ERROR = 8;
	
}
