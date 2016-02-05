package com.fgk.im;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.fgk.im.util.AppCache;

public class ServerAppRun {
	
	private static final Logger LOGGER = Logger.getLogger(ServerAppRun.class);
	
	public static void main(String[] args) {
		final ApplicationContext context = new FileSystemXmlApplicationContext("conf/applicationContext.xml");
		AppCache.context = context;
		
		LOGGER.info("ServerApp Start Success");
		System.out.println("git ok");
	}

}
