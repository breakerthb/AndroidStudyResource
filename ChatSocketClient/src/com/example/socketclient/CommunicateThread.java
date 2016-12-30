package com.example.socketclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class CommunicateThread implements Runnable {

	private List<ChatInfo> listChat = null;
	private String send = "";
	
	public CommunicateThread(List<ChatInfo> listChat, String send){
		this.listChat = listChat;
		this.send = send;
	}
	
	@Override
	public void run() {
		
		
		ChatInfo chatInfo = new ChatInfo();
		chatInfo.setTime(new Date());
		chatInfo.setMsg(send);
		chatInfo.setComeMsg(false);
		listChat.add(chatInfo);
		
		String receive;
		
		Socket socket = null;
		
		try {
			// 创建Socket实例，并绑定连接远端IP地址和端口
			socket = new Socket("10.0.2.2", 9999);
			
			// Send Data Stream
			OutputStream ops = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(ops);
			dos.writeUTF(send);
			dos.flush();
			

			// Receive Data Stream
			InputStream ips = socket.getInputStream();
			DataInputStream dis = new DataInputStream(ips);
			receive = dis.readUTF();

			Log.d("debug", receive);
			
			chatInfo = new ChatInfo();
			chatInfo.setTime(new Date());
			chatInfo.setMsg(receive);
			chatInfo.setComeMsg(true);
			listChat.add(chatInfo);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
