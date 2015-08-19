package com.niuxin.chat;



public class ChatServer{
	
	public void startService() {	
		Server server = new Server();
		new Thread(server).start();	
	}

	
}
