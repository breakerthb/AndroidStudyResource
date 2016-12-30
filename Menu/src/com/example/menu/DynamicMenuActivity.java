package com.example.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class DynamicMenuActivity extends Activity {

	private TextView tvPara = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamicmenu);
		
		tvPara = (TextView) findViewById(R.id.tvPara);
		
		findViewById(R.id.btnM).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvPara.setText("M");
			}
		});
		
		findViewById(R.id.btnN).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvPara.setText("N");
			}
		});
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		String currentText = tvPara.getText().toString();
		if ("M".equals(currentText)) {
			menu.clear();// 先清掉菜单
			MenuItem item = menu.add(0, 400, 401, "to N");// 可以通过点击这个菜单项来改变tv1的值这样(变成N)就可以测试了
			item.setIcon(android.R.drawable.alert_dark_frame);// android自带的图标
		}
		if ("N".equals(currentText)) {
			menu.clear();// 先清掉菜单
			MenuItem item = menu.add(0, 401, 402, "to M");// 可以通过点击这个菜单项来改变tv1的值这样(变成M)就可以测试了
			item.setIcon(android.R.drawable.alert_light_frame);
		}
		menu.add(0, 402, 403, "Now is " + currentText);// 现在共有两个菜单子项

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			break;
		case 2:
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
