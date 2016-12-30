package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	private String TAG = "debug";
	
	public class MyBinder extends Binder {

		MyService getServices() {
			return MyService.this;
		}

	}

	private final IBinder mBinder = new MyBinder();
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "MyService : onBind");
		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "MyService : onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "MyService : onDestroy");
		
	}



	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d(TAG, "MyService : onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(TAG, "MyService : onStartCommand");
		
		return super.onStartCommand(intent, flags, startId);
	}

}
