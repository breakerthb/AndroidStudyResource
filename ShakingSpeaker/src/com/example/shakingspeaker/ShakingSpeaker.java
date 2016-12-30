package com.example.shakingspeaker;
import java.text.DecimalFormat;

import com.google.tts.TextToSpeechBeta;
import com.google.tts.TextToSpeechBeta.OnInitListener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.TextView;

public class ShakingSpeaker extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	private Sensor accelerator;
	
	private float x, y, z;
	private boolean init;
	private DecimalFormat df;
	
	private TextToSpeechBeta soundTTS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		this.x = this.y = this.z = 0.0f;
		df = new DecimalFormat("0.00");
		init = true;
		
		soundTTS = new TextToSpeechBeta(this, new OnInitListener() {
			
			@Override
			public void onInit(int arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		this.accelerator = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume() {
		super.onResume();

		this.sensorManager.registerListener(this, this.accelerator, SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.sensorManager.unregisterListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float new_x = event.values[SensorManager.DATA_X];
		float new_y = event.values[SensorManager.DATA_Y];
		float new_z = event.values[SensorManager.DATA_Z];
		
		if (this.init) {
			this.x = new_x;
			this.y = new_y;
			this.z = new_z;
			
			this.init = false;
			
			return;
		}
		
		TextView tv = (TextView) this.findViewById(R.id.tvShow);
		TextView tvResult = (TextView) this.findViewById(R.id.tvResult);
		TextView tvHistory = (TextView) this.findViewById(R.id.tvHistory);
		
		float delt_x = Math.abs(x - new_x);
		float delt_y = Math.abs(y - new_y);
		float delt_z = Math.abs(z - new_z);
		if (delt_x > 8.0) {
			tvResult.setText("left_right");
			if (this.soundTTS.isSpeaking() == false) {
				this.soundTTS.speak("ÔÙ¼û", 0, null);
			} else if (delt_z > 10.0) {
				tvResult.setText("forward_backup");
				if (this.soundTTS.isSpeaking() == false) {
					this.soundTTS.speak("ÄãºÃ", 0, null);
				} else {
					tvResult.setText("NULL");
				}
			}
		}
		
		this.x = new_x;
		this.y = new_y;
		this.z = new_z;
		
		tvHistory.setText(tvResult.getText());
		String text = "x:" + df.format(new_x) + "y:" + df.format(new_y) + "z:" + df.format(new_z);
		tv.setText(text);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
