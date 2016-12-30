package com.example.contentprovider;

import java.util.HashMap;

import com.example.contentprovider.FirstProviderMetaData.UserTableMetaData;
import com.example.sqlite.db.DatabaseHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class FirstContentProvider extends ContentProvider {

	public static final UriMatcher uriMatcher;
	public static final int INCOMING_USER_COLLECTION = 1;
	public static final int INCOMING_USER_SINGLE = 2;
	private DatabaseHelper dh;
	static{
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(FirstProviderMetaData.AUTHORIY, "users", INCOMING_USER_COLLECTION);
		uriMatcher.addURI(FirstProviderMetaData.AUTHORIY, "users/#", INCOMING_USER_SINGLE);
		}
	public static HashMap<String, String> userProjectionMap;
	static{
		userProjectionMap = new HashMap<String, String>();
		userProjectionMap.put(UserTableMetaData._ID, UserTableMetaData._ID);
		userProjectionMap.put(UserTableMetaData.USER_NAME, UserTableMetaData.USER_NAME);
	}
	
	public FirstContentProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dh = new DatabaseHelper(getContext(), FirstProviderMetaData.DATABASE_NAME);
		System.out.println("onCreate");
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		System.out.println("getType");
		switch(uriMatcher.match(uri))
		{
		case INCOMING_USER_COLLECTION:
			return UserTableMetaData.CONTENT_TYPE;
		case INCOMING_USER_SINGLE:
			return UserTableMetaData.CONTENT_TYPE_ITEM;
		default:
			throw new IllegalArgumentException("Unknown URI" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		System.out.println("insert");
		SQLiteDatabase db = dh.getWritableDatabase();
		long rowId = db.insert(UserTableMetaData.TABLE_NAME, null, values);
		if (rowId > 0)
		{
			Uri insertedUserUri = ContentUris.withAppendedId(UserTableMetaData.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(insertedUserUri, null);
			return insertedUserUri;
		}
		throw new SQLException("Failed to insert row into" + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		System.out.println("delete");
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
