package com.notebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.notebook.R;
import com.notebook.model.Note;
import com.notebook.sqlite.DBCtrl;
import com.notebook.util.INI;

public class DisplayActivity extends Activity {

	private int id;
	
	private TextView tvTitle = null;
	private TextView tvContent = null;
	private TextView tvTime = null;

	private DBCtrl dbCtrl = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diplay);

		setTitle("±Ê¼Ç");
		
		Intent intent = getIntent();
		id = intent.getIntExtra("id", -1);

		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvContent  = (TextView) findViewById(R.id.tvContent);
		tvTime = (TextView) findViewById(R.id.tvTime);
		
		dbCtrl = new DBCtrl(getApplicationContext(), INI.DB_NAME, INI.DB_TABLE_NAME);
		
		init();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		init();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		dbCtrl.close();
	}

	private void init() {
		dbCtrl.open();
		
		Note note = dbCtrl.query(id);
		

		tvTitle.setText(note.getTitle());
		tvContent.setText(note.getContent());
		tvTime.setText(note.getTime());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, R.string.update);
		menu.add(0, 2, 2, R.string.delete);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			Intent intent = new Intent(DisplayActivity.this, UpdateActivity.class);
			intent.putExtra("id", id);
			startActivity(intent);
			break;
		case 2:
			dbCtrl.open(false);
			dbCtrl.deleteSham(id);
			
			finish();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
