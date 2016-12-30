package com.example.controllistview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SimpleListView extends ListActivity {
    private String[] list = {"姓名：MOMO","性别：男","年龄：25","居住地：北京","邮箱：HelloWorld@gmail.com"};
    ListView mListView = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	mListView = getListView();
	
	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	
	mListView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Toast.makeText(SimpleListView.this, "您选择了" + list[position], Toast.LENGTH_LONG).show();
	    }
	});

	super.onCreate(savedInstanceState);
    }
}
