package com.example.simulationweixinui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChatActivity extends Activity {

	private Button sendButton = null;
	private EditText contentEditText = null;
	private ListView chatListView = null;
	private List<ChatEntity> chatList = null;
	private ChatAdapter chatAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat);

		contentEditText = (EditText) this.findViewById(R.id.et_content);
		sendButton = (Button) this.findViewById(R.id.btn_send);
		chatListView = (ListView) this.findViewById(R.id.listview);
		chatList = new ArrayList<ChatEntity>();
		ChatEntity chatEntity = null;
		for (int i = 0; i < 2; i++) {
			chatEntity = new ChatEntity();
			if (i % 2 == 0) {
				chatEntity.setComeMsg(false);
				chatEntity.setContent("Hello");
				chatEntity.setChatTime("2012-09-20 15:12:32");
			} else {
				chatEntity.setComeMsg(true);
				chatEntity.setContent("Hello,nice to meet you!");
				chatEntity.setChatTime("2012-09-20 15:13:32");
			}
			chatList.add(chatEntity);
		}
		
		chatAdapter = new ChatAdapter(this, chatList);
		
		chatListView.setAdapter(chatAdapter);
		sendButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!contentEditText.getText().toString().equals("")) {
					// ·¢ËÍÏûÏ¢
					send();
				} else {
					Toast.makeText(ChatActivity.this, "Content is empty",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void send() {
		ChatEntity chatEntity = new ChatEntity();
		chatEntity.setChatTime("2012-09-20 15:16:34");
		chatEntity.setContent(contentEditText.getText().toString());
		chatEntity.setComeMsg(false);
		chatList.add(chatEntity);
		((BaseAdapter) chatAdapter).notifyDataSetChanged();
		chatListView.setSelection(chatList.size() - 1);
		contentEditText.setText("");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}

}
