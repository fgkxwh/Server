package com.fgk.im.socket;

import java.util.ArrayList;
import java.util.List;

import com.fgk.im.bean.Data;
import com.google.gson.Gson;

/**
 * 信息发送
 * @author Administrator
 *
 */
public class MessageSend {
	
	public static void send(String uri,Object...params){
		
		List<Object> temParams = new ArrayList<Object>();
		for (int i = 0; i < params.length; i++) {
			temParams.add(params[i]);
		}

		Data data = new Data("UserService/login",temParams);
		if (SocketHandler.session != null) {
			
			SocketHandler.session.write(data);
		}else {
			
			System.out.println(" MessageSend.send:"+"发送时session为空，数据发送失败");
		}
		
	}

}
