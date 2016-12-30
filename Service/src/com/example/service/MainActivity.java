package com.example.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private String TAG = "debug";

	private MyService mService;
	private static boolean ISRUNNING = false;

	private Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		intent = new Intent(MainActivity.this, MyService.class);

		Button btnStart = (Button) findViewById(R.id.btnStart);
		Button btnBind = (Button) findViewById(R.id.btnBind);
		Button btnUnbind = (Button) findViewById(R.id.btnUnbind);
		Button btnStop = (Button) findViewById(R.id.btnStop);

		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "Start Service...");
				// --- 启动服务 ----
				startService(intent);
				ISRUNNING = true;
			}
		});
		
		btnBind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Bind Service...");
				if (ISRUNNING) {
					// ---- 绑定 ----
					bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
				}
			}
		});

		btnUnbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Unbind Service...");
				// ---- 取消绑定 ----
				unbindService(mConnection);
			}
		});

		btnStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Stop Service...");
				// ---- 关闭服务 ----
				stopService(intent);
				ISRUNNING = false;
			}
		});
	}

	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d(TAG, "onServiceConnected()");
			mService = ((MyService.MyBinder) service).getServices();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d(TAG, "onServiceDisconnected");
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
