package com.example.sqlite;

import com.example.sqlite.db.DBHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SQLiteActivity extends Activity {

	private String dbName = "TestDB";
	
	private SQLMethod sqlMethod = null;
	private CVMethod cvMethod = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnCreateDB = (Button) findViewById(R.id.btnCreateDB);
		Button btnUpdateDB = (Button) findViewById(R.id.btnUpdateDB);
		
		Button btnSQLInsert = (Button) findViewById(R.id.btnSQLInsert);
		Button btnSQLUpdate = (Button) findViewById(R.id.btnSQLUpdate);
		Button btnSQLQuery = (Button) findViewById(R.id.btnSQLQuery);
		Button btnSQLDelete = (Button) findViewById(R.id.btnSQLDelete);

		Button btnInsert = (Button) findViewById(R.id.btnInsert);
		Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
		Button btnQuery = (Button) findViewById(R.id.btnQuery);
		Button btnDelete = (Button) findViewById(R.id.btnDelete);
		
		btnCreateDB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sqlMethod = new SQLMethod(SQLiteActivity.this, dbName);
				
				cvMethod = new CVMethod(SQLiteActivity.this, dbName);
			}
		});
		
		btnUpdateDB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				DBHelper dbHelper = new DBHelper(SQLiteActivity.this, "TestDB", 2);
				SQLiteDatabase db = dbHelper.getReadableDatabase();
			}
		});
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// SQL Method
		btnSQLInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sqlMethod.InsertDB("1", "Hello");
			}
		});
		
		btnSQLUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sqlMethod.UpdateDB("1", "Hello");
			}
		});
		
		btnSQLQuery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sqlMethod.QueryDB();
			}
		});
		
		btnSQLDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sqlMethod.DeleteDB(1);
			}
		});
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Content Value Method
		// Insert
		btnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cvMethod == null)
				{
					Toast.makeText(SQLiteActivity.this, "Please Create Database", Toast.LENGTH_SHORT).show();
					
					return;
				}
				
				cvMethod.insert(1, "Jim");
			}
		});
		
		// Update
		btnUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cvMethod.update(1, "Jack");
			}
		});
		
		btnQuery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cvMethod.query(1);
			}
		});
		
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cvMethod.delete(1);
			}
		});
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	}

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
