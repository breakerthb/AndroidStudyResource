package com.example.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn1 = (Button) findViewById(R.id.Button1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog();
			}
		});
		
		Button btn2 = (Button) findViewById(R.id.Button2);
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new Builder(MainActivity.this);
				builder.setIcon(android.R.drawable.btn_star).setTitle("ϲ�õ���").setMessage("��ϲ�������ܵĵ�Ӱ��");
				builder.setPositiveButton("��ϲ��", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Toast.makeText(MainActivity.this, "�Һ�ϲ�����ĵ�Ӱ��",
										Toast.LENGTH_LONG).show();
							}
						}).setNegativeButton("��ϲ��", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Toast.makeText(MainActivity.this, "�Ҳ�ϲ�����ĵ�Ӱ��",
										Toast.LENGTH_LONG).show();
							}
						}).setNeutralButton("һ��", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Toast.makeText(MainActivity.this, "̸����ϲ����ϲ����",
										Toast.LENGTH_LONG).show();
							}
						});
				
				Dialog dialog = builder.create();

				dialog.show();

			}
		});
		
		Button btn3 = (Button) findViewById(R.id.Button3);
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(MainActivity.this).setTitle("������").setIcon(
					     android.R.drawable.ic_dialog_info).setView(new EditText(MainActivity.this)).setPositiveButton("ȷ��", null)
					     .setNegativeButton("ȡ��", null).show();
			}
		});
		
		Button btn4 = (Button) findViewById(R.id.Button4);
		btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(MainActivity.this).setTitle("��ѡ��").setMultiChoiceItems(
					     new String[] { "Item1", "Item2" }, null, null)
					     .setPositiveButton("ȷ��", null)
					     .setNegativeButton("ȡ��", null).show();
			}
		});
		
		Button btn5 = (Button) findViewById(R.id.Button5);
		btn5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(MainActivity.this).setTitle("��ѡ��").setIcon(
					     android.R.drawable.ic_dialog_info).setSingleChoiceItems(
					     new String[] { "Item1", "Item2" }, 0,
					     new DialogInterface.OnClickListener() {
					      public void onClick(DialogInterface dialog, int which) {
					       dialog.dismiss();
					      }
					     }).setNegativeButton("ȡ��", null).show();
			}
		});
		
		Button btn6 = (Button) findViewById(R.id.Button6);
		btn6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(MainActivity.this).setTitle("�б��").setItems(
					     new String[] { "Item1", "Item2" }, null).setNegativeButton(
					     "ȷ��", null).show();
			}
		});
		
		Button btn7 = (Button) findViewById(R.id.Button7);
		btn7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater = getLayoutInflater();
				   View layout = inflater.inflate(R.layout.dialog,
				     (ViewGroup) findViewById(R.id.dialog));

				   new AlertDialog.Builder(MainActivity.this).setTitle("�Զ��岼��").setView(layout)
				     .setPositiveButton("ȷ��", null)
				     .setNegativeButton("ȡ��", null).show();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dialog();
		}
		return false;
	}

	protected void dialog() {
		AlertDialog.Builder builder = new Builder(this);

		builder.setMessage("ȷ���˳���");
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

				MainActivity.this.finish();
			}
		});

		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();
	}

}
