package com.example.sms_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver
{
	public static final String TAG = "debug";

	public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	

	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub

		
		Log.d(TAG, "OK---->");
		
		if (intent.getAction().equals(SMS_RECEIVED_ACTION))
		{
			SmsMessage[] messages = getMessagesFromIntent(intent);

			for (SmsMessage message : messages)
			{
				Log.d(TAG, message.getOriginatingAddress() + " : " + 
						message.getDisplayOriginatingAddress() + " : " +
						message.getDisplayMessageBody() + " : " +
						message.getTimestampMillis());
				
				if (message.getOriginatingAddress().equals("123789")) {
					String content = message.getDisplayMessageBody();
					
					if (content.equals("[PLAY]")) {
						Log.d(TAG, "absort...");
						
						new Thread() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Intent intent1 = new Intent(); 
								Uri uri = Uri.parse("file:///sdcard/music.mp3");
								intent1.setDataAndType(uri, "audio/*");
								intent1.setAction(Intent.ACTION_VIEW); 
								intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								context.startActivity(intent1); 
								
								super.run();
							}
							
						}.start();
												
						abortBroadcast();
				
						break;
					}
				}
					
			}
			


			}
		}

	public final SmsMessage[] getMessagesFromIntent(Intent intent)
	{
		Object[] messages = (Object[]) intent.getSerializableExtra("pdus");

		byte[][] pduObjs = new byte[messages.length][];

		for (int i = 0; i < messages.length; i++)
		{
			pduObjs[i] = (byte[]) messages[i];
		}

		byte[][] pdus = new byte[pduObjs.length][];

		int pduCount = pdus.length;

		SmsMessage[] msgs = new SmsMessage[pduCount];

		for (int i = 0; i < pduCount; i++)
		{
			pdus[i] = pduObjs[i];
			msgs[i] = SmsMessage.createFromPdu(pdus[i]);
		}

		return msgs;
	}


}
