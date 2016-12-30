package com.example.telphonenumbook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnGetNumber = (Button) findViewById(R.id.btnGet);
		btnGetNumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, ListDisplay.class);
				startActivity(intent);
			}
		});

		Button btnInsert = (Button) findViewById(R.id.btnInsert);
		btnInsert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues values = new ContentValues();
				// 首先向RawContacts.CONTENT_URI执行一个空值插入，目的是获取系统返回的rawContactId
				Uri rawContactUri = getContentResolver()
						.insert(RawContacts.CONTENT_URI, values);
				long rawContactId = ContentUris.parseId(rawContactUri);
				
				// 往data表入姓名数据
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);// 内容类型
				values.put(StructuredName.GIVEN_NAME, "李天山");
				
				getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
				
				// 往data表入电话数据
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
				values.put(Phone.NUMBER, "13921009789");
				values.put(Phone.TYPE, Phone.TYPE_MOBILE);
		
				getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI,	values);
				
				// 往data表入Email数据
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
				values.put(Email.DATA, "liming@itcast.cn");
				values.put(Email.TYPE, Email.TYPE_WORK);

				getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI,	values);
			}
		});
		
		Button btnBatInsert = (Button) findViewById(R.id.btnBatInsert);
		btnBatInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 //文档位置：reference\android\provider\ContactsContract.RawContacts.html
		        ArrayList<ContentProviderOperation>ops = new ArrayList<ContentProviderOperation>();
		        int rawContactInsertIndex = ops.size();
		        ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
		                .withValue(RawContacts.ACCOUNT_TYPE,null)
		                .withValue(RawContacts.ACCOUNT_NAME,null)
		                .build());
		        //文档位置：reference\android\provider\ContactsContract.Data.html
		        ops.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI)
		                .withValueBackReference(Data.RAW_CONTACT_ID,rawContactInsertIndex)
		                .withValue(Data.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE)
		                .withValue(StructuredName.GIVEN_NAME,"赵薇")
		                .build());
		        ops.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI)
		                 .withValueBackReference(Data.RAW_CONTACT_ID,rawContactInsertIndex)
		                 .withValue(Data.MIMETYPE,Phone.CONTENT_ITEM_TYPE)
		                 .withValue(Phone.NUMBER,"13671323809")
		                 .withValue(Phone.TYPE,Phone.TYPE_MOBILE)
		                 .withValue(Phone.LABEL, "手机号")
		                 .build());
		        ops.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI)
		                 .withValueBackReference(Data.RAW_CONTACT_ID,rawContactInsertIndex)
		                 .withValue(Data.MIMETYPE,Email.CONTENT_ITEM_TYPE)
		                 .withValue(Email.DATA,"liming@itcast.cn")
		                 .withValue(Email.TYPE,Email.TYPE_WORK)
		                 .build());
		        ContentProviderResult[] results;
		        
				try {
					results = getContentResolver().applyBatch(ContactsContract.AUTHORITY,ops);
					
			        for(ContentProviderResult result : results){
			            Log.i("debug", result.uri.toString());
			        }
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OperationApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	 //获取所有联系人
    public void testContacts() throws Exception{
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        ContentResolver resolver = getContentResolver();
        
        //查询raw_contacts._id
        Cursor cursor = resolver.query(uri, new String[]{"_id"}, null, null, null);
        while(cursor.moveToNext()){
            int contactid = cursor.getInt(0);
            StringBuilder sb = new StringBuilder("contactid=");
            sb.append(contactid);
            //content://com.android.contacts/contacts/#/data
            uri= Uri.parse("content://com.android.contacts/contacts/"+contactid+ "/data");
            //查询data表中相关的字段
            Cursor datacursor = resolver.query(uri, new String[]{"mimetype","data1","data2"}, null, null,null);
            while(datacursor.moveToNext()){
                String data = datacursor.getString(datacursor.getColumnIndex("data1"));
                String type = datacursor.getString(datacursor.getColumnIndex("mimetype"));
                if("vnd.android.cursor.item/name".equals(type)){//姓名
                    sb.append(",name="+data);
                }else if("vnd.android.cursor.item/email_v2".equals(type)){//email
                    sb.append(",email="+data);
                }else if("vnd.android.cursor.item/phone_v2".equals(type)){//phone
                    sb.append(",phone="+data);
                }
            }
            Log.i("debug", sb.toString());
        }
    }
    
    //根据号码获取联系人的姓名
    public void testContactNameByNumber() throws Exception{
        String number = "18601025011";
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + number);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"display_name"}, null, null, null);
        if(cursor.moveToFirst()){
            String name = cursor.getString(0);
            Log.i("debug",name);
        }
        cursor.close();
    }
    
    //添加联系人。缺点：由多个操作完成，无法保证全部都成功
    public void testAddContact() throws Exception{
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
       long contactid =ContentUris.parseId(resolver.insert(uri, values));
        //添加姓名
        uri= Uri.parse("content://com.android.contacts/data");
        values.put("raw_contact_id",contactid);
        values.put("mimetype","vnd.android.cursor.item/name");
        values.put("data2","张小小");
        resolver.insert(uri,values);
        
        //添加电话
        values.clear();
        values.put("raw_contact_id",contactid);
        values.put("mimetype","vnd.android.cursor.item/phone_v2");
        values.put("data2","2");
        values.put("data1","13671323507");
        resolver.insert(uri,values);
        
        //添加Email
        values.clear();
        values.put("raw_contact_id",contactid);
        values.put("mimetype","vnd.android.cursor.item/email_v2");
        values.put("data2","2");
        values.put("data1","zhangxx@csdn.net");
        resolver.insert(uri,values);
    }
    
    //在同一个事务中完成联系人各项数据的添加
    public void testAddContact2() throws Exception{
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
            .withValue("account_name",null) //account_name专门用于存放google的登录帐号
            .build();
        operations.add(op1);
        
 
       //注意：因为是在一个事务中完成的，所以不知道raw_contact_id的值，
       //     因此在设置raw_contact_id时使用withValueBackReference
       //     .withValueBackReference("raw_contact_id",0)的含义是使用第一个操作对象添加完成后所返回的记录ID作为字段的值
        uri= Uri.parse("content://com.android.contacts/data");
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
           .withValueBackReference("raw_contact_id",0)
            .withValue("mimetype","vnd.android.cursor.item/name")
            .withValue("data2","李小龙")
            .build();
        operations.add(op2);
        
        ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri)
           .withValueBackReference("raw_contact_id",0)
            .withValue("mimetype","vnd.android.cursor.item/phone_v2")
            .withValue("data1","13560650505")
            .withValue("data2","2")
            .build();
        operations.add(op3);
        
        ContentProviderOperation op4 = ContentProviderOperation.newInsert(uri)
           .withValueBackReference("raw_contact_id",0)
            .withValue("mimetype","vnd.android.cursor.item/email_v2")
            .withValue("data1","liming@sohu.com")
            .withValue("data2","2")
            .build();
        operations.add(op4);
        
        resolver.applyBatch("com.android.contacts",operations);
    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
