package com.example.thread.demo;


import com.example.thread.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SingleActivity extends Activity {

    public final static int LOAD_PROGRESS = 0; 
    public final static int LOAD_COMPLETE = 1; 

    TextView textView = null;
    
    //���մ��ݹ�������Ϣ
    @SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
    	@Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case LOAD_PROGRESS:
            	textView.setText("��ǰ���ڵ�" + msg.arg1 + "��ѭ��");
                break;
            case LOAD_COMPLETE:
            	textView.setText("һ����ʱ" + msg.arg1 + "����");
                break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_single);

        textView = (TextView) findViewById(R.id.tvShow);
        textView.setText("�����ť��ʼ����ʱ��");
        
        Button btn = (Button) findViewById(R.id.btnStop);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
            	new Thread() {
                    @Override
                    public void run() {
                        long mLoadStatr = System.currentTimeMillis();
                        
                        for (int i = 0; i < 50; i++) {
                        	System.out.println("out3 : " + i);
                        	
                        	try {
        						sleep(1000);
        					} catch (InterruptedException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
                        	
                            Message msg = new Message();
                            msg.what = LOAD_PROGRESS;
                            msg.arg1 = i + 1;
                            handler.sendMessage(msg);
                        }
                        
                        long mLoadEnd = System.currentTimeMillis();
                        
                        Message msg = new Message();
                        msg.what = LOAD_COMPLETE;
                        msg.arg1 = (int) (mLoadEnd - mLoadStatr);
                        handler.sendMessage(msg);
                    }
                }.start();
            }
        });
        
        super.onCreate(savedInstanceState);
    }

}