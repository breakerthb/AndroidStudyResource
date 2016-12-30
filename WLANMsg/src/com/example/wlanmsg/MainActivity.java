package com.example.wlanmsg;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	 private Button btnClientStart, btnClientStop, btnServerStart, btnServerStop;
		
	   private String TAG = "debug";
	   
	   private MulticastServer mServerSend = null;
	   private MulticastClient mClientReceive = null;
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        btnClientStart = (Button)this.findViewById(R.id.client_start);
        btnClientStart.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "btnClientStart");
				
				try {
					if(mClientReceive == null) {
						mClientReceive = new MulticastClient(MainActivity.this);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				mClientReceive.startReceive();
				

			}
		});
        
        btnClientStop = (Button)this.findViewById(R.id.client_stop);
        btnClientStop.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "btnClientStop");
				
				if(mClientReceive != null) {
					mClientReceive.stopReceive();
				}
			}
		});
        
        
        btnServerStart = (Button)this.findViewById(R.id.server_start);
        btnServerStart.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "btnServerStart");
				
				if(mServerSend == null) {
					mServerSend = new MulticastServer(MainActivity.this);
				}
				mServerSend.startSend();
			}
		});
        
        
        btnServerStop = (Button)this.findViewById(R.id.server_stop);
        btnServerStop.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "btnServerStop");
				
				mServerSend.stopSend();
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
