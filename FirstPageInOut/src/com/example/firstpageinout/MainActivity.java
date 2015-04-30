package com.example.firstpageinout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_in2);
		findViewById(R.id.yoyo).startAnimation(loadAnimation);	
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, HomeActivity.class);
				startActivity(intent);

				int VERSION = android.os.Build.VERSION.SDK_INT;
				if (VERSION >= 5) {
					MainActivity.this.overridePendingTransition(
							R.anim.alpha_in, R.anim.alpha_out);
				}
				finish();
			}
		}, 3000);

	}

	private void setShowAnimation(View view, int duration) {
		AlphaAnimation mShowAnimation = new AlphaAnimation(0.0f, 1.0f);
		mShowAnimation.setDuration(duration);
		mShowAnimation.setFillAfter(true);
		view.startAnimation(mShowAnimation);
	}

	@Override
	public void onBackPressed() {
		finish();
		System.exit(0);
	}
}
