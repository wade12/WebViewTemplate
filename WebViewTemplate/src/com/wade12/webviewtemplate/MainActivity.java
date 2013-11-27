package com.wade12.webviewtemplate;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

	WebView webView;
	// WebView allows display of formatted html & css in the project.
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		webView = new WebView(this);
		setContentView(webView);
		context = this;
		// webView.setWebViewClient(client);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new WebViewInterface(), "MainActivityInterface");
		// webView is able to communicate with Activity through JavaScript.
		
		InputStream inputStream;
		try {
			inputStream = this.getAssets().open("article.html");
			int streamSize = inputStream.available();
			byte[] buffer = new byte[streamSize];
			inputStream.read(buffer);
			inputStream.close();
			String html = new String(buffer);
			// webView.loadData(html, "text/html", "UTF-8");
			webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "UTF-8", null);
			
		} // end try
		catch (IOException ioexception) {
			ioexception.printStackTrace();
		} // end catch
		
		
	} // end method onCreate

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	} // end method onCreateOptionsMenu
	
	
	/*
	// WebChromeClient client = new WebChromeClient();
	WebViewClient client = new WebViewClient();

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub
		Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
		return false;
	} // end method shouldOverrideUrlLoading
	*/
	
	
	/*
	@Override
	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
		Toast.makeText(context, description, Toast.LENGTH_SHORT).show();
		return super.onReceivedError(view, errorCode,description, failingUrl);
	} // end method onReceivedError
	*/
	

	// JavaScript enabling works as thus:
	// create a class with methods and mark them as javascript interface methods
	// then bind class containing methods to the webview
	// and then the js can access those methods through a js object
	public class WebViewInterface {
		
		@JavascriptInterface
		public void dislayToast() {
			Toast.makeText(context, "JavaScript WebInterface called.", Toast.LENGTH_LONG).show();
		} // end method dislayToast
	} // end Class webViewInterface
	
} // end Class MainActivity
