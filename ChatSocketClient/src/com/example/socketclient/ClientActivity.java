package com.example.socketclient;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClientActivity extends Activity {

	private List listChat = new ArrayList<ChatInfo>();
	private ChatView cv =null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client);

		final TextView tvInput = (TextView) findViewById(R.id.et_content);
		
		final ListView lvShow = (ListView) findViewById(R.id.listview);
		
		cv = new ChatView(this, listChat);
		lvShow.setAdapter(cv);
		
		Button btnSend = (Button) findViewById(R.id.btn_send);
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String send = tvInput.getText().toString();
				if (send.isEmpty())
				{
					Toast toast = Toast.makeText( ClientActivity.this, "Input is empty", Toast.LENGTH_SHORT);
					toast.show();

					return;
				}
				tvInput.setText("");
				
				new Thread(new CommunicateThread(listChat, send)).start();
				
				cv.notifyDataSetChanged();
				lvShow.setSelection(listChat.size() - 1);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client, menu);
		return true;
	}

}
