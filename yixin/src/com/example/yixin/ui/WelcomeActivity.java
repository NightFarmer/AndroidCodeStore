/**
 * 
 */
package com.example.yixin.ui;

import com.example.yixin.R;
import com.example.yixin.config.Constants;
import com.example.yixin.util.SharedPreferencesUtil;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * @author kince
 * 
 */
public class WelcomeActivity extends BaseActivity {
    
	public Context mContext;
	//程序是否使用过
	private boolean isUse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_welcome);

		mContext = WelcomeActivity.this;
		isUse = SharedPreferencesUtil.readIsFirstUse(mContext);
		if (!isUse) {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					/**
                     * 
                     */
					openActivity(GuideActivity.class);
				}
			}, Constants.TIME_DELAY_GUIDE);
		} else {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
                    /**
                     * 
                     */
					openActivity(YixinMainActivity.class);
				}
			}, Constants.TIME_DELAY_WELCOME);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void setupView() {
		super.setupView();
	}

	@Override
	protected void setupData() {
		super.setupData();
	}

}
