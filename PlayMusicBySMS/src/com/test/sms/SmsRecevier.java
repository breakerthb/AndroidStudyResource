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
  
    // ���ܶ���   
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
                String content = message.getMessageBody();// �õ���������   
                String sender = message.getOriginatingAddress();// �õ�����Ϣ�ĺ���   
                if (sender.equals(num)) {  
                    abortBroadcast();// ��ֹ����   
                    Log.e("TAG", "�˺���Ϊ���������룬������!");  
                }  
                /* 
                 *  
                 * �ظ���Ϣ��Ҫ�õ� 
                Date date = new Date(message.getTimestampMillis()); 
                SimpleDateFormat format = new SimpleDateFormat( 
                        "yyyy-MM-dd HH:mm:ss"); 
                String sendContent = format.format(date) + ":" + sender + "--" 
                        + content; 
                SmsManager smsManager = SmsManager.getDefault();// ����Ϣʱ��Ҫ�� 
                smsManager.sendTextMessage("", null, sendContent, null, null);// ת���� 
                Log.v("TAG", sendContent); 
                */  
            }  
        }  
    }  
}  
