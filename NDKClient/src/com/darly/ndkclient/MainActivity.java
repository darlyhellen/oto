package com.darly.ndkclient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	
	static {
		System.loadLibrary("NDKClient");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.ndk_text);
		tv.setText(getNDKStr());
	}

	/**
	 * 下午1:31:45
	 * 
	 * @author zhangyh2 TODO
	 */
	public native String getNDKStr();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
