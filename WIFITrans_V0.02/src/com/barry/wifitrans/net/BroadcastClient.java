package com.barry.wifitrans.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import com.barry.wifitrans.util.INI;
import com.barry.wifitrans.util.MyApplication;

import android.content.Context;
import android.util.Log;

public class BroadcastClient {

	Context context = null;
	public BroadcastClient(Context context) {
		this.context = context;
	}
	
	public void process() {
		// TODO Auto-generated method stub
		MyApplication myApplication = (MyApplication) context.getApplicationContext();
		
		MulticastSocket ms;
		try {
			ms = new MulticastSocket();
			ms.setTimeToLive(4);
			
			byte[] data = myApplication.getMyInfo().getIp().getBytes();
			
			//Broadcast IP
			InetAddress address = InetAddress.getByName(INI.IP_BROADCAST);
			if (!address.isMulticastAddress()) {
				Log.d(INI.TAG, INI.IP_BROADCAST + " is not broadcast address");
			}
			
			DatagramPacket dataPacket = new DatagramPacket(data, data.length, address, INI.PORT_BROADCAST);
			
			ms.send(dataPacket);
			ms.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
