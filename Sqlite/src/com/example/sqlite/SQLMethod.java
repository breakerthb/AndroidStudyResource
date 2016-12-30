package com.example.sqlite;

import com.example.sqlite.db.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLMethod {
	private String dbName = null;
	private String dbTableName = null;
	private Context context;
	
	public SQLMethod(Context context, String dbName) {
		this.context = context;
		this.dbName = dbName;
		
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		db.close();
	}


	void InsertDB(String id, String name) {
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		String sql = "insert into " + dbTableName + " (" + id + "," + name
				+ ") values('" + id + "','" + name + "')";
		
		db.execSQL(sql);
	}

	String QueryDB() {
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		String sql = "select * from " + dbTableName;
		Cursor cursor = dbHelper.query(db, sql);
		String text = "";
		while (cursor.moveToNext()) {
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				text += cursor.getString(i);
			}
			text += "\n";
		}

		return text;
	}
	
	void UpdateDB(String id, String name) {
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		String sql = "update " + dbTableName + " set name ='" + name
				+ ".' where id =" + id;
		
		db.execSQL(sql);
	}

	void DeleteDB(int id)
	{
		DBHelper dbHelper = new DBHelper(context, dbName);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		String sql = "delete from Test where id = " + id;
		
		db.execSQL(sql);
	}
}
