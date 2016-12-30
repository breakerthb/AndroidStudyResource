package com.example.threadcomm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity1 extends Activity {
	TextView tvShow = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		
		tvShow = (TextView) findViewById(R.id.tvShow);
		
		findViewById(R.id.btnSend).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				MyHandler myHandler = new MyHandler(Looper.myLooper());
				myHandler.removeMessages(0);
				
				String msgStr = "主线程不同组件通信:消息来自button";
				Message m = myHandler.obtainMessage(1, 1, 1, msgStr);
				myHandler.sendMessage(m);
			}
		});
		
	}

	private class MyHandler extends Handler {
		public MyHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			tvShow.setText(msg.obj.toString());
		}
	}

}
