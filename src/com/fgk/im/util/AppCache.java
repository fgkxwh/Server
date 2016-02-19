package com.fgk.im.util;

import java.util.HashMap;

import org.apache.mina.core.session.IoSession;
import org.springframework.context.ApplicationContext;

public class AppCache {
	
	public static ApplicationContext context;
	
	//在线用户的用户名和session的对应表
	public static HashMap<String, IoSession> usernameIosessionMap = new HashMap<String, IoSession>();

}
