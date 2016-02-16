package com.fgk.im.dao;

import com.fgk.im.entity.User;

public interface IUserDao {
	
	/**
	 * 检测是否可以登录
	 * @param username
	 * @param password
	 * @return false 不可以  | true 可以 
	 */
	public boolean canLogin(User user);
	
	/**
	 * 注册
	 * @param username
	 * @param password
	 * @return false 注册失败 | true 注册成功
	 */
	public boolean register(User user);

}
