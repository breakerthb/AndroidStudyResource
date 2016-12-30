package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

	public SMSReceiver() {
		Log.d("debug", "SMSReceiver : SMSReceiver Start!");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.d("debug", "SMSReceiver : Receive a message!");
	}

}
