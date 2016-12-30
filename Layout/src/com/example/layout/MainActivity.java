package com.example.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore.Action;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn1 = (Button) findViewById(R.id.btn1);
		Button btn2 = (Button) findViewById(R.id.btn2);
		Button btn3 = (Button) findViewById(R.id.btn3);
		Button btn4 = (Button) findViewById(R.id.btn4);
		Button btn5 = (Button) findViewById(R.id.btn5);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("para", 1);
				
				intent.setClass(MainActivity.this, LayoutDisplay.class);
				
				startActivity(intent);
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("para", 2);
				
				intent.setClass(MainActivity.this, LayoutDisplay.class);
				
				startActivity(intent);
			}
		});
		
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("para", 3);
				
				intent.setClass(MainActivity.this, LayoutDisplay.class);
				
				startActivity(intent);
			}
		});
		
		btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("para", 4);
				
				intent.setClass(MainActivity.this, LayoutDisplay.class);
				
				startActivity(intent);
			}
		});
		
		btn5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("para", 5);
				
				intent.setClass(MainActivity.this, LayoutDisplay.class);
				
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
