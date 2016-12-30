package com.example.thread.demo;

import com.example.thread.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LooperActivity extends Activity {
    Long mLoadStatr = 0L;
    Long mLoadEnd = 0L;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            new Thread() {
                @Override
                public void run() {
                   
                    //如果handler不指定looper的话
                    //默认为mainlooper来进行消息循环，
                    //而当前是在一个新的线程中它没有默认的looper
                    //所以我们须要手动调用prepare()拿到他的loop 
                    //可以理解为在Thread创建Looper的消息队列
                    Looper.prepare();
                    
                    Toast.makeText(LooperActivity.this, "收到消息",Toast.LENGTH_LONG).show();
                    
                    //在这里执行这个消息循环如果没有这句
                    //就好比只创建了Looper的消息队列而
                    //没有执行这个队列那么上面Toast的内容是不会显示出来的
                    Looper.loop();
                
                //如果没有   Looper.prepare();  与 Looper.loop();
                //会抛出异常Can't create handler inside thread that has not called Looper.prepare()   
                //原因是我们新起的线程中是没有默认的looper所以须要手动调用prepare()拿到他的loop
                }
            }.start();
        }
        };
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_loop);

        /** 拿到button 与 TextView 对象 **/
        Button btnSend = (Button) findViewById(R.id.btnSend1);
        btnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new Thread() {
                    @Override
                    public void run() {
                
                            //发送一条空的消息
                            //空消息中必需带一个what字段
                            //用于在handler中接收
                            //这里暂时我先写成0
                            handler.sendEmptyMessage(0);
                
                    }
                }.start();
            }
        });

        
        super.onCreate(savedInstanceState);
    }
}
