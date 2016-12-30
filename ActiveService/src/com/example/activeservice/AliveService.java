package com.example.activeservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AliveService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true)
				{
					Log.d("debug", "I'm alive...");	
				
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}.start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
