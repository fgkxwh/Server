package com.fgk.im.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.fgk.im.service.ITalkService;
import com.fgk.im.socket.MessageSend;

/** 
* @author fanguangkai E-mail: fgkxwh@126.com
* @version 创建时间：2016年2月19日 上午10:33:58 
* 聊天服务类说明 
*/
@Service("talkService")
public class TalkServiceImpl implements ITalkService{

	@Override
	public void privateTalk(HashMap<String, Object> params) {
		
		String message = params.get("message").toString();//客户端发送来的私聊信息
		
		//转发给发给的人
		MessageSend.send("talkAction/privateTalk", params);
		System.out.println("message:"+message);
	}
	

}
