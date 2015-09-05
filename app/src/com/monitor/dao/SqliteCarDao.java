package com.monitor.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.monitor.domain.CarInfo;
import com.monitor.service.DBOpenHelper;

public class SqliteCarDao implements CarDao {

	private DBOpenHelper dbOpenHelper;

	public SqliteCarDao(Context context) {
		this.dbOpenHelper = new DBOpenHelper(context);
	}



	@Override
	public void save(CarInfo car) {

		// 获取数据库实例
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		
		if(null == findById(car.getCar_id())){
			db.execSQL(
					"insert into car(_id, driver_name, number) values(?,?,?)",
					new Object[] { car.getCar_id(),car.getDriver_name(),
							car.getNumber() });
		}else{
			update(car);
		}
	}

	@Override
	public void deleteById(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from car where _id=?", new Object[] { id });

	}

	@Override
	public void update(CarInfo car) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL(
				"update car set driver_name=?, number=? where _id=?",
				new Object[] { car.getDriver_name(),
						car.getNumber(), car.getCar_id() });

	}

	@Override
	public CarInfo findById(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from car where _id=?",
				new String[] { id.toString() });

		if (cursor.moveToFirst()) {
			CarInfo car = new CarInfo();

			// 填充车辆信息
			car.setCar_id(cursor.getInt(cursor.getColumnIndex("_id")));
			car.setDriver_name(cursor.getString(cursor
					.getColumnIndex("driver_name")));
			car.setNumber(cursor.getString(cursor.getColumnIndex("number")));

			cursor.close();
			return car;
		} else {
			cursor.close();
			return null;
		}

	}

	@Override
	public CarInfo findByDriverName(String driverName) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from car where driver_name=?",
				new String[] { driverName });

		if (cursor.moveToFirst()) {
			CarInfo car = new CarInfo();

			// 填充车辆信息
			car.setCar_id(cursor.getInt(cursor.getColumnIndex("_id")));
			car.setDriver_name(cursor.getString(cursor
					.getColumnIndex("driver_name")));
			car.setNumber(cursor.getString(cursor.getColumnIndex("number")));
			
			cursor.close();
			return car;
		} else {
			cursor.close();
			return null;
		}
	}

	@Override
	public CarInfo findByNumber(String number) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from car where number=?",
				new String[] { number });

		if (cursor.moveToFirst()) {
			CarInfo car = new CarInfo();

			// 填充车辆信息
			car.setCar_id(cursor.getInt(cursor.getColumnIndex("_id")));
			car.setDriver_name(cursor.getString(cursor
					.getColumnIndex("driver_name")));
			car.setNumber(cursor.getString(cursor.getColumnIndex("number")));
			

			cursor.close();
			return car;
		} else {
			cursor.close();
			return null;
		}
	}



	@Override
	public long getCount() {

		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from car", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
	
		return result;
	
	}
	
	/**
	 * 根据输入内容模块查询
	 * @param name
	 * @return
	 */
	public Cursor query(String str) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		/*Cursor c = db.rawQuery("select* from car driver_name like '%" + name
				+ "%' limit 15", null);*/
		
		Cursor c = db.rawQuery("select * from car where driver_name like '%" + str + "%' limit 10",null);
		
		
		return c;

	}

	@Override
	public CarInfo findByDriverNameAndNumber(String driverName, String number) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from car where driver_name=? and number=?",
				new String[] {driverName, number});

		if (cursor.moveToFirst()) {
			CarInfo car = new CarInfo();

			// 填充车辆信息
			car.setCar_id(cursor.getInt(cursor.getColumnIndex("_id")));
			car.setDriver_name(cursor.getString(cursor
					.getColumnIndex("driver_name")));
			car.setNumber(cursor.getString(cursor.getColumnIndex("number")));
			

			cursor.close();
			return car;
		} else {
			cursor.close();
			return null;
		}
	
	}

}
