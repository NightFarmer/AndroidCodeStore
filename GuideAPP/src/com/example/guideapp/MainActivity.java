package com.example.guideapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private static final int GO_HOME = 1000;  
    private static final int GO_GUIDE = 1001;  
    
    static final String SHAREDPREFERENCES_NAME = "first_pref";  
    
 // 延迟3秒  
    private static final long SPLASH_DELAY_MILLIS = 3000;  
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Intent intent = new Intent();
			switch (msg.what) {
			case GO_HOME:
				intent.setClass(MainActivity.this, HomeActivity.class);
				break;
			case GO_GUIDE:
				intent.setClass(MainActivity.this, SplashActicity.class);
				break;
			}
			startActivity(intent);
			finish();
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		//全屏显示 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);   
		
		// 读取SharedPreferences中需要的数据  
        // 使用SharedPreferences来记录程序的使用次数 
		SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		
		// 取得相应的值，如果没有该值，说明还未写入，用true作为默认值  
		boolean isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);
		
		if (isFirstIn) {
			handler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
		}else {
			handler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
		}
	}

}
