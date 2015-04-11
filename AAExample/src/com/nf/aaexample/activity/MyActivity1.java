package com.nf.aaexample.activity;

import com.nf.aaexample.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MyActivity1 extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		linearLayout.setLayoutParams(layoutParams);
		Button button = new Button(this);
		button.setLayoutParams(layoutParams);
		button.setText("hehehehehehe");
		LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		linearLayout.addView(button, layoutParams2);
		setContentView(linearLayout);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				intent.setAction("android.intent.action.YOYOCKN");
//				intent.setClass(MyActivity1.this, MainActivity.class);
//				startActivity(intent);
//				Intent intent = getIntent();
//				setResult(222, intent);
//				finish();
				Intent intent = new Intent(MyActivity1.this, ActivityHome.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		getString(R.string.app_name);
	}
	
	@Override
	public void onBackPressed() {
		Log.i("MyActivity1", "onBackPressed");
        setResult(1111);
		super.onBackPressed();
	}
}
