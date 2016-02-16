package com.fgk.im.dao.impl;

import javax.persistence.criteria.From;

import org.springframework.stereotype.Repository;

import com.fgk.im.dao.IUserDao;
import com.fgk.im.entity.User;

@Repository("userDao")//小写首字母 是因为定义这个变量时，java默认私有变量的首字母是小写，自动注入时方便
public class UserDaoImpl extends BaseDao<User,Integer> implements IUserDao {

	@Override
	public boolean canLogin(User user) {
		
		if (_existUsername(user)){//如果存在该用户名
			
			if (_passwordCorrect(user)) {//如果密码正确
				
				return true;
			}else {
				
				System.out.println("用户密码错误");
				return false;
			}
			
		}else {
			
			System.out.println("用户名不存在");
			return false;
		}
	}
	
	/**
	 * 是否存在用户名
	 * @param username
	 * @return false 不存在 | true 存在
	 */
	private boolean _existUsername(User user){
		
		Object result =  createQuery("From User u Where u.username = ?",user.getUsername()).uniqueResult();
		
		if (result == null) {
			
			return false;
		}else{
			
			return true;
		}
	}

	/**
	 * 
	 * @param user
	 * @return false 密码错误 | true 密码正确
	 */
	private boolean _passwordCorrect(User user){
		
		Object result = createQuery("From User u Where u.username = ? And u.password = ?",
				user.getUsername(),user.getPassword());
		
		if (result == null) {
			
			return false;
		}else {
			
			return true;
		}
	}
	
	@Override
	public boolean register(User user) {
		
		super.add(user);
		return false;
	}



}
