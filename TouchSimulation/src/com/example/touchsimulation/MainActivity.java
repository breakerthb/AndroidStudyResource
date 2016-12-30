package com.example.touchsimulation;

import java.util.Random;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {

	private Draw draw = null;
	private MessageHandler messageHandler;
	private Thread thread;
	private boolean needTreadRun = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		draw = new Draw(this);
//		draw.setOnTouchListener(new OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				 System.out.println("mContent/2 touch:" + event);
//				return false;
//			}
//		});
	    setContentView(draw);//将view视图放到Activity中显示
	    
	    Looper looper = Looper.myLooper();
	    messageHandler = new MessageHandler(looper, draw);
	    thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				AutoRunThreadMethod();
				
			}
		});
	    thread.start();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		needTreadRun = false;
		
		super.onDestroy();
	}

	private void AutoRunThreadMethod()
	{
		int x = 0;
		int y = 0;
		DisplayMetrics metric = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(metric);
	        
		Random r = new Random();
		while(needTreadRun)
		{
			x = r.nextInt(metric.widthPixels);
			y = r.nextInt(metric.heightPixels);
			
			synchronized(this)
    		{
    			try
    			{
    				wait(1000); //1秒
    			}
    			catch (InterruptedException e)
    			{
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
			
    		Message message = Message.obtain();
			message.what = 1;
			message.arg1 = x;
			message.arg2 = y;
			String str = "x:" + x +"  y:" + y;
			Log.d("debug", str);
            messageHandler.sendMessage(message);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
