package com.fgk.im.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fgk.im.bean.Data;
import com.fgk.im.dao.IUserDao;
import com.fgk.im.entity.User;
import com.fgk.im.service.IUserService;
import com.fgk.im.socket.MessageSend;
import com.fgk.im.socket.SocketHandler;
import com.fgk.im.util.AppCache;

@Service("userService")
public class UserServiceImpl  implements IUserService{
	
	private HashMap<String, Object> result;
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public void login(HashMap<String, Object> params){
		
		String strUsername = params.get("strUsername").toString();//用户名
		String strPassword = params.get("strPassword").toString();//密码
		
		User user = new User(strUsername,strPassword);
		
		boolean flag = userDao.canLogin(user);
		result = _getHMInstance();
		result.put("success", flag);
		result.put("username",strUsername);
		MessageSend.send("userAction/login",result);
		
		if (flag) {
			
			//保存登录的用户的session
			AppCache.usernameIosessionMap.put(strUsername,SocketHandler.session);
		}
		
		
		System.out.println("userAction/login");
	}

	@Override
	public void register(HashMap<String, Object> params) {

		String strUsername = params.get("strUsername").toString();
		String strPassword = params.get("strPassword").toString();
		
		User user = new User(strUsername,strPassword);
		
		boolean flag = userDao.register(user);
		
		result = _getHMInstance();
		result.put("success", flag);
		MessageSend.send("userAction/register",result);
	}

	private HashMap<String, Object> _getHMInstance(){
		
		HashMap<String, Object> params = new HashMap<String,Object>();
		if (params.isEmpty()) {
			return params;
		}else{
			params.clear();//清空hashmap
			return params;
		}
	}
}
