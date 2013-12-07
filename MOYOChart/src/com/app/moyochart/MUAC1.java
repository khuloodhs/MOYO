package com.app.moyochart;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MUAC1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_muac1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.muac1, menu);
		return true;
	}

}
