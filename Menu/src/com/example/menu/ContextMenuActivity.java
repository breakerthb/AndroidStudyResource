package com.example.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.Button;

public class ContextMenuActivity extends Activity {

	private Button btn1 = null;
	private Button btn2 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contextmenu);

		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);

		registerForContextMenu(btn1);
		registerForContextMenu(btn2);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		if (v == btn1) {
			menu.setHeaderTitle("这是1");
			menu.add(200, 200, 200, "Context Menu 1");
			menu.add(200, 201, 201, "Context Menu 2");
		} else if (v == btn2) {
			menu.setHeaderTitle("这是2");
			menu.add(300, 300, 300, "C 1");
			menu.add(300, 301, 301, "C 2");
		}

		super.onCreateContextMenu(menu, v, menuInfo);
	}

}
