package com.example.thread.demo;

import java.util.Timer;
import java.util.TimerTask;

import com.example.thread.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AsyncTaskActivity extends Activity {

    public final static int LOAD_PROGRESS = 0; 
    public final static int CLOSE_PROGRESS = 1; 
    
    TextView mTextView = null;

    Timer mTimer = null;
    TimerTask mTimerTask = null;
   
    int mTimerID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_async);
        
        Button btnSend = (Button) findViewById(R.id.btnSend);
        mTextView = (TextView) findViewById(R.id.textView1);

        btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 new AsyncTask<Object, Object, Object>() {
			            @Override
			            protected void onPreExecute() {
			                mTextView.setText("开始加载进度");
			                super.onPreExecute();
			            }

			            @Override
			            protected Object doInBackground(Object... arg0) {
			                Long startTime = System.currentTimeMillis();
			                
			                for (int i = 0; i < 50; i++) {
			                	try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			                	System.out.println("Out 4 : " + i);
			                    publishProgress(i);
			                }
			                
			                Long endTime = System.currentTimeMillis();
			                
			                return endTime - startTime;
			            }

			            @Override
			            protected void onPostExecute(Object result) {
			                mTextView.setText("一共耗时" + result+ "毫秒");
			                
			                super.onPostExecute(result);
			            }
			            
			            
			            @Override
			            protected void onProgressUpdate(Object... values) {
			                mTextView.setText("当前加载进度" + values[0]);
			                super.onProgressUpdate(values);
			            }
			        }.execute();
			}
		});
        
        super.onCreate(savedInstanceState);
    }

}
