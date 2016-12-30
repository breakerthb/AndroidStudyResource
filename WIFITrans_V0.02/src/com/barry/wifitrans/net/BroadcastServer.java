package com.barry.wifitrans.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.barry.wifitrans.model.Member;
import com.barry.wifitrans.util.INI;
import com.barry.wifitrans.util.MyApplication;

/**
 * Listen the other users access
 * 
 * @author BarryTang
 * 
 */
public class BroadcastServer {

	private MulticastSocket ms = null;
	
	public BroadcastServer(int broadcastPost) {
		try {
			ms = new MulticastSocket(broadcastPost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		ms.close();
	}
	
	/**
	 * Listen the other users
	 * 
	 * @return Access user's IP
	 */
	public String Listen() {
		// TODO Auto-generated method stub
		String ip = "";
		try {
			InetAddress receiveAddress = InetAddress
					.getByName(INI.IP_BROADCAST);
			ms.joinGroup(receiveAddress);

			byte buf[] = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, 1024);

			try {
				ms.receive(dp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ip = new String(buf, 0, dp.getLength());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ip;
	}
}