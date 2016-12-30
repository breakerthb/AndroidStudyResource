package com.cking.smsinterception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class QueryDataSaveService extends Service{
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//deleteFile ("locsms_file");
		
		Log.d("debug", "QueryDataSaveService onCreate...");

	}
	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("debug", "QueryDataSaveService onStartCommand...");
		
		String con = intent.getStringExtra("message");	
		String filepath="/data/data/com.cking.smsinterception/files/";
		String FILENAME = "query_sms.txt";
		String content=con+"\n";
		final int NUM=10;														//���ݵ�������
		
/*���������������������������������������������ļ��������Ƿ���100������������������������������������������������������������������������������������*/
		int count = 0;
		InputStream input;
		try {
			input = openFileInput(FILENAME);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));

			String value = br.readLine();
			if(value != null)
			{

					while(value !=null){
					  count++;
					  value = br.readLine();
					}
			
					br.close();
					input.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
/*���������������������������������������������µ����ݡ���������������������������������������������������������������������������������*/
						
	if(count<NUM)					//�����û100����ֱ�Ӳ���
		{
				FileOutputStream os01;
				try {
					os01 = openFileOutput(FILENAME, Context.MODE_WORLD_READABLE+Context.MODE_APPEND+Context.MODE_WORLD_WRITEABLE);
					os01.write(content.getBytes());
					os01.close();
					System.out.println("write done!");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
	else							//������ڵ���100������ˢ�£��ڲ���
		{
					List<String> ls=new ArrayList<String> ();
					//���ļ�����������еĶ��뵽LIST��
					try{      		
						
			        	System.out.println("begin read data from file!");
						FileReader fr = new FileReader(filepath + FILENAME);
						BufferedReader br=new BufferedReader(fr);
						String Line=br.readLine();
					
						while(Line!=null){ 
							ls.add(Line);
							Line=br.readLine();
						} 
						br.close();
						fr.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						
						e.printStackTrace();
					} 
					
					
							ls.remove(0);			//ɾ����ɵ�һ�����ݣ�����������
					try{
							FileOutputStream os02=openFileOutput("query_sms.txt", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE); 
					        Iterator<String> it=ls.iterator();
					        while(it.hasNext())
					        {
					        	String str=it.next()+"\n";
					        	byte[] bu= str.getBytes();
					        	os02.write(bu);
					        }
					        os02.write(content.getBytes());  
					        os02.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		}
	
	
/*����������������������������������������������ѯӦ�ó������������������������������������������������������������������������������������*/
		//����ǰ��������
		ComponentName com=new ComponentName("com.cking.querysms","com.cking.querysms.SearchSmsService");
		intent.setComponent(com);		
		QueryDataSaveService.this.startService(intent);
		
		stopSelf();
		return START_REDELIVER_INTENT ;
}
	
		
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}


