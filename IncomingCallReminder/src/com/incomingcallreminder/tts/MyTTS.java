package com.incomingcallreminder.tts;

import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class MyTTS {
	
	private static MyTTS myTTS = null;
	
	private TextToSpeech mSpeech = null;
	
	private MyTTS(Context context) {
		mSpeech = new TextToSpeech(context, new OnInitListener() {
			
			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				mSpeech.setLanguage(Locale.ENGLISH);
				mSpeech.setSpeechRate(0.8f);
			}
		});
		
		
	}
	
	public static MyTTS getInstance(Context context) {
		if (myTTS == null) {
			myTTS = new MyTTS(context);
			// Test
			myTTS.read("ABC");
		}
		
		return myTTS;
	}

	public void destroy() {
		if (mSpeech != null) 
		{
            mSpeech.stop();
            mSpeech.shutdown();
        }
	}

	public void read(String text) {
		mSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
	
}
