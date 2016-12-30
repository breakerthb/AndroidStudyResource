package com.example.playmusicbysms;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MediaPlayer mp;
	private TextView tv;
	private boolean isPaused = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnPlay = (Button) findViewById(R.id.btnPlay);
		Button btnPause = (Button) findViewById(R.id.btnPause);
		Button btnStop = (Button) findViewById(R.id.btnStop);

		tv = (TextView) findViewById(R.id.txtShow);

		mp = MediaPlayer.create(this, R.raw.music);

		btnPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (mp != null) {
						mp.stop();
					}
					mp.prepare();
					mp.start();
					tv.setText("音乐播放中...");
				} catch (Exception e) {
					tv.setText("播放发生异常...");
					e.printStackTrace();
				}
			}
		});

		btnPause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (mp != null) {
						mp.stop();
						tv.setText("音乐停止播放...");
					}
				} catch (Exception e) {
					tv.setText("音乐停止发生异常...");
					e.printStackTrace();
				}

			}
		});

		btnStop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (mp != null) {
						if (isPaused == false) {
							mp.pause();
							isPaused = true;
							tv.setText("停止播放!");
						} else if (isPaused == true) {
							mp.start();
							isPaused = false;
							tv.setText("开始播发!");
						}
					}
				} catch (Exception e) {
					tv.setText("发生异常...");
					e.printStackTrace();
				}
			}
		});

		/* 当MediaPlayer.OnCompletionLister会运行的Listener */
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			// @Override
			/* 覆盖文件播出完毕事件 */
			public void onCompletion(MediaPlayer arg0) {
				try {
					/*
					 * 解除资源与MediaPlayer的赋值关系 让资源可以为其它程序利用
					 */
					mp.release();
					/* 改变TextView为播放结束 */
					tv.setText("音乐播发结束!");
				} catch (Exception e) {
					tv.setText(e.toString());
					e.printStackTrace();
				}
			}
		});

		/* 当MediaPlayer.OnErrorListener会运行的Listener */
		mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			@Override
			/* 覆盖错误处理事件 */
			public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				try {
					/* 发生错误时也解除资源与MediaPlayer的赋值 */
					mp.release();
					tv.setText("播放发生异常!");
				} catch (Exception e) {
					tv.setText(e.toString());
					e.printStackTrace();
				}
				return false;
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
