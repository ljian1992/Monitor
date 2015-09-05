package com.monitor.dao;

import com.monitor.domain.UserInfo;

public interface UserDao {
	/**
	 * 根据用户名查找用户
	 * @param username 用户名
	 * @return 根据用户名找到的用户信息bean,如果没找到返回null
	 */
	public UserInfo findUserByUserName(String username);
	/**
	 * 添加用户
	 * @param user 要添加的用户信息bean
	 */
	public void addUser(UserInfo user);
	
	/**
	 * 根据用户名密码查找对应的用户
	 * @param username 用户名
	 * @param password 密码
	 * @return 找到的用户,如果找不到则返回null
	 */
	public UserInfo findUserByUNandPSW(String username,String password);
}
