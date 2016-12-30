package com.example.download;

import com.example.utils.HttpDownloader;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
		
		Button btnDownloadText = (Button) findViewById(R.id.btnDownloadText);
		Button btnDownloadMP3 = (Button) findViewById(R.id.btnDownloadMP3);
		
		btnDownloadText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HttpDownloader httpDownloader = new HttpDownloader();
				String lrc = httpDownloader.download("http://10.0.2.2:8888/Struts2/abc.txt");
				System.out.println(lrc);
			}
		});
		
		btnDownloadMP3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HttpDownloader httpDownloader = new HttpDownloader();
				int ret = httpDownloader.downFile("http://10.0.2.2:8888/Struts2/SEP.png", "File/", "a.png");
				System.out.println(ret);
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
