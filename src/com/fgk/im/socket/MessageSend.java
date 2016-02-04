package com.fgk.im.socket;

import com.fgk.im.bean.Data;

/**
 * 信息发送
 * @author Administrator
 *
 */
public class MessageSend {
	
	public static void send(Data data){
		
		if (SocketHandle.session != null) {
			
			SocketHandle.session.write(data);
		}else {
			
			System.out.println(" MessageSend.send:"+"发送时session为空，数据发送失败");
		}
	    
	}

}
