package com.monitor.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		
		super(context, "monitorsysytem.db", null, 1); //创建数据库
	
	}

	@Override 
	public void onCreate(SQLiteDatabase db) {
		//生成车辆信息数据表
		db.execSQL("CREATE TABLE car("
				+ "_id integer primary key autoincrement,"
				+ "driver_name varchar(20) NOT NULL,"
				+ "number varchar(12) NOT NULL)");

	}

	@Override //版本号被修改时调用
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
