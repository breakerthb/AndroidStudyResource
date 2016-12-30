package com.example.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class FirstProviderMetaData {

	public static final String AUTHORIY = "com.example.contentprovider.FirstProviderMetaData";
	public static final String DATABASE_NAME = "FirstProvider.db";
	public static final int DATABASE_VERSION = 1;
	public static final String USERS_TABLE_NAME = "users";
	
	public static final class UserTableMetaData implements BaseColumns{
		public static final String TABLE_NAME = "users";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORIY + "/users");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir.vnd.firstprovider.user";
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.firstprovider.user";
		public static final String USER_NAME = "name";
		public static final String DEFAULT_SORT_ORDER = "_id desc";
	}

}
