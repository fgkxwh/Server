package com.fgk.im.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mina.core.session.IoSession;

import com.fgk.im.bean.Data;
import com.google.gson.Gson;

/**
 * 信息发送
 * @author Administrator
 *
 */
public class MessageSend {
	
	public static void send(String uri,HashMap<String, Object> params){

		Data data = new Data(uri,params);
		if (SocketHandler.session != null) {
			
			SocketHandler.session.write(data);
		}else {
			
			System.out.println(" MessageSend.send:"+"发送时session为空，数据发送失败");
		}
		
	}

	public static void send(String uri,HashMap<String, Object> params,IoSession session){
		
		Data data = new Data(uri,params);
		if (session != null) {
			
			session.write(data);
		}else {
			
			System.out.println(" MessageSend.send:"+"发送时session为空，数据发送失败");
		}
		
	}
}
