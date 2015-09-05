package com.monitor.activity;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class MapApplication extends Application {

	public LocationClient bdLocationClient;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		//启动项目时，自动初始化百度地图
		SDKInitializer.initialize(getApplicationContext()); 
		
		//初始化百度地图核心类
		bdLocationClient = new LocationClient(this);
	}
}