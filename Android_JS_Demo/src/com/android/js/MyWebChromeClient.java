package com.android.js;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;


public class MyWebChromeClient extends WebChromeClient {

	@Override
	public boolean onJsAlert(WebView view, String url, String message,
			JsResult result) {
		// TODO Auto-generated method stub
		final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
		builder.setTitle("Barry Alert Test").setMessage(message).setPositiveButton("ȷ��", null);
		//����Ҫ�󶨰����¼�
		//����keycode����84֮��İ���
		builder.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				Log.v("onJsAlert", "keyCode==" + arg1 + "event=" + arg2);
				return false;
			}
		});
		
		
		//��ֹ��Ӧ��back�����¼�
		builder.setCancelable(false);
		AlertDialog dialog = builder.create();
		dialog.show();
		result.confirm();//��Ϊû�а��¼�����Ҫǿ��confirm,����ҳ�������ʾ�������ݡ�
		return true;
		// return super.onJsAlert(view, url, message, result);
	}

	@Override
	public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
		// TODO Auto-generated method stub
		final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
		
		builder.setTitle("Barry Confirm Test").setMessage(message).
		setPositiveButton("ȷ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				result.confirm();
			}
		}).setNeutralButton("ȡ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				result.cancel();
			}
		});
				 
		builder.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) 
			{
				result.cancel();
			}
		});

				
		//����keycode����84֮��İ��������ⰴ�����¶Ի�����Ϣ��ҳ���޷��ٵ����Ի��������
		builder.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				Log.v("onJsConfirm", "keyCode==" + keyCode + "event=" + event);
				return true;
			}
		});
				 
		//��ֹ��Ӧ��back�����¼�
		//builder.setCancelable(false);
		AlertDialog dialog = builder.create();
		dialog.show();
		return true;
		// return super.onJsConfirm(view, url, message, result);
	}

	@Override
	public boolean onJsPrompt(WebView view, String url, String message,
			String defaultValue, final JsPromptResult result) {
		// TODO Auto-generated method stub
		final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
		builder.setTitle("Barry Prompt Test").setMessage(message);
		final EditText et = new EditText(view.getContext());
		et.setSingleLine();
		et.setText(defaultValue);
		builder.setView(et);
		builder.setPositiveButton("ȷ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				result.confirm(et.getText().toString());
			}
		}).setNeutralButton("ȡ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				result.cancel();
			}
		});

		//����keycode����84֮��İ��������ⰴ�����¶Ի�����Ϣ��ҳ���޷��ٵ����Ի��������
		builder.setOnKeyListener(new OnKeyListener() {
		@Override
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			Log.v("onJsPrompt", "keyCode==" + keyCode + "event=" + event);
			return true;
		}
		});

		//��ֹ��Ӧ��back�����¼�
		//builder.setCancelable(false);
		AlertDialog dialog = builder.create();
		dialog.show();
		return true;
		// return super.onJsPrompt(view, url, message, defaultValue, result);
	}

	public MyWebChromeClient() {
		// TODO Auto-generated constructor stub
	}

}
