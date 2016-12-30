package com.notebook.activity;


import com.example.notebook.R;
import com.notebook.model.Note;
import com.notebook.sqlite.DBCtrl;
import com.notebook.util.INI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends Activity{

	private Note note = null;
	private DBCtrl dbCtrl = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		setTitle("ÐÞ¸Ä");
		
		dbCtrl = new DBCtrl(getApplicationContext(), INI.DB_NAME, INI.DB_TABLE_NAME);
		
		note = new Note();
		
		final EditText etTitle = (EditText) findViewById(R.id.etTitle);
		final EditText etContent = (EditText) findViewById(R.id.etContent);
		
		Intent intent = getIntent();
		int id = intent.getIntExtra("id", -1);
		note.setId(id);
		
		dbCtrl.open();
		final Note note = dbCtrl.query(id);
		
		etTitle.setText(note.getTitle());
		etContent.setText(note.getContent());
		
		Button btnSave = (Button) findViewById(R.id.btnSave);
		Button btnCancel = (Button) findViewById(R.id.btnCancel);
		
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				note.setTitle(etTitle.getText().toString());
				note.setContent(etContent.getText().toString());
				
				dbCtrl.open(false);
				dbCtrl.update(note);
				
				finish();
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		dbCtrl.close();
	}
}
