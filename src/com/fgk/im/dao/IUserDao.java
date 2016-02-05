package com.fgk.im.dao;

public interface IUserDao {
	
	/**
	 * 检测是否可以登录
	 * @param username
	 * @param password
	 * @return false 不可以  | true 可以 
	 */
	public boolean canLogin(String username,String password);

}
