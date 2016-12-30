package com.example.sms_broadcast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn = (Button) findViewById(R.id.btnTest);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				// Uri uri = Uri.parse("file:///sdcard/music.mp3");
				Uri uri = Uri
						.parse("android.resource://com/example/sms_broadcast/raw/music.mp3");
				intent.setDataAndType(uri, "audio/*");
				intent.setAction(Intent.ACTION_VIEW);
				startActivity(intent);

			}
		});

	}

	public void copyResToSdcard(String name) {// name为sd卡下制定的路径
		Field[] raw = R.raw.class.getFields();
		for (Field r : raw) {
			try {
				// System.out.println("R.raw." + r.getName());
				int id = getResources().getIdentifier(r.getName(), "raw",
						getPackageName());
				
				if (!r.getName().equals("allapps")) {
					String path = name + "/" + r.getName() + ".mp3";
					BufferedOutputStream bufEcrivain = new BufferedOutputStream(
							(new FileOutputStream(new File(path))));
					BufferedInputStream VideoReader = new BufferedInputStream(
							getResources().openRawResource(id));
					byte[] buff = new byte[20 * 1024];
					int len;

					while ((len = VideoReader.read(buff)) > 0) {
						bufEcrivain.write(buff, 0, len);
					}

					bufEcrivain.flush();
					bufEcrivain.close();
					VideoReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory(); // 获取跟目录
		}
		
		return sdDir.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
