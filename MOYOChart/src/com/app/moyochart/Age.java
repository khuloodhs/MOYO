package com.app.moyochart;

import com.app.moyochart.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import MOYO.*;

public class Age extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_age);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.age, menu);
		return true;
	}
	



}
