package com.test.sms;

import java.text.SimpleDateFormat;  
import java.util.Date;  
  
import android.content.BroadcastReceiver;  
import android.content.Context;  
import android.content.Intent;  
import android.telephony.SmsManager;  
import android.telephony.SmsMessage;  
import android.util.Log;  
  
public class SmsRecevier extends BroadcastReceiver {  
    private String num;  
  
    public SmsRecevier(String number) {  
        Log.v("TAG", "debug");  
        num=number;  
    }  
  
    // 接受短信   
    @Override  
    public void onReceive(Context context, Intent intent) {  
        Log.v("TAG", "SmsRecevier onReceive");  
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");  
        if (pdus != null && pdus.length > 0) {  
            SmsMessage[] messages = new SmsMessage[pdus.length];  
            for (int i = 0; i < pdus.length; i++) {  
                byte[] pdu = (byte[]) pdus[i];  
                messages[i] = SmsMessage.createFromPdu(pdu);  
            }  
            for (SmsMessage message : messages) {  
                String content = message.getMessageBody();// 得到短信内容   
                String sender = message.getOriginatingAddress();// 得到发信息的号码   
                if (sender.equals(num)) {  
                    abortBroadcast();// 中止发送   
                    Log.e("TAG", "此号码为黑名单号码，已拦截!");  
                }  
                /* 
                 *  
                 * 回复信息需要用到 
                Date date = new Date(message.getTimestampMillis()); 
                SimpleDateFormat format = new SimpleDateFormat( 
                        "yyyy-MM-dd HH:mm:ss"); 
                String sendContent = format.format(date) + ":" + sender + "--" 
                        + content; 
                SmsManager smsManager = SmsManager.getDefault();// 发信息时需要的 
                smsManager.sendTextMessage("", null, sendContent, null, null);// 转发给 
                Log.v("TAG", sendContent); 
                */  
            }  
        }  
    }  
}  
