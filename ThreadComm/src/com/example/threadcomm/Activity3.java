package com.example.threadcomm;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends Activity {

	Handler mHandler;
	TextView mText;

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			mText.setText("This is Update from ohter thread, Mouse DOWN");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		
		mHandler = new Handler();
		mText = (TextView) findViewById(R.id.tvShow);

		Button btnSubTread = (Button) findViewById(R.id.btnSend);
		btnSubTread.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {
					public void run() {
						mHandler.post(runnable);
					}
				}.start();
			}
		});

	}
}
