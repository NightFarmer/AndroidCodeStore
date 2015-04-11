package com.nf.aaexample.activity.base;

import com.nf.aaexample.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public abstract class ActivityFrame extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		appendMainBody(getContainLayoutId());
		initVariable();
		initView();
		initListener();
		bindData();
	}
	
	protected void appendMainBody(int resId) {
		LinearLayout mainBody = (LinearLayout) findViewById(R.id.main_body);
		View inflate;
		try {
			inflate = LayoutInflater.from(this).inflate(resId, null);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mainBody.addView(inflate, layoutParams);
	}
	
	protected abstract int getContainLayoutId();
	protected abstract void initVariable();
	protected abstract void initView();
	protected abstract void initListener();
	protected abstract void bindData();
}
