package com.example.socketclient;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatView extends BaseAdapter {

	Context context = null;
	List<ChatInfo> listChat = null;
	
	public ChatView(Context context, List<ChatInfo> listChat){
		this.context = context;
		this.listChat = listChat;
	}
		
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listChat.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listChat.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ChatInfo chatInfo = listChat.get(position);
		
		if (chatInfo.isComeMsg())
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.chat_from_item, null);
		}
		else
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.chat_to_item, null);
		}
		
		TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
		TextView tvMsg = (TextView) convertView.findViewById(R.id.tv_content);
		tvTime.setText(chatInfo.getStrTime());
		tvMsg.setText(chatInfo.getMsg());
		
		return convertView;
	}

	

}
