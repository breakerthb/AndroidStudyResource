package com.barry.wifitrans.net;

import com.barry.wifitrans.model.Member;
import com.barry.wifitrans.util.INI;
import com.barry.wifitrans.util.MyApplication;

import android.content.Context;

public class NetManager {
	
	private boolean ap_start;
	private Context context = null;
	
	public NetManager(Context context) {
		ap_start = true;
		this.context = context;
	} 

  
	/*
	 * Listen the others' info
	 */
	public void  broadcastListen() {
		new Thread() {

			@Override
			public void run() {
				
				String ip = "";
				
				BroadcastServer server = new BroadcastServer(INI.PORT_BROADCAST);

				while (ap_start) {
					ip = server.Listen();
					
					Member member = new Member();
	            	member.setIp(ip);
	            	
	            	SocketClient client = new SocketClient();
	            	member.setName(client.askName(member.getIp()));
	            	
	            	MyApplication myApplication = (MyApplication) context.getApplicationContext();
	            	myApplication.listMembers.add(member);
				}
		
				server.close();
			}
			
		}.start();

	}
	
	/*
	 * Send Info to others
	 */
	public static void broadcastSend(Context context) {
		// TODO Auto-generated method stub
		BroadcastClient client = new BroadcastClient(context);
		client.process();
	}

	public void beAsk() {
		new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SocketServer server = new SocketServer(INI.PORT_SOCKET_CHAT);
				
				while (ap_start) {
					server.beAskListen();
				}
				
				server.close();
			}
			
		}.start();
		
	}

	public void prepareReceiveFile() {
		// TODO Auto-generated method stub
		new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SocketServer server = new SocketServer(INI.PORT_SOCKET_FILE);
		
				while(ap_start) {
					String ip = server.prepareReceiveFile();
					
					// Receive File
					SocketClient client = new SocketClient();
					client.ReceiveFile(ip);
				}
		
				server.close();
			}
			
		}.start();
		
	
	}

	public static boolean prepareSendFile(String ip, String fileName) {
		// TODO Auto-generated method stub
		SocketClient client = new SocketClient();
		return client.prepareSendFile(ip, fileName);
	}
	
	public static void SendFile(String filePath) {
		SocketServer server = new SocketServer(INI.PORT_SOCKET_FILE_TRAN);
		server.SendFile(filePath);
		server.close();
	}

	public void stop() {
		ap_start = false;
	}
}
