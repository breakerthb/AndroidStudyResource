package com.example.udpbroadcastsocket;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ClientActivity extends Activity {

	/*发送广播端的socket*/
	private MulticastSocket ms;
	/*发送广播的按钮*/
	private Button sendUDPBrocast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sendUDPBrocast = (Button) findViewById(R.id.btnSend);
		sendUDPBrocast.setOnClickListener(new SendUDPBrocastListener());
		try {
			/*创建socket实例*/
			ms = new MulticastSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 单击按钮时，发送局域网广播
	 * */
	class SendUDPBrocastListener implements OnClickListener{
		
		@Override
		public void onClick(View v) {
			//发送的数据包，局网内的所有地址都可以收到该数据包
			DatagramPacket dataPacket = null;
			
			try {
				ms.setTimeToLive(4);
				
				//将本机的IP（这里可以写动态获取的IP）地址放到数据包里，其实server端接收到数据包后也能获取到发包方的IP的
				byte[] data = "192.168.19.1".getBytes();
				
				//224.0.0.1为广播地址
				InetAddress address = InetAddress.getByName("224.0.0.1");
				//这个地方可以输出判断该地址是不是广播类型的地址
				System.out.println(address.isMulticastAddress());
				dataPacket = new DatagramPacket(data, data.length, address,	8003);
				ms.send(dataPacket);
				ms.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
