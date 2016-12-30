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
					tv.setText("���ֲ�����...");
				} catch (Exception e) {
					tv.setText("���ŷ����쳣...");
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
						tv.setText("����ֹͣ����...");
					}
				} catch (Exception e) {
					tv.setText("����ֹͣ�����쳣...");
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
							tv.setText("ֹͣ����!");
						} else if (isPaused == true) {
							mp.start();
							isPaused = false;
							tv.setText("��ʼ����!");
						}
					}
				} catch (Exception e) {
					tv.setText("�����쳣...");
					e.printStackTrace();
				}
			}
		});

		/* ��MediaPlayer.OnCompletionLister�����е�Listener */
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			// @Override
			/* �����ļ���������¼� */
			public void onCompletion(MediaPlayer arg0) {
				try {
					/*
					 * �����Դ��MediaPlayer�ĸ�ֵ��ϵ ����Դ����Ϊ������������
					 */
					mp.release();
					/* �ı�TextViewΪ���Ž��� */
					tv.setText("���ֲ�������!");
				} catch (Exception e) {
					tv.setText(e.toString());
					e.printStackTrace();
				}
			}
		});

		/* ��MediaPlayer.OnErrorListener�����е�Listener */
		mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			@Override
			/* ���Ǵ������¼� */
			public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				try {
					/* ��������ʱҲ�����Դ��MediaPlayer�ĸ�ֵ */
					mp.release();
					tv.setText("���ŷ����쳣!");
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
