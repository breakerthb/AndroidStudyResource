package com.android.js;

import com.android.js.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class PhoneTest extends Activity {

	private WebView mWebView;
	private Handler mHandler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		mWebView = (WebView) findViewById(R.id.webview);

		mWebView.getSettings().setJavaScriptEnabled(true);

		mWebView.setWebViewClient(new DemoWebViewClient());
		//mWebView.setWebChromeClient(new MyWebChromeClient());

		mWebView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
		mWebView.loadUrl("file:///android_asset/index.html");
		
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mWebView.loadUrl("javascript:wave()");
			}
		});
		
	}

	final class DemoJavaScriptInterface {
		public void clickOnAndroid() {
			mHandler.post(new Runnable() {
				public void run() {
					System.out.println("=======================AAA=======================");
					Toast toast = Toast.makeText( PhoneTest.this, "AAA", Toast.LENGTH_SHORT);
					toast.show();

				}
			});
		}
	}

}
