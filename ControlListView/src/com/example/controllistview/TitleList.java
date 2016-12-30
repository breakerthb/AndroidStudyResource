package com.example.controllistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TitleList extends ListActivity {
    private String[] listTitle = { "姓名", "性别", "年龄", "居住地","邮箱"};
    private String[] list = { "雨松", "男", "25", "北京",
            "xuanyusong@gmail.com" };
    ListView mListView = null;
    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mListView = getListView();
      
        int lengh = listTitle.length;
        for(int i =0; i < lengh; i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("title", listTitle[i]);
            item.put("text", list[i]);
            mData.add(item); 
        }
        
        SimpleAdapter adapter = new SimpleAdapter(this, mData, android.R.layout.simple_list_item_2,
                new String[]{"title","text"},new int[]{android.R.id.text1,android.R.id.text2});
        setListAdapter(adapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(TitleList.this,"您选择了标题：" + listTitle[position] + "内容："+ list[position], 
						Toast.LENGTH_LONG).show();
			}
        });
        super.onCreate(savedInstanceState);
    }
}
