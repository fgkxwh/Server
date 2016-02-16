package com.fgk.im.service;

import java.util.ArrayList;

public interface IUserService {
	
	//登录服务接口
	public void login(ArrayList<Object> params);
	
	//注册服务接口
	public void register(ArrayList<Object> params);

}
