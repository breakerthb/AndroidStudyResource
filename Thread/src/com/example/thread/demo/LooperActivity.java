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
                   
                    //���handler��ָ��looper�Ļ�
                    //Ĭ��Ϊmainlooper��������Ϣѭ����
                    //����ǰ����һ���µ��߳�����û��Ĭ�ϵ�looper
                    //����������Ҫ�ֶ�����prepare()�õ�����loop 
                    //�������Ϊ��Thread����Looper����Ϣ����
                    Looper.prepare();
                    
                    Toast.makeText(LooperActivity.this, "�յ���Ϣ",Toast.LENGTH_LONG).show();
                    
                    //������ִ�������Ϣѭ�����û�����
                    //�ͺñ�ֻ������Looper����Ϣ���ж�
                    //û��ִ�����������ô����Toast�������ǲ�����ʾ������
                    Looper.loop();
                
                //���û��   Looper.prepare();  �� Looper.loop();
                //���׳��쳣Can't create handler inside thread that has not called Looper.prepare()   
                //ԭ��������������߳�����û��Ĭ�ϵ�looper������Ҫ�ֶ�����prepare()�õ�����loop
                }
            }.start();
        }
        };
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_loop);

        /** �õ�button �� TextView ���� **/
        Button btnSend = (Button) findViewById(R.id.btnSend1);
        btnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new Thread() {
                    @Override
                    public void run() {
                
                            //����һ���յ���Ϣ
                            //����Ϣ�б����һ��what�ֶ�
                            //������handler�н���
                            //������ʱ����д��0
                            handler.sendEmptyMessage(0);
                
                    }
                }.start();
            }
        });

        
        super.onCreate(savedInstanceState);
    }
}
