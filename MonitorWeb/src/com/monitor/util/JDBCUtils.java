package com.monitor.util;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static Properties prop = null;
	private JDBCUtils() {
	}
	static{
		try{
			prop = new Properties();  //新建一个读取配置文件类
			
			//用类加载器加载配置文件config.properties
			String filepath = JDBCUtils.class.getClassLoader().getResource("config.properties").getPath();
			FileReader file = new FileReader(filepath);
			prop.load(file);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取连接
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @return 数据库连接器对象
	 */
	public static Connection getConn() throws ClassNotFoundException, SQLException{
		
		// 1.注册数据库驱动
		Class.forName(prop.getProperty("driver"));
		// 2.获取连接对象
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
	}
	
	/**
	 * 关闭连接
	 * @param rs 资源
	 * @param stat 传输器对象
	 * @param conn 连接器对象
	 */
	public static void close(ResultSet rs, Statement stat,Connection conn){
		//关闭记录集
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		
		//关闭声明
		if(stat!=null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stat = null;
			}
		}
		
		//关闭连接对象
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	
	}
}
