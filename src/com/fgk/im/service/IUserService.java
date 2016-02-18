package com.fgk.im.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface IUserService {
	
	//登录服务接口
	public void login(HashMap<String, Object> params);
	
	//注册服务接口
	public void register(HashMap<String, Object> params);

}
