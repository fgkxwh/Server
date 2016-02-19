package com.fgk.im.socket;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.transport.Session;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import com.fgk.im.bean.Data;
import com.fgk.im.bean.Data;
import com.fgk.im.util.AppCache;
import com.google.gson.Gson;

import sun.net.www.content.image.gif;
import sun.print.resources.serviceui;

@Service("socketHandler")
public class SocketHandler implements IoHandler{

	//当前最新连接的用户的session
	public static IoSession session;
	
	
	//在线的用户名和用户的session映射表
	public static HashMap<String,IoSession> usernameSessionMap = new HashMap<String, IoSession>();
	
	@Override
	public void exceptionCaught(IoSession session, Throwable e) throws Exception {

		System.out.println("Server exceptionCaught:" + e.getMessage());
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		System.out.println("Server messageReceived:"+ session + "  :"+message);
		
		if (message instanceof Data) {
			
			 Data data = (Data) message;//转换为Data对象
			
			 String uri = data.getUri();
		     String[] arrUri = uri.split("/");//以/分割开 类名和 方法名
		     String strClass = arrUri[0];//类名
		     String strMethod = arrUri[1];//方法名
		     HashMap<String, Object> params = data.getParams();//参数
		     
//		     params.add(session);//在参数列表后 加上session

		     
			 Object service = AppCache.context.getBean(strClass);
			 Method m = service.getClass().getDeclaredMethod(strMethod, params.getClass());
		 	 Object result = m.invoke(service, params);
			   
		 	 
			 //如果返回不为空   void返回类型的函数 会返回 Void Void和null不相同
//			 if (result != null) {
//					
//					List<Object> returnParams = new ArrayList<Object>();
//				
//					returnParams.add(result);
//					
//					
//					MessageSend.send(new Data(data.getUri(),returnParams));
//				}
		}
		
	}
	
	
//	private Class<?>[] getClasses(List<Object> params) {
//		
//			Class<?>[] classArray = new Class<?>[params.size()];
//
//			for (int i = 0; i < params.size(); i++) {
//				classArray[i] = params.get(i).getClass();
//			}
//			return classArray;
//	}

	@Override
	public void messageSent(IoSession session, Object object) throws Exception {
		System.out.println("Server messageSent:"+ session + "  :"+object);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("Server sessionClosed:"+ session );
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		
		System.out.println("Server sessionCreated:"+ session );
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus idleStatus) throws Exception {

		System.out.println("Server sessionIdle:"+ session +"  , " +idleStatus.toString());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		
		System.out.println("Server sessionOpened:"+ session );
		this.session = session;
		
		
		
//		MessageSend.send(new Data("UserService/login",new ArrayList<Object>()));
	}

}
