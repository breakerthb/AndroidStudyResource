package com.notebook.activity;

import java.util.ArrayList;
import java.util.List;

import com.notebook.model.Note;
import com.notebook.sqlite.DBCtrl;
import com.notebook.util.INI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DustbinActivity extends ListActivity {

	private List<Note> listInfo = null;
	private List<String> listShow = null;

	private DBCtrl dbCtrl = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dbCtrl = new DBCtrl(getApplicationContext(), INI.DB_NAME, INI.DB_TABLE_NAME);
		setTitle("������");
		
		initList();

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listShow));

		ListView mListView = getListView();
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {

				final int dbID = listInfo.get(position).getId();
				
				AlertDialog.Builder builder = new Builder(DustbinActivity.this);
				builder.setIcon(android.R.drawable.btn_star).setTitle("����").setMessage("��Ҫ��ʲô��");
				builder.setNegativeButton("�ָ�", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dbCtrl.open(false);
								dbCtrl.recover(dbID);
								
								onResume();

							}
						}).setNeutralButton("ɾ��", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dbCtrl.open(false);
								dbCtrl.delete(dbID);
								
								onResume();
							}
						}).setPositiveButton("ȡ��", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								// Do nothing
							}
						});
				
				Dialog dialog = builder.create();

				dialog.show();
			}
		});

		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		dbCtrl.close();
	}
	
	private void initList() {
		dbCtrl.open();
		
		listInfo = dbCtrl.selectALlDustbin();

		listShow = new ArrayList<String>();

		for (int i = 0; i < listInfo.size(); i++) {
			StringBuilder sb = new StringBuilder();
			Note note = listInfo.get(i);

			sb.append(note.getTitle());
			sb.append("--");
			sb.append(note.getTime());

			listShow.add(sb.toString());
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		initList();
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listShow));
		
		super.onResume();
	}
	
}
