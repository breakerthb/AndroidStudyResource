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

public class Activity2 extends Activity {

	TextView tvShow = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);

		Button button = (Button) findViewById(R.id.btnSend);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Looper curLooper = Looper.myLooper();
						Looper mainLooper = Looper.getMainLooper();

						String msg;
						MyHandler mHandler = null;

						if (curLooper == null) {
							mHandler = new MyHandler(mainLooper);
							msg = "curLooper is null";
						} else {
							mHandler = new MyHandler(curLooper);
							msg = "This is curLooper";
						}

						mHandler.removeMessages(0);

						Message m = mHandler.obtainMessage(1, 1, 1, msg);

						mHandler.sendMessage(m);
					}

				}.start();

			}
		});

		tvShow = (TextView) findViewById(R.id.tvShow);
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