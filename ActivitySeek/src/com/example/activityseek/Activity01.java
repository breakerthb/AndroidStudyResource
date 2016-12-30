package com.example.activityseek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Activity01 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        Intent intent = new Intent();  
        intent.putExtra("return", "ABC");  
        setResult(RESULT_OK, intent);  
        finish();  
	}
}
