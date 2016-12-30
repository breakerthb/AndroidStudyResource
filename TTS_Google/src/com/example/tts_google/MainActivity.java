package com.example.tts_google;

import java.util.Locale;

import com.google.tts.TextToSpeechBeta;
import com.google.tts.TextToSpeechBeta.OnInitListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnInitListener {
	/** Called when the activity is first created. */
	private Button mBtn;
	private EditText mText;
	// ʹ��com.google.tts���е�TextToSpeechBeta
	private TextToSpeechBeta mTTS;

	private static final String TAG = "debug";
	private static final int REQ_TTS_STATUS_CHECK = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ���TTS�����Ƿ��Ѿ���װ���ҿ���
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeechBeta.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);

		mText = (EditText) findViewById(R.id.etInput);
		mBtn = (Button) findViewById(R.id.btnTTS);
		mBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ttsText = mText.getText().toString();
				if (ttsText != "") {
					// ��ȡ�ı����е�����
					mTTS.speak(ttsText, TextToSpeechBeta.QUEUE_ADD, null);
				}
			}
		});
	}

	// ʵ��TTS��ʼ���ӿ�
	public void onInit(int status, int version) {
		// TODO Auto-generated method stub
		Log.v(TAG, "version = " + String.valueOf(version));
		// �ж�TTS��ʼ���ķ��ذ汾�ţ����Ϊ-1����ʾû�а�װ��Ӧ��TTS����
		if (version == -1) {
			// ��ʾ��װ�����TTS����
			alertInstallEyesFreeTTSData();
		} else {
			// TTS Engine��ʼ�����
			if (status == TextToSpeechBeta.SUCCESS) {
				Log.v(TAG, "success to init tts");
				// ����TTS���棬com.google.tts��eSpeak֧�ֵ����԰������ģ�ʹ��AndroidϵͳĬ�ϵ�pico��������Ϊcom.svox.pico
				mTTS.setEngineByPackageNameExtended("com.google.tts");
				int result = mTTS.setLanguage(Locale.CHINA);
				// ���÷�������
				if (result == TextToSpeechBeta.LANG_MISSING_DATA
						|| result == TextToSpeechBeta.LANG_NOT_SUPPORTED)
				// �ж������Ƿ����
				{
					Log.v(TAG, "Language is not available");
					mBtn.setEnabled(false);
				} else {
					mTTS.speak("���,����!", TextToSpeechBeta.QUEUE_ADD, null);
					mBtn.setEnabled(true);
				}
			} else {
				Log.v(TAG, "failed to init tts");
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_TTS_STATUS_CHECK) {
			switch (resultCode) {
			case TextToSpeechBeta.Engine.CHECK_VOICE_DATA_PASS:
			{
				// ʹ�õ���TextToSpeechBeta
				mTTS = new TextToSpeechBeta(this, this);
				Log.v(TAG, "TTS Engine is installed!");

			}
				break;
			case TextToSpeechBeta.Engine.CHECK_VOICE_DATA_BAD_DATA:
				// ��Ҫ��������������
			case TextToSpeechBeta.Engine.CHECK_VOICE_DATA_MISSING_DATA:
				// ȱ����Ҫ���Ե���������
			case TextToSpeechBeta.Engine.CHECK_VOICE_DATA_MISSING_VOLUME:
			// ȱ����Ҫ���Եķ�������
			{
				// ��������������������д�,�������ذ�װ��Ҫ������
				Log.v(TAG, "Need language stuff:" + resultCode);
				Intent dataIntent = new Intent();
				dataIntent
						.setAction(TextToSpeechBeta.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(dataIntent);
			}
				break;
			case TextToSpeechBeta.Engine.CHECK_VOICE_DATA_FAIL:
				// ���ʧ��
			default:
				Log.v(TAG, "Got a failure. TTS apparently not available");
				break;
			}
		} else {
			// ����Intent���صĽ��
		}
	}

	// �����Ի�����ʾ��װ�����TTS����
	private void alertInstallEyesFreeTTSData() {
		Builder alertInstall = new AlertDialog.Builder(this)
				.setTitle("ȱ����Ҫ��������")
				.setMessage("���ذ�װȱ�ٵ�������")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// ����eyes-free���������ݰ�
						String ttsDataUrl = "http://eyes-free.googlecode.com/files/tts_3.1_market.apk";
						Uri ttsDataUri = Uri.parse(ttsDataUrl);
						Intent ttsIntent = new Intent(Intent.ACTION_VIEW,
								ttsDataUri);
						startActivity(ttsIntent);
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		alertInstall.create().show();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mTTS != null) {
			mTTS.shutdown();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mTTS != null) {
			mTTS.stop();
		}
	}

}
