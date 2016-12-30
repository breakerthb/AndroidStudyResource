package com.example.touchsimulation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

class MessageHandler extends Handler
{
	View v;
	public MessageHandler(Looper looper, View v)
	{
		super(looper);
		this.v = v;
	}
	
    @Override
    public void handleMessage(Message msg)
    {
    	Bundle bundle = msg.getData();
    	switch (msg.what)
    	{
    	case 1:
    		//µã»÷
    		v.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), 
    				SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, msg.arg1, msg.arg2, 0));
            //v.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 30, 30, 0));
    		break;
    	default:
    		break;
    	}
    	
    }
}
