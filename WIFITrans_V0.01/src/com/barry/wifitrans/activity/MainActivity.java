package com.barry.wifitrans.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.barry.wifitrans.model.UserInfo;
import com.barry.wifitrans.net.NetManager;
import com.barry.wifitrans.net.WIFI;
import com.barry.wifitrans.util.INI;
import com.barry.wifitrans.util.MyApplication;
import com.example.broadcast.R;

public class MainActivity extends Activity {

	NetManager netManager = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Check WIFI & Get IP
		String ip = INI.IP_SIMU;
		if (INI.LOCAL == INI.ATHOME) {
			WIFI wifi = new WIFI(this);
			wifi.CheckWIFI();
			ip = wifi.GetMyIP();
		}
		
		String name = android.os.Build.MODEL;
		
		UserInfo myInfo = new UserInfo(ip, name);
		
		MyApplication myApplication = (MyApplication) getApplication();
		myApplication.setMyInfo(myInfo);
		
		Button btnJoin = (Button) findViewById(R.id.btnJoin);
		btnJoin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NetManager netManager = new NetManager(MainActivity.this);
				
				netManager.broadcastListen();
				netManager.beAsk();
				netManager.prepareReceiveFile();
				
				netManager.broadcastSend(MainActivity.this);
			}
		});
		
		Button btnShowMem = (Button) findViewById(R.id.btnShowMem);
		btnShowMem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MembersActivity.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
