package com.incomingcallreminder.receiver;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;
import com.incomingcallreminder.tts.MyTTS;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneStateReceiver extends BroadcastReceiver {
	private static final String TAG = "debug";

	// private static MyPhoneStateListener phoneListener = new
	// MyPhoneStateListener();

	private static boolean incomingFlag = false;

	private static String incoming_number = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		
		// 如果是拨打电话
		if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
			incomingFlag = false;
			String phoneNumber = intent
					.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Log.i(TAG, "call OUT:" + phoneNumber);
		} else {
			// 如果是来电
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Service.TELEPHONY_SERVICE);

			switch (tm.getCallState()) {
			case TelephonyManager.CALL_STATE_RINGING: // 来电
				incomingFlag = true;// 标识当前是来电
				incoming_number = intent.getStringExtra("incoming_number");
				Log.i(TAG, "RINGING :" + incoming_number);
				
				ITelephony iTelephony = PhoneStateReceiver.getITelephony(context);
				try {
					iTelephony.endCall();
					Log.i(TAG, "RINGING : Filter...");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				MyTTS tts = MyTTS.getInstance(context);
				tts.read("coming call  " + incoming_number);
				
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: // 摘记
				if (incomingFlag) {
					Log.i(TAG, "incoming ACCEPT :" + incoming_number);
				}
				break;

			case TelephonyManager.CALL_STATE_IDLE: // 空闲
				if (incomingFlag) {
					Log.i(TAG, "incoming IDLE");
				}
				break;
			}
		}
		
		//abortBroadcast();
	}
	
	 private static ITelephony getITelephony(Context context) {
	        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	        Class<TelephonyManager> c = TelephonyManager.class;
	        Method getITelephonyMethod = null;
	        
	        try {
	            getITelephonyMethod = c.getDeclaredMethod("getITelephony",
	                    (Class[]) null); // 获取声明的方法
	            getITelephonyMethod.setAccessible(true);
	        } catch (SecurityException e) {
	            e.printStackTrace();
	        } catch (NoSuchMethodException e) {
	            e.printStackTrace();
	        }

	        ITelephony iTelephony = null;
	        try {
	        	iTelephony = (ITelephony) getITelephonyMethod.invoke(mTelephonyManager, (Object[]) null); // 获取实例
	            return iTelephony;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return iTelephony;
	    }

}
