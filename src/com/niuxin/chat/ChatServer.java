package com.niuxin.chat;

import javax.annotation.Resource;

public class ChatServer{
	@Resource
	private Server server;
	
	public void startService() {	
		//Server server = new Server();
		new Thread(server).start();	
		
	}

	
}
