package com.example.asynctask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView tvShow;
	private Button mButton;
	private ProgressBar mProgressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvShow = (TextView) findViewById(R.id.tvShow);
		mButton = (Button) findViewById(R.id.button);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetCSDNLogoTask task = new GetCSDNLogoTask();
				task.execute("http://csdnimg.cn/www/images/csdnindex_logo.gif");
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	/**
	 * 	1.Params�����ݸ���̨����Ĳ������͡�
	 *  2.Progress����̨����ִ�й����У�������λ��progress��units�������͡������Ǻ�̨�����Ѿ�ִ���˰ٷ�֮���ˡ���
	 *  3.Result�� ��ִ̨�з��صĽ�������͡�
	 */
	class GetCSDNLogoTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			Log.d("debug", "-->doInBackground()");
			
			for (int i = 0; i < 10; i++) {
				publishProgress((i + 1) * 10);// �������onProgressUpdate(Integer... progress)����
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return "SUCCESS";
		}

		protected void onProgressUpdate(Integer... progress) {
			Log.d("debug", "-->onProgressUpdate()");
			
			mProgressBar.setProgress(progress[0]);
			tvShow.setText(progress[0] + "%");
		}

		protected void onPostExecute(String result) {
			Log.d("debug", "-->onPostExecute()");
			
			Toast.makeText(MainActivity.this, result,
					Toast.LENGTH_LONG).show();
			tvShow.setText(result);
		}

		protected void onPreExecute() {
			Log.d("debug", "-->onPreExecute()");
			
			mProgressBar.setProgress(0);
		}

		protected void onCancelled() {
			Log.d("debug", "-->onCancelled()");
			
			mProgressBar.setProgress(0);
		}

	}

}
