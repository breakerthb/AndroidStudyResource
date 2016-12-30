package com.example.controltest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProBar extends Activity {

	int i = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_probar);
		
		final ProgressBar bar1 = (ProgressBar) findViewById(R.id.ProgressBar1);
		final ProgressBar bar2 = (ProgressBar) findViewById(R.id.progressBar2);
		Button btn = (Button) findViewById(R.id.btnStart);

		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (i == 0){
					bar1.setVisibility(View.VISIBLE);//���ý���������
					//���ý����������ֵ��Ĭ��Ϊ100����ֵҲ���ڲ����ļ�������,
					//������ڲ����ļ��������˴�ֵ�����ڳ����������ˣ���ô���������õ�ֵ�Ḳ�ǲ����ļ������õ�ֵ
					bar1.setMax(150);	
					bar2.setVisibility(View.VISIBLE);//���ý���������
				}else if (i < bar1.getMax()){
					//�������������ĵ�ǰֵ
					bar1.setProgress(i);
					//���ý������ڶ����ȵĵ�ǰֵ
					bar1.setSecondaryProgress(i+10);
					
					//Ĭ�Ͻ������޷���ʾ���е�״̬
					bar2.setProgress(i);
				}else{
					//���ý�����������
					bar1.setVisibility(View.GONE);
					bar2.setVisibility(View.GONE);
				}
				
				i+=10;
			}
		});
	}

}
