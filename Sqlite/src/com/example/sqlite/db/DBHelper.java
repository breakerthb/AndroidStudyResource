package com.example.sqlite.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final int VERSION =1;
	
	private String TAG = "debug";
	private String SQL_TABLE_DEF = "create table Test(id int, name varchar(20))";
	
	//当调用getWritableDatabase() 
    //或 getReadableDatabase()方法时创建一个数据库
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBHelper(Context context, String name, int version){
		this(context, name, null, version);
	}

	public DBHelper(Context context, String name){
		this(context, name, VERSION);
	}
	

	/**
	 * Create a new table
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d(TAG, "Create a default table...");
		db.execSQL(SQL_TABLE_DEF);
	}

	/**
	 * 	when version change
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.d(TAG, "Upgrade the database...");
	}
	
	
	public Cursor query(SQLiteDatabase db, String sql) {
		return db.rawQuery(sql, null);
	}
	
	public void exec(SQLiteDatabase db, String sql) {
		db.execSQL(sql);
	}

}
