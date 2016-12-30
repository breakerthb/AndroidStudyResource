package com.cking.smsinterception;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

public class ParametersSetService extends Service {
	String name = "navigation_setting";

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Log.d("debug", "ParametersSetService onStartCommand...");

		if (intent.hasExtra("callNumber03")
				&& intent.getStringExtra("callNumber03") != null)// ���ú������ĺ���
		{ // (CMD,TN4,4001156666)
			String callnum03 = intent.getStringExtra("callNumber03");
			// String fileName="navigation_setting";

			System.out.println("����");
			SharedPreferences settings03 = getSharedPreferences(name,
					Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
			SharedPreferences.Editor edit03 = settings03.edit();
			System.out.println("the num is "
					+ settings03.getString("callcennum", "0000"));
			edit03.putString("callcennum", callnum03);
			edit03.commit();

			// ���� ���ú������ĺ���Ļظ�����
			String serverSmsNum03 = settings03.getString("smscennum",
					"15220110571");// (FNS,TN4,4001156666)
			String con03 = "FNS,TN4,"
					+ settings03.getString("callcennum", null);
			SmsManager smsMgr03 = SmsManager.getDefault();
			smsMgr03.sendTextMessage(serverSmsNum03, null, con03, null, null);

			return START_REDELIVER_INTENT;

		} else if (intent.hasExtra("QuePraReq")) // ��̨��ѯ��������
		{ // (CMD,QTN)

			SharedPreferences settings04 = getSharedPreferences(name,
					Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
			String callphone04 = settings04.getString("callcennum", null);
			String servertype04 = settings04.getString("servertyepe", null);
			String serverSmsNum04 = settings04.getString("smscennum", null);

			String con04 = "FNS,TNS," + callphone04 + ",0000," + servertype04
					+ ",00,0,0,00";// �ظ���ѯ
			SmsManager smsMgr04 = SmsManager.getDefault(); // (FNS,TNS,4001156666,0000,02,00,0,0,00)
			smsMgr04.sendTextMessage(serverSmsNum04, null, con04, null, null);

			return START_REDELIVER_INTENT;
		} else if (intent.hasExtra("callNumber05")
				&& intent.hasExtra("servertype05")) // �������ظ�ע��
		{ // (CMD,TNS,4001156666,0000,02,00,0,0,00)

			String callphone05 = intent.getStringExtra("callNumber05");
			String servertype05 = intent.getStringExtra("servertype05");

			SharedPreferences settings05 = getSharedPreferences(name,
					Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
			SharedPreferences.Editor edit05 = settings05.edit();
			String serverSmsNum05 = settings05.getString("smscennum",
					"15220110571");
			edit05.putString("callcennum", callphone05);
			edit05.putString("servertyepe", servertype05);
			edit05.putBoolean("registerSwitch", true);
			edit05.commit();

			// ��Ҫ��OKIS����
			Context context;
			try {
				context = createPackageContext("com.cking.navigationsetting",
						Context.CONTEXT_IGNORE_SECURITY);
				FileOutputStream stream = context.openFileOutput("okis.txt",
						Context.MODE_WORLD_READABLE
								+ Context.MODE_WORLD_WRITEABLE);
				String str = "shi";
				byte[] bu = str.getBytes();
				stream.write(bu);
				stream.close();
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// �ն�ȷ��ע��ظ�
			// (FNS,TNS,4001156666,0000,02,00,0,0,00)
			String con05 = "FNS,TNS," + callphone05 + ",0000," + servertype05
					+ ",00,0,0,00";
			SmsManager smsMgr05 = SmsManager.getDefault();
			smsMgr05.sendTextMessage(serverSmsNum05, null, con05, null, null);

			return START_REDELIVER_INTENT;

		} else if (intent.hasExtra("modifyservertype")
				&& intent.getStringExtra("modifyservertype") != null) // �޸ķ�������
		{ // (CMD,TN3,02)
			String servertype06 = intent.getStringExtra("modifyservertype");

			SharedPreferences settings06 = getSharedPreferences(name,
					Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
			SharedPreferences.Editor edit06 = settings06.edit();
			edit06.putString("servertype", servertype06);
			edit06.commit();

			String serverSmsNum06 = settings06.getString("smscennum",
					"15220110571");// �޸ĳɹ��ظ�ȷ��
			String con06 = "FNS,TN3," + servertype06; // (FNS,TN3,02)
			SmsManager smsMgr06 = SmsManager.getDefault();
			smsMgr06.sendTextMessage(serverSmsNum06, null, con06, null, null);

			return START_REDELIVER_INTENT;

		} else if (intent.hasExtra("cancelserver")) // ȡ��ע��ͷ���
		{ // (CMD,TNC)

			SharedPreferences settings07 = getSharedPreferences(name,
					Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
			SharedPreferences.Editor edit07 = settings07.edit();
			String serverSmsNum07 = settings07.getString("smscennum",
					"15220110571");

			edit07.putBoolean("registerSwitch", false);
			edit07.putString("callcennum", "4001156666");
			edit07.putString("smscennum", "15220110571");
			edit07.putString("servertype", "00");
			edit07.commit();

			// �ն˻ظ�ע���ɹ�
			String con07 = "FNS,TNC"; // (FNS,TNC)
			SmsManager smsMgr07 = SmsManager.getDefault();
			smsMgr07.sendTextMessage(serverSmsNum07, null, con07, null, null);

			return START_REDELIVER_INTENT;

		} else if (intent.hasExtra("smscennum08")
				&& intent.getStringExtra("smscennum08") != null)// ���ö������ĺ���
		{ // (CMD,007,007,15220110571,0)
			String smscennum08 = intent.getStringExtra("smscennum08");

			SharedPreferences settings08 = getSharedPreferences(name,
					Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
			SharedPreferences.Editor edit08 = settings08.edit();
			edit08.putString("smscennum", smscennum08);
			edit08.commit();
			// �ն˻ظ����ö������ĺ���ɹ�
			// (FNS,007,007,15220110571,0)
			String con08 = "FNS,007,007,"
					+ settings08.getString("smscennum", null) + ",0";
			SmsManager smsMgr08 = SmsManager.getDefault();
			smsMgr08.sendTextMessage(smscennum08, null, con08, null, null);

			return START_REDELIVER_INTENT;

		}

		return super.onStartCommand(intent, flags, startId);

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
