package com.fgk.im.dao.impl;

import org.springframework.stereotype.Repository;

import com.fgk.im.dao.IUserDao;
import com.fgk.im.entity.User;

@Repository("userDao")//小写首字母 是因为定义这个变量时，java默认私有变量的首字母是小写，自动注入时方便
public class UserDaoImpl extends BaseDao<User,Integer> implements IUserDao {

	@Override
	public boolean canLogin(String username, String password) {
		
		
		return false;
	}
	
	/**
	 * 是否存在用户名
	 * @param username
	 * @return false 不存在 | true 存在
	 */
	public boolean existUsername(String username){
		
		return false;
		
	}



}
