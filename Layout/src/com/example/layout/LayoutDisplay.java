package com.example.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LayoutDisplay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = this.getIntent();
		
		int para = intent.getIntExtra("para", -1);
		switch (para)
		{
		case 1:
			setContentView(R.layout.linearlayout);
			break;
		case 2:
			setContentView(R.layout.framelayout);
			break;
		case 3:
			setContentView(R.layout.absolutelayout);
			break;
		case 4:
			setContentView(R.layout.relativelayout);
			break;
		case 5:
			setContentView(R.layout.tablelayout);
			break;
		default:
			break;
		}
	}

}
