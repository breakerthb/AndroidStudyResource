package com.notebook.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final int VERSION =1;
	
	private String TAG = "debug";
	
	// state : 0, have been delete 1, display
	private String SQL_TABLE_DEF = "create table Notes(id integer primary key autoincrement, title varchar(100), content text ,time timestamp DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime')), state int)";
	
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

	public Cursor opendb(SQLiteDatabase db, String sql) {
		return db.rawQuery(sql, null);
	}

}
