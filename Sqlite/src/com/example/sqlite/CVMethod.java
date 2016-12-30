package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqlite.db.DBHelper;

public class CVMethod {
	
	Context context = null;
	String dbName = "";
	
	public CVMethod(Context context, String dbName)
	{
		this.context = context;
		this.dbName = dbName;
		
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.close();
	}

	public void insert(int id, String name) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("name", name);
		
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.insert("Test", null, values);
		db.close();
	}
	
	public void update(int id, String name){
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("name", name);
		
		db.update("Test", values, "id=?", new String[]{id + ""});
		
		db.close();
	}

	public void query(int id) {
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("Test", new String[]{"id","name"}, "id=?", new String[]{id + ""}, null, null, null);
		while (cursor.moveToNext())
		{
			String name = cursor.getString(cursor.getColumnIndex("name"));
			Log.d("debug", "query-->" + name);
		}
		
		cursor.close();
		db.close();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete("Test", "id=?", new String[]{id + ""} );
		db.close();
	}
	
}
