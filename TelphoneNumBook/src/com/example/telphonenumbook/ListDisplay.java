package com.example.telphonenumbook;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListDisplay extends Activity {

	private ListView myPhoneList;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myPhoneList = new ListView(this);

		ContentResolver phoneContentResolver = this.getContentResolver();

		List<String> myList = new ArrayList<String>();
		Cursor phoneCursor = phoneContentResolver.query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		while (phoneCursor.moveToNext()) {
			String contactId = phoneCursor.getString(phoneCursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			String name = phoneCursor.getString(phoneCursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

			Cursor phones = phoneContentResolver.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
							+ contactId, null, null);

			while (phones.moveToNext()) {
				/*
				 * 这里要注意不可以直接查询所有的电话簿中的号码，而要根据联系人的ID来查去某一个人的号码，
				 * 所以这里通过一个循环来查询上面遍历到的联系人所对应的号码
				 */
				String phoneNumber = phones
						.getString(phones
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				
				myList.add(name + " # " + phoneNumber);
				Log.i("debug", "ID=" + ContactsContract.Contacts._ID
						+ "      Mum: " + phoneNumber);
			}
			startManagingCursor(phones);
			phones.close();

			Cursor emails = getContentResolver().query(
					ContactsContract.CommonDataKinds.Email.CONTENT_URI,
					null,
					ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, 
					null, 
					null);
			
			while (emails.moveToNext()) {
				// This would allow you get several email addresses
				String emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
				Log.i("RongActivity", "emailAddress=" + emailAddress);
			}
			emails.close();
		}

		startManagingCursor(phoneCursor);
		phoneCursor.close();

		Adapter myAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, myList);
		myPhoneList.setAdapter((ListAdapter) myAdapter);
		setContentView(myPhoneList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
