package com.monitor.xmlparse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.monitor.domain.UserInfo;

public class Xmluser {
	public String userParse(UserInfo user){
		Document document = DocumentHelper.createDocument();
	    Element root = document.addElement("users");

	    Element u = root.addElement("user")
	    		.addAttribute("user_id", user.getUser_id()+"")
	    		.addAttribute("username", user.getUsername())
	    		.addAttribute("nickname", user.getNickname())
	    		.addAttribute("password", user.getPassword())
	    		.addAttribute("password2", user.getPassword2())
	    		.addAttribute("gender", user.getGender())
	    		.addAttribute("phone", user.getPhone())
	    		.addAttribute("email", user.getEmail());
	   
	    String xml = document.asXML();
	    return xml;
	}
	
	public String loginUserParse(UserInfo user){
		Document document = DocumentHelper.createDocument();
	    Element root = document.addElement("users");

	    Element u = root.addElement("user")
	    		.addAttribute("username", user.getUsername())
	    		.addAttribute("password", user.getPassword());
	    		
	   
	    String xml = document.asXML();
	    return xml;
	}
	
}
