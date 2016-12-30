package com.example.udpbroadcastsocket;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ServerActivity extends Activity {

	private MulticastSocket ds;
	String multicastHost="224.0.0.1";
	InetAddress receiveAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tvIPShow = (TextView) findViewById(R.id.tvIPShow);
		tvIPShow.setText("IP : " + getLocalIpAddress());
		
		Button btnStart = (Button) findViewById(R.id.btnStart);
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					ds = new MulticastSocket(8003);
					receiveAddress=InetAddress.getByName(multicastHost);
					ds.joinGroup(receiveAddress);
					
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							byte buf[] = new byte[1024];
							DatagramPacket dp = new DatagramPacket(buf, 1024);
							while (true) {
								try {
									ds.receive(dp);
									//Toast.makeText(this, new String(buf, 0, dp.getLength()), Toast.LENGTH_LONG);
									System.out.println("client ip : " + new String(buf, 0, dp.getLength()));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}).start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
	public String getLocalIpAddress() {   
		String str = "";
	      try {       
	         for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {       
	             NetworkInterface intf = en.nextElement();       
	              for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {       
	                  InetAddress inetAddress = enumIpAddr.nextElement();       
	                  if (!inetAddress.isLoopbackAddress()) {       
	                     str = inetAddress.getHostAddress().toString(); 
	                     return str;
	                 }        
	              }       
	          }       
	      } catch (SocketException ex) {       
	    	  Log.d("debug", ex.toString());       
	      }       
	     return str;       
	 }   

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
