package com.example.thread.demo;

import com.example.thread.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BasicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);

		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch(msg.what) {
				case 0:
					dialog();
					break;
				default:
					break;
				}
				
			}
			
		};
		
		Button btnThread1 = (Button) findViewById(R.id.btnThread1);
		Button btnThread2 = (Button) findViewById(R.id.btnThread2);
		
		btnThread1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Thread thread = new Thread(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 0; i < 50; i++)
						{
							try {
								if (i == 5) {
									Message msg = new Message();
									msg.what = 0;
									handler.sendMessage(msg);
								}
								
								
								sleep(1000);
								
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Out2 : " + i);
						}
						
						super.run();
					}
					
				};
				thread.start();
			}
		});
		
		btnThread2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for (int i = 0; i < 50; i++)
						{
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Out2 : " + i);
						}
					}
				});
				thread.start();
			}
		});
	}
	
	private void dialog() {
		AlertDialog.Builder builder = new Builder(BasicActivity.this);
		builder.setIcon(android.R.drawable.btn_star).setTitle("ϲ�õ���").setMessage("��ϲ�������ܵĵ�Ӱ��");
		builder.setPositiveButton("��ϲ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						Toast.makeText(BasicActivity.this, "�Һ�ϲ�����ĵ�Ӱ��",
								Toast.LENGTH_LONG).show();
					}
				}).setNegativeButton("��ϲ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						Toast.makeText(BasicActivity.this, "�Ҳ�ϲ�����ĵ�Ӱ��",
								Toast.LENGTH_LONG).show();
					}
				}).setNeutralButton("һ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						Toast.makeText(BasicActivity.this, "̸����ϲ����ϲ����",
								Toast.LENGTH_LONG).show();
					}
				});
		
		Dialog dialog = builder.create();

		dialog.show();
	}
}
