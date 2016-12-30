package com.incomingcallreminder.activity;

import java.util.Locale;

import com.incomingcall.reminder.R;
import com.incomingcallreminder.tts.MyTTS;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	TextToSpeech tts = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MyTTS tts = MyTTS.getInstance(MainActivity.this);
	
				
		findViewById(R.id.btnTest).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				
				
				//tts.speak("123456", TextToSpeech.QUEUE_FLUSH, null);
				//tts.stop();
				//tts.shutdown();
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
