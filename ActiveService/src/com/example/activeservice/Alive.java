package com.example.activeservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alive extends BroadcastReceiver {

	public Alive() {
		Log.d("debug", "Init ...");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d("debug", "Start ...");
		
		Intent service = new Intent();
		// service.setAction("com.xxx.service.PushService");
		service.setClass(context, AliveService.class);
		context.startService(service);

//		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
//			System.out.println("手机开机了...bootComplete!");
//		} else if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())) {
//			System.out.println("新安装了应用程序....pakageAdded!");
//		} else if (Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction())) {
//			System.out.println("应用程序被卸载了....pakageRemoved!");
//		} else if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
//			System.out.println("手机被唤醒了.....userPresent");
//			Intent service = new Intent();
//			// service.setAction("com.xxx.service.PushService");
//			service.setClass(context, AliveService.class);
//			context.startService(service);
//		}

	}

}
