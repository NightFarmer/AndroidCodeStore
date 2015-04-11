package com.nf.aaexample.activity.base;

import android.content.Intent;
import android.widget.Toast;

public abstract class ActivityBase extends ActivityFrame{

	protected void showMsg(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	protected void openActivity(Class<? extends ActivityBase> cls) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		startActivity(intent);
	}
}
