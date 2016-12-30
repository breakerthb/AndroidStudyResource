package com.example.playmusicbysms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver  
{  
      
    private String TAG = "debug";     
    @Override    
    public void onReceive(Context context, Intent intent) {     
        Log.v(TAG, ">>>>>>>onReceive start");     
        // ��һ������ȡ���ŵ����ݺͷ�����      
        StringBuilder body = new StringBuilder();// ��������      
        StringBuilder number = new StringBuilder();// ���ŷ�����      
          
        Log.v(TAG, "number"+ number);  
        Bundle bundle = intent.getExtras();     
        if (bundle != null) {     
            Object[] _pdus = (Object[]) bundle.get("pdus");     
            SmsMessage[] message = new SmsMessage[_pdus.length];     
            for (int i = 0; i < _pdus.length; i++) {     
                message[i] = SmsMessage.createFromPdu((byte[]) _pdus[i]);     
            }     
            for (SmsMessage currentMessage : message) {     
                body.append(currentMessage.getDisplayMessageBody());     
                number.append(currentMessage.getDisplayOriginatingAddress());     
            }     
            String smsBody = body.toString();     
            String smsNumber = number.toString();   
              
              
              
            Log.v(TAG, "smsNumber"+ smsNumber);  
              
            Log.v(TAG, "smsBody"+ smsBody);  
            if (smsNumber.contains("+86")) {     
                smsNumber = smsNumber.substring(3);     
            }    
              
            String tmp[] =smsBody.split(";");  
              
            for(int i=0; i<tmp.length;i++)  
            {  
                System.out.println(tmp[i]);  
                  
                Log.v(TAG, "tmp[i]"+ tmp[i]);  
            }  
              
              
              
              
            // �ڶ���:ȷ�ϸö��������Ƿ������������      
            boolean flags_filter = true;     
            if (smsNumber.equals("10086")) {// ����10086�����Ķ���      
                flags_filter = true;     
                Log.v(TAG, "sms_number.equals(10086)");     
            }     
            // ������:ȡ��      
            if (flags_filter) {   
                  
                Log.v(TAG, "flags_filter");  
                this.abortBroadcast();     
            }     
        }     
        Log.v(TAG, ">>>>>>>onReceive end");     
    }     
  
  
  
  
} 
