package com.example.activitylifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Activity01 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity01);
		
		TextView v = (TextView) findViewById(R.id.txtView11);
		v.setText("Activity01");
		
		System.out.println("-->OnCreate");
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("-->OnDestroy");
		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		System.out.println("-->OnPause");
		
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("-->OnRestart");
		
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("-->OnResume");
		
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.println("-->OnStart");
		
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("-->OnStop");
		
		super.onStop();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity01, menu);
		return true;
	}

}
