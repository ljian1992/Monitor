package com.monitor.util;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.monitor.domain.CarInfo;
import com.monitor.domain.LocationInfo;
import com.monitor.domain.MonitorInfo;

public class XMlParserUtils {

	/**
	 * 将List<CarInfo>集合转换中XML字符串
	 * @param carList
	 * @return
	 */
	public static String XMLCarParserXtoS(List<CarInfo> carList){
		 
		//生成Document对象，Document对象代表XML文档
		 Document document = DocumentHelper.createDocument();
		 
		 //添加根节点
	     Element root = document.addElement( "cars" );
	     
	     //遍历CarList生成xml字符串
	     if(null != carList){   	         
	    	 for(CarInfo tmp:carList){
	            Element car = root.addElement("car")
	            		.addAttribute("car_id", String.valueOf(tmp.getCar_id()));
	            car.addElement("driver_name").addText(tmp.getDriver_name());
	            car.addElement("number").addText(tmp.getNumber());           
	         }
	     }	     
		return document.asXML();    
	}
	
	
	public static String XMLOneMonitorDataParserXtoS(MonitorInfo monitorData){
		
		 Document document = DocumentHelper.createDocument();
	     Element root = document.addElement( "MonitorDatas" );
	     
	     //变量monitorData生成xml字符串
	     if(null != monitorData){
	    	         
	    	
	            Element md = root.addElement("monitorData")
	            		.addAttribute("md_id", String.valueOf(monitorData.getCar_id()));
	            	            
	            md.addElement("temperature").addText(monitorData.getTemperature()+"");
	            md.addElement("humidity").addText(monitorData.getHumidity()+"");
	            md.addElement("carbon_dioxide").addText(monitorData.getCarbon_dioxide()+"");
	            md.addElement("oxygen").addText(monitorData.getOxygen()+"");
	            md.addElement("nitrogen").addText(monitorData.getNitrogen()+"");
	            md.addElement("ethylene").addText(monitorData.getEthylene()+"");
	            md.addElement("ammonia").addText(monitorData.getAmmonia()+"");
	            md.addElement("sulfur_dioxide").addText(monitorData.getSulfur_dioxide()+"");
	            md.addElement("car_id").addText(monitorData.getCar_id()+"");
	            md.addElement("time").addText(monitorData.getTime().toString());
	         
	     }
	     

		return document.asXML();    
	}
	
	public static String XMLMonitorDataParserXtoS(List<MonitorInfo> MonitorDataList){
		
		 Document document = DocumentHelper.createDocument();
	     Element root = document.addElement( "MonitorDatas" );
	     
	     //MonitorDataList生成xml文件
	     if(null != MonitorDataList){
	    	         
	    	 for(MonitorInfo tmp:MonitorDataList){
	            Element md = root.addElement("monitorData")
	            		.addAttribute("car_id", String.valueOf(tmp.getCar_id()));
	            
	            
	            md.addElement("temperature").addText(tmp.getTemperature()+"");
	            md.addElement("humidity").addText(tmp.getHumidity()+"");
	            md.addElement("carbon_dioxide").addText(tmp.getCarbon_dioxide()+"");
	            md.addElement("oxygen").addText(tmp.getOxygen()+"");
	            md.addElement("nitrogen").addText(tmp.getNitrogen()+"");
	            md.addElement("ammonia").addText(tmp.getAmmonia()+"");
	            md.addElement("sulfur_dioxide").addText(tmp.getSulfur_dioxide()+"");
	            md.addElement("car_id").addText(tmp.getCar_id()+"");
	            md.addElement("time").addText(tmp.getTime().toString());
	         }
	     }
	     

		return document.asXML();    
	}
	
	public static String XMLLocationDataParserXtoS(LocationInfo location){
		
		 Document document = DocumentHelper.createDocument();
	     Element root = document.addElement( "Locations" );
	     
	     //MonitorDataList生成xml文件
	     if(null != location){
	    	         
	            Element md = root.addElement("location")
	            		.addAttribute("location_id", location.getLocation_id()+"");
	                     
	            md.addElement("car_id").addText(location.getCar_id()+"");
	            md.addElement("latitude").addText(location.getLatitude()+"");
	            md.addElement("longitude").addText(location.getLongitude()+"");	        
	     }
		return document.asXML();    
	}
}
