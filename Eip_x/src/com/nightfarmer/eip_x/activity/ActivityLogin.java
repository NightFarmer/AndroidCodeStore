package com.nightfarmer.eip_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nightfarmer.eip_x.R;
import com.nightfarmer.eip_x.base.ActivityWithTitle;
import com.nightfarmer.eip_x.utils.TitleBar;

public class ActivityLogin extends ActivityWithTitle{

	public ActivityLogin() {
		super(R.layout.activity_login, "µÇÂ½");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Button loginBt = (Button) findViewById(R.id.Login_loginBt);
		EditText et_name = (EditText) findViewById(R.id.login_edittext_name);
		EditText et_pawd = (EditText) findViewById(R.id.login_edittext_password);
		loginBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ActivityLogin.this, "dengl", 300).show();
				Intent intent = new Intent(ActivityLogin.this, ActivityHome.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	@Override
	protected void initTitleBar(TitleBar titleBar) {
		super.initTitleBar(titleBar);
		Button showOpitonButton = titleBar.showOpitonButton("×¢²á");
		showOpitonButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(ActivityRegister.class);
			}
		});
	}
}
