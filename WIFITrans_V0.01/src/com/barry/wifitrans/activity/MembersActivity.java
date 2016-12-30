package com.barry.wifitrans.activity;

import java.io.File;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.barry.wifitrans.net.NetManager;
import com.barry.wifitrans.util.ExDialog;
import com.barry.wifitrans.util.MyApplication;
import com.example.broadcast.R;

public class MembersActivity extends ListActivity {
	
	private MyApplication myApplication = null;
	
	private final int RET_FILE_PATH = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		myApplication = (MyApplication) getApplicationContext();
		
		//setListAdapter
		MyArrayAdaper adapter = new MyArrayAdaper(this, R.layout.activity_members);
		this.setListAdapter(adapter);
		
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String ip = myApplication.listMembers.get(position).getIp();
				String name = myApplication.listMembers.get(position).getName();
				
				myApplication.setToIp(ip);
				
				Intent intent = new Intent();
				intent.putExtra("explorer_title", "Search File");
				intent.setDataAndType(Uri.fromFile(new File("/sdcard")), "*/*");
				intent.setClass(MembersActivity.this, ExDialog.class);
				startActivityForResult(intent, RET_FILE_PATH);
			}
			
		}); 
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		String path;
		if (resultCode == RESULT_OK) {
			if (requestCode == RET_FILE_PATH) {
				Uri uri = intent.getData();
				String fileName = uri.getPath();
				
				// Send File
				if (NetManager.prepareSendFile(myApplication.getToIp(), fileName)) {
					NetManager.SendFile(fileName);
				} else {
					// Refuse
				}
			}
		}
	}

	class MyArrayAdaper extends ArrayAdapter<Object> {

		Context context = null;
		int resource = 0;
		
		public MyArrayAdaper(Context context, int resource) {
			super(context, resource);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.resource = resource;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(resource, null);
			}
			
			TextView tvIp = (TextView) convertView.findViewById(R.id.tvIP);
			String str = myApplication.listMembers.get(position).getIp();
			tvIp.setText(str);
			
			TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
			str = myApplication.listMembers.get(position).getName();
			tvIp.setText(str);
			
			return convertView;
		}

		
	}
}
