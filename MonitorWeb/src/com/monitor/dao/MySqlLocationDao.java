package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.monitor.domain.LocationInfo;
import com.monitor.util.JDBCUtils;

public class MySqlLocationDao implements LocationDao {

	/**
	 * 根据车辆id获取车辆的位置信息
	 * @param car_id
	 * @return LocationInfo对象
	 */
	@Override
	public LocationInfo getLocationLastInfo(int car_id) {
		
		//要执行的SQL查找语句
		String sql = "select * from locationInfo where car_id=? order by location_id desc";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//获取数据库连接器对象
			conn = JDBCUtils.getConn();
			
			//获取传输器对象
			ps = conn.prepareStatement(sql);
			
			//写入sql语句name信息
			ps.setInt(1,  car_id);
			
			//执行sql查找语句
			rs = ps.executeQuery();
			
			if(rs.next()){
				LocationInfo locationInfo = new LocationInfo();
				
				//构建LocationInfo对象
				locationInfo.setLocation_id(Integer.valueOf(rs.getString("location_id")));
				locationInfo.setCar_id(Integer.valueOf(rs.getString("car_id")));
				locationInfo.setLatitude(rs.getDouble("latitude"));
				locationInfo.setLongitude(rs.getDouble("longitude"));
				
				return locationInfo;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//关闭连接
			JDBCUtils.close(rs, ps, conn);
		}
	}

}
