package com.wade12.webviewtemplate;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

	WebView webView;
	// WebView allows display of formatted html & css in the project.
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		webView = new WebView(this);
		setContentView(webView);
		
		InputStream inputStream;
		try {
			inputStream = this.getAssets().open("article.html");
			int streamSize = inputStream.available();
			byte[] buffer = new byte[streamSize];
			inputStream.read(buffer);
			inputStream.close();
			String html = new String(buffer);
			webView.loadData(html, "text/html", "UTF-8");
			
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

} // end Class MainActivity
