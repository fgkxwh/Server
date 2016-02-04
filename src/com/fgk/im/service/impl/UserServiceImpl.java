package com.fgk.im.service.impl;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

import com.fgk.im.bean.Data;
import com.fgk.im.service.IUserService;
import com.fgk.im.socket.MessageSend;

@Service("UserService")
public class UserServiceImpl  implements IUserService{
	
	@Override
	public void login(ArrayList<Object> params){
		
		System.out.println("UserService.login");
		for (Object object : params) {
			System.out.println(object);
		}
		
		
//		MessageSend.send(new Data("UserAction/login",params));
		
	}

}
