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
    private String[] list = {"������MOMO","�Ա���","���䣺25","��ס�أ�����","���䣺HelloWorld@gmail.com"};
    ListView mListView = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	mListView = getListView();
	
	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	
	mListView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Toast.makeText(SimpleListView.this, "��ѡ����" + list[position], Toast.LENGTH_LONG).show();
	    }
	});

	super.onCreate(savedInstanceState);
    }
}
