package com.example.myexplorer;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "MyExplorerDemo";
	private static final int REQUEST_EX = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("explorer_title", getString(R.string.dialog_read_from_dir));
				intent.setDataAndType(Uri.fromFile(new File("/sdcard")), "*/*");
				intent.setClass(MainActivity.this, ExDialog.class);
				startActivityForResult(intent, REQUEST_EX);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		String path;
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_EX) {
				Uri uri = intent.getData();
				TextView text = (TextView) findViewById(R.id.text);
				text.setText("select: " + uri);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
