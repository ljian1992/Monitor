package com.monitor.service;

import com.monitor.dao.UserDao;
import com.monitor.domain.UserInfo;
import com.monitor.exception.UserMsgException;
import com.monitor.factory.DaoFactory;

public class UserService {

	private UserDao dao = DaoFactory.getFactory().getUserDao();
	
	/**
	 * 把用户新添加到数据库中
	 * @param user 要注册的用户
	 * @throws UserMsgException
	 */
	public void registerUser(UserInfo user) throws UserMsgException{
		
		//1.检查用户名是否已经存在
		if(null != dao.findUserByUserName(user.getusername())){
			throw new UserMsgException("用户名已存在");
		}
		
		//2.注册用户
		dao.addUser(user);
	}
	
	/**
	 * 根据用户名和密码判断用户是否在数据库中
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 有则返回用户数据类，无责返回NULL
	 */
	public UserInfo isUser(String username, String password){
		
		return dao.findUserByUNandPSW(username, password);
	}	
}
