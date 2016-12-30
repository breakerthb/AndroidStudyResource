package com.example.wifi;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private WifiManager wifiManager = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnStart = (Button) findViewById(R.id.btnStart);
		Button btnStop = (Button) findViewById(R.id.btnStop);
		Button btnCheck = (Button) findViewById(R.id.btnCheck);
		
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				wifiManager.setWifiEnabled(true);
				System.out.println("Wifi state --->" + wifiManager.getWifiState());
				Toast.makeText(MainActivity.this, "当前WIFI状态为" + wifiManager.getWifiState(), Toast.LENGTH_SHORT);
			}
		});
		
		btnStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				wifiManager.setWifiEnabled(false);
				System.out.println("Wifi state --->" + wifiManager.getWifiState());
				Toast.makeText(MainActivity.this, "当前WIFI状态为" + wifiManager.getWifiState(), Toast.LENGTH_SHORT);
			}
		});
		
		btnCheck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				System.out.println("Wifi state --->" + wifiManager.getWifiState());
				Toast.makeText(MainActivity.this, "当前WIFI状态为" + wifiManager.getWifiState(), Toast.LENGTH_SHORT);
			
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
