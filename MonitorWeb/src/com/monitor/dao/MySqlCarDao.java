package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.monitor.domain.CarInfo;
import com.monitor.util.JDBCUtils;

public class MySqlCarDao implements CarDao{

	@Override
	public List<CarInfo> getAllCarInfo() {
		String sql = "select * from car";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		//ArrayList非现场安全，但效率比Vector高
		List<CarInfo> carList = new ArrayList<CarInfo>();
		
		try {
			//获取数据库连接器对象
			conn = JDBCUtils.getConn();
			
			//获取传输器对象
			ps = conn.prepareStatement(sql);
									
			//执行sql查找语句
			rs = ps.executeQuery();
			
			//把找到的car信息存入ArrayList<Car>容器当中
			if(rs.next()){				
				do {
					CarInfo car = new CarInfo();
					
					car.setCar_id(rs.getInt("car_id"));
					car.setDriver_name(rs.getString("driver_name"));
					car.setNumber(rs.getString("number"));
					
					carList.add(car);
					car = null;	
				}while (rs.next());
				
				return carList;
			}
			else{
				return null;
			}			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
		
	}

}
