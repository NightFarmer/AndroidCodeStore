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
    
 // �ӳ�3��  
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
		//ȫ����ʾ 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);   
		
		// ��ȡSharedPreferences����Ҫ������  
        // ʹ��SharedPreferences����¼�����ʹ�ô��� 
		SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		
		// ȡ����Ӧ��ֵ�����û�и�ֵ��˵����δд�룬��true��ΪĬ��ֵ  
		boolean isFirstIn = sharedPreferences.getBoolean("isFirstIn", true);
		
		if (isFirstIn) {
			handler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
		}else {
			handler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
		}
	}

}
