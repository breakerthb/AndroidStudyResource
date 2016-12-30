package com.example.broadcast

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.view.Menu
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

public class MainActivity extends Activity 

private SMSReceiver smsReceiver
private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED"
@Overrid
protected void onCreate(Bundle savedInstanceState) 
super.onCreate(savedInstanceState)
setContentView(R.layout.activity_main)

Button btnSend = (Button) findViewById(R.id.btnSend)
Button btnRegist = (Button) findViewById(R.id.btnRegist)
Button btnUnRegist =(Button) findViewById(R.id.unRegist)

btnSend.setOnClickListener(new OnClickListener() 

@Overrid
public void onClick(View v) 
// TODO Auto-generated method stu
TestReceiver tr = new TestReceiver()
Intent intent = new Intent()
intent.setAction(Intent.ACTION_EDIT)
MainActivity.this.sendBroadcast(intent)


})

btnRegist.setOnClickListener(new OnClickListener() 

@Overrid
public void onClick(View v) 
// TODO Auto-generated method stu
smsReceiver = new SMSReceiver()
IntentFilter filter = new IntentFilter()
filter.addAction(SMS_ACTION)
MainActivity.this.registerReceiver(smsReceiver, filter)

})

btnUnRegist.setOnClickListener(new OnClickListener() 

@Overrid
public void onClick(View v) 
// TODO Auto-generated method stu
MainActivity.this.unregisterReceiver(smsReceiver)

})


@Overrid
public boolean onCreateOptionsMenu(Menu menu) 
// Inflate the menu; this adds items to the action bar if it is present
getMenuInflater().inflate(R.menu.main, menu)
return true





