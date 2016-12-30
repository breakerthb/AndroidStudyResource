package com.example.musicstream;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ImageButton playButton;
	private TextView playTime;
	private StreamingMediaPlayer audioStreamer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		playTime = (TextView) findViewById(R.id.playTime);
		playButton = (ImageButton) findViewById(R.id.button_play);
		playButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (audioStreamer == null) {
					startStreamingAudio();
					playButton.setImageResource(R.drawable.button_pause);
					playButton.setEnabled(false);
				} else {
					if (audioStreamer.getMediaPlayer().isPlaying()) {
						audioStreamer.getMediaPlayer().pause();
						playButton.setImageResource(R.drawable.button_play);
					} else {
						audioStreamer.getMediaPlayer().start();
						audioStreamer.startPlayProgressUpdater();
						playButton.setImageResource(R.drawable.button_pause);
					}
				}
			}
		});
	}



	private void startStreamingAudio() {
		try {
			final SeekBar progressBar = (SeekBar) findViewById(R.id.progress_bar);
			audioStreamer = new StreamingMediaPlayer(this, playButton,
					progressBar, playTime);
			audioStreamer
					.startStreaming(
							"http://10.0.2.2:8888/share/music/HelloSong.mp3");
		} catch (IOException e) {
			Log.e(getClass().getName(), "Error starting to stream audio.", e);
		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
