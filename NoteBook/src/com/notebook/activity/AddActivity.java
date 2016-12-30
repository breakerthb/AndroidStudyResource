package com.notebook.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.notebook.R;
import com.notebook.model.Note;
import com.notebook.sqlite.DBCtrl;
import com.notebook.util.INI;

public class AddActivity extends Activity {

	private EditText etTitle = null;
	private EditText etContent = null;
	private DBCtrl dbCtrl = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		setTitle("ÐÂ±Ê¼Ç");
		
		dbCtrl = new DBCtrl(getApplicationContext(), INI.DB_NAME, INI.DB_TABLE_NAME);
		
		etTitle  = (EditText) findViewById(R.id.etTitle);
		etContent = (EditText) findViewById(R.id.etContent);
		
		Button btnAdd = (Button) findViewById(R.id.btnSave);
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Note note = new Note(etTitle.getText().toString(), etContent.getText().toString());
				
				dbCtrl.open(false);
				dbCtrl.insert(note);
				
				finish();
			}
		});
		
		Button btnCancel = (Button) findViewById(R.id.btnCancel);
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

	private void init() {
		etTitle.setText("");
		etContent.setText("");
	}
}
