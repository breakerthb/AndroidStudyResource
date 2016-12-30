package com.example.googletts;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etEnglish;
	Button btnEnglishTTS;
	TextToSpeech mSpeech;
	
	EditText etChinese;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etEnglish = (EditText) findViewById(R.id.etEnglish);
		btnEnglishTTS = (Button) findViewById(R.id.btnTTS);
		btnEnglishTTS.setEnabled(false);
		
		Button btnInit = (Button) findViewById(R.id.btnInit);
		Button btnDestroy = (Button) findViewById(R.id.btnDestory);
		
		btnInit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mSpeech = new TextToSpeech(getApplicationContext(), new OnInitListener() {
					
					@Override
					public void onInit(int status) {
						if (status == TextToSpeech.SUCCESS) {
							int result = mSpeech.setLanguage(Locale.ENGLISH);
							
							if (result == TextToSpeech.LANG_MISSING_DATA || 
									result == TextToSpeech.LANG_NOT_SUPPORTED) {
								Log.e("lanageTag", "not use");
							} else {
								btnEnglishTTS.setEnabled(true);
								mSpeech.speak("please input text", TextToSpeech.QUEUE_FLUSH, null);
							}
						}
					}
				});
				

			}
		});

		btnDestroy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mSpeech != null) 
				{
		            mSpeech.stop();
		            mSpeech.shutdown();
		            btnEnglishTTS.setEnabled(false);
		        }
			}
		});
		
		btnEnglishTTS.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {// 读取文本框里输入的英文
				String str = etEnglish.getText().toString();
				mSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null);
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		
		menu.add(0, 1, 1, R.string.speed);
		//menu.add(0, 2, 2, R.string.delete);
		
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			AlertDialog.Builder builder = new Builder(MainActivity.this);
			builder.setIcon(android.R.drawable.btn_star).setTitle("Speed Setting").setMessage("Please Chose Speed Model");
			builder.setNeutralButton("Fast", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO Auto-generated method stub
							mSpeech.setSpeechRate(2.0f);
							Toast.makeText(MainActivity.this, "Fast",
									Toast.LENGTH_LONG).show();
						}
					}).setNegativeButton("Normal", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO Auto-generated method stub
							mSpeech.setSpeechRate(1.0f);
							Toast.makeText(MainActivity.this, "Normal",
									Toast.LENGTH_LONG).show();
						}
					}).setPositiveButton("Slow", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO Auto-generated method stub
							mSpeech.setSpeechRate(0.5f);
							Toast.makeText(MainActivity.this, "Slow",
									Toast.LENGTH_LONG).show();
						}
					});
			
			Dialog dialog = builder.create();

			dialog.show();
			break;
//		case 2:
//			dbCtrl.open(false);
//			dbCtrl.delete(id);
//			
//			finish();
//			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
