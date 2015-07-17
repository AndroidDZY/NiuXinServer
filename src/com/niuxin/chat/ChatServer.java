package com.niuxin.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer implements Runnable{
	private ServerSocket serverSocket;
	private boolean isStart;
	// 所有在线的客户端
	List<ClientThread> clients = new ArrayList<ClientThread>();


	public void startService() {
	
		ChatServer server = new ChatServer();
		new Thread(server).start();

	}

	/* 处理单个客户端的线程类 */
	class ClientThread implements Runnable {
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRun;

		public ClientThread(Socket s) {
			this.socket = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				isRun = true;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public void send(String s) {
			try {
				dos.writeUTF(s);
				System.out.println(s);
			} catch (IOException e) {
				clients.remove(this);
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while (isRun) {
					String s = dis.readUTF();
					for (int i = 0; i < clients.size(); i++) {
						// 对在线的客户端发送消息
						ClientThread c = clients.get(i);
						c.send(s);
					}
				}
			} catch (EOFException e) {
				clients.remove(this);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(9999);
			isStart = true;
			System.out.println("9999端口 等待客户端连接……");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (isStart) {
				Socket socket = serverSocket.accept();
				ClientThread client = new ClientThread(socket);
				new Thread(client).start();
				clients.add(client);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
