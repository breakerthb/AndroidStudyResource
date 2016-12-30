package com.example.thread.demo;

import java.util.Timer;
import java.util.TimerTask;

import com.example.thread.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TimerTaskActivity extends Activity {

    public final static int LOAD_PROGRESS = 0; 
    public final static int CLOSE_PROGRESS = 1; 
    
    private Timer timer = null;
    private TimerTask timerTask = null;
    
    TextView mTextView = null;
    
    private int timerId = 0;
    
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case LOAD_PROGRESS:
                mTextView.setText("当前TimerID为" + msg.arg1 );
                break;
            case CLOSE_PROGRESS:
                mTextView.setText("当前Timer已经关闭请重新开启" );
                break;
            
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_timer);

        Button btnStart = (Button) findViewById(R.id.btnStart);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        mTextView = (TextView) findViewById(R.id.tvShow1);
        mTextView.setText("点击按钮开始更新时间");
        
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                StartTimer();
            }
        });

        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //停止执行timer
                CloseTimer();
            }
        });
        
        super.onCreate(savedInstanceState);
    }

    public void StartTimer() {
        if (timer == null) {
            timerTask = new TimerTask() {
                public void run() {
                	timerId ++;
                    Message msg = new Message();
                    msg.what = LOAD_PROGRESS;
                    msg.arg1 = (int) (timerId);
                    handler.sendMessage(msg);
                }
            };
            
            timer = new Timer();
          
            timer.schedule(timerTask, 1000, 1000);
        }

    }

    public void CloseTimer() {
        if (timer != null) {
        	timer.cancel();
        	timer = null;
        }
        if (timerTask != null) {
        	timerTask = null;
        }
        
        timerId = 0;
        
        handler.sendEmptyMessage(CLOSE_PROGRESS);
    }
}
