package com.monitor.domain;

import java.io.Serializable;

import com.monitor.exception.UserMsgException;

/*
 * 用于存储用户信息
 * */
public class UserInfo implements Serializable{
	private int user_id;
	private String username;
	private String nickname;
	private String password;
	private String password2;
	private String gender;
	private String phone;
	private String email;
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 检查用户信息格式是否正确
	 * 
	 * @throws UserMsgException
	 */
	public void checkValue() throws UserMsgException{
		if(null == username || "".equals(username)){
			throw new UserMsgException("用户名不能为空");
		}
		if(null == nickname || "".equals(nickname)){
			throw new UserMsgException("昵称不能为空");
		}
		if(null == password || "".equals(password)){
			throw new UserMsgException("密码不能为空");
		}
		if(null == password2 || "".equals(password2)){
			throw new UserMsgException("确认密码不能为空");
		}
		if(!password.toString().equals(password2.toString())){
			throw new UserMsgException("两次密码不一致");
		}
		if(null == phone || "".equals(phone)){
			throw new UserMsgException("手机号码不能为空");
		}
		if(11 != phone.length()){
			throw new UserMsgException("手机号码长度不对");
		}
		if(null == email || "".equals(email)){
			throw new UserMsgException("邮箱不能为空");
		}
		if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
			throw new UserMsgException("邮箱格式不正确");
		}
		
	}
}
