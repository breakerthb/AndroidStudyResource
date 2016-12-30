package com.example.preferenceactvity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	public static final int SET = Menu.FIRST;
	public static final int EXIT = Menu.FIRST + 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

	//点击Menu菜单选项响应事件 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case 1:
			Intent mIntent = new Intent();
			mIntent.setClass(this, MyPreference.class);
			startActivity(mIntent);
			break;
		case 2:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, SET, 0, "设置");
		menu.add(0, EXIT, 0, "退出");
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
