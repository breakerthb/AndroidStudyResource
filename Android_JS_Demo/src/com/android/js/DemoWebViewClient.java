package com.android.js;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class DemoWebViewClient extends WebViewClient { 
	@Override 
	public boolean shouldOverrideUrlLoading(WebView view, String url) { 
    	view.loadUrl(url); 
    	return true; 
	} 
} 
