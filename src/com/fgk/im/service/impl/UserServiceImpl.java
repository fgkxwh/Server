package com.fgk.im.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fgk.im.bean.Data;
import com.fgk.im.dao.IUserDao;
import com.fgk.im.entity.User;
import com.fgk.im.service.IUserService;
import com.fgk.im.socket.MessageSend;

@Service("UserService")
public class UserServiceImpl  implements IUserService{
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public void login(ArrayList<Object> params){
		
		String strUsername = params.get(0).toString();//用户名
		String strPassword = params.get(1).toString();//密码
		
		User user = new User(strUsername,strPassword);
		
		boolean flag = userDao.canLogin(user);
		
		if (flag) {//可以登录
			
			MessageSend.send("UserAction/login",true);
		}else {//不可以登录
			
			MessageSend.send("UserAction/login",false);
		}
	}

	@Override
	public void register(ArrayList<Object> params) {

		String strUsername = params.get(0).toString();
		String strPassword = params.get(1).toString();
		
		User user = new User(strUsername,strPassword);
		
		boolean flag = userDao.register(user);
		
		if (flag) {//注册成功
			
			MessageSend.send("UserAction/register",true);
		}else {
			
			MessageSend.send("UserAction/register",false);
		}
	}

}
