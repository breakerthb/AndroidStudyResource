package com.example.threadcomm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity4 extends Activity {

	private final int UPDATE = 0;

	private TextView tvShow = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity1);

		tvShow = (TextView) findViewById(R.id.tvShow);

		Button btn = (Button) findViewById(R.id.btnSend);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final MyHandler mHandler = new MyHandler();
				
				new Thread() {
					public void run() {
						Message msg = mHandler.obtainMessage(UPDATE);
						mHandler.sendMessage(msg);
					}
				}.start();
			}
		});
	}

	private class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case UPDATE:// 在收到消息时，对界面进行更新
				tvShow.setText("This update by message");
				break;
			}
		}
	}

}
