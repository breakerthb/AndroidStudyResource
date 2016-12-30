package com.notebook.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.notebook.model.Note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBCtrl {
	Context context = null;
	String dbName = "";
	String tableName = "";

	private DBHelper dbHelper = null;
	private SQLiteDatabase db = null;

	public DBCtrl(Context context, String dbName, String tableName) {
		this.context = context;
		this.dbName = dbName;
		this.tableName = tableName;

		dbHelper = new DBHelper(context, dbName);
		// SQLiteDatabase db = dbHelper.getReadableDatabase();
		// db.close();
	}

	public void open() {
		open(true);
	}

	public void open(boolean readonly) {
		if (readonly) {
			db = dbHelper.getReadableDatabase();
		} else {
			db = dbHelper.getWritableDatabase();
		}
	}

	public void close() {
		if (db != null) {
			db.close();
		}

		if (dbHelper != null) {
			dbHelper.close();
		}
	}

	public void insert(Note note) {
		ContentValues values = new ContentValues();
		values.put("title", note.getTitle());
		values.put("content", note.getContent());
		values.put("state", 1);

		db.insert(tableName, null, values);
	}

	public void update(Note note) {
		ContentValues values = new ContentValues();

		values.put("title", note.getTitle());
		values.put("content", note.getContent());

		db.update(tableName, values, "id=?", new String[] { note.getId() + "" });
	}

	public List<Note> selectAll() {
		List<Note> list = new ArrayList<Note>();

		Cursor cursor = db.query(tableName, new String[] { "id", "title",
				"content", "time" }, "state=?", new String[] { "1" }, null,
				null, null);

		while (cursor.moveToNext()) {
			Note note = new Note();
			note.setId(cursor.getInt(cursor.getColumnIndex("id")));
			note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			note.setContent(cursor.getString(cursor.getColumnIndex("content")));
			note.setTime(cursor.getString(cursor.getColumnIndex("time")));

			list.add(note);
		}

		cursor.close();

		return list;
	}

	public List<Note> selectALlDustbin() {
		List<Note> list = new ArrayList<Note>();

		Cursor cursor = db.query(tableName, new String[] { "id", "title",
				"content", "time" }, "state=?", new String[] { "0" }, null,
				null, null);

		while (cursor.moveToNext()) {
			Note note = new Note();
			note.setId(cursor.getInt(cursor.getColumnIndex("id")));
			note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			note.setContent(cursor.getString(cursor.getColumnIndex("content")));
			note.setTime(cursor.getString(cursor.getColumnIndex("time")));

			list.add(note);
		}

		cursor.close();

		return list;
	}

	public Note query(int id) {
		Note note = new Note();

		note.setId(id);

		Cursor cursor = db.query(tableName, new String[] { "title", "content",
				"time" }, "id=?", new String[] { id + "" }, null, null, null);
		while (cursor.moveToNext()) {
			note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			note.setContent(cursor.getString(cursor.getColumnIndex("content")));
			note.setTime(cursor.getString(cursor.getColumnIndex("time")));
		}

		cursor.close();

		return note;
	}

	public void deleteSham(int id) {
		ContentValues values = new ContentValues();

		values.put("state", 0);

		db.update(tableName, values, "id=?", new String[] { id + "" });
	}
	
	public void recover(int id) {
		ContentValues values = new ContentValues();

		values.put("state", 1);

		db.update(tableName, values, "id=?", new String[] { id + "" });
	}
	
	public void delete(int id) {
		db.delete(tableName, "id=?", new String[] { id + "" });
	}

	// //////////////////////////////////////////////////////////////////////////////////
	// SQL
	public Cursor query(String sql) {
		return db.rawQuery(sql, null);
	}

	public void exec(String sql) {
		db.execSQL(sql);
	}
}
