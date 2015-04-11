package com.example.guideapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

public class SplashActicity extends Activity implements OnPageChangeListener{
	
	
	private ArrayList<ImageView> dotsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		final ArrayList<View> arrayList = new ArrayList<View>();
		arrayList.add(inflater.inflate(R.layout.sp1, null));
		arrayList.add(inflater.inflate(R.layout.sp2, null));
		arrayList.add(inflater.inflate(R.layout.sp3, null));
		
		dotsList = new ArrayList<ImageView>();
		dotsList.add((ImageView) findViewById(R.id.page0));
		dotsList.add((ImageView) findViewById(R.id.page1));
		dotsList.add((ImageView) findViewById(R.id.page2));
		
		ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager_splash);
		viewpager.setOnPageChangeListener(this);
		viewpager.setAdapter(new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {
				return arrayList.size();
			}
			
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = arrayList.get(position);
				container.addView(view);
				
				if (position == arrayList.size()-1) {
					view.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							gohome();
						}
					});
				}
				
				return view;
			}
			
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(arrayList.get(position));
			}
		});
		
		findViewById(R.id.go_home).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gohome();
			}
		});
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setDotsSelected(arg0);
	}

	private void gohome(){
		SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean("isFirstIn", false);
		editor.commit();
		Intent intent = new Intent(SplashActicity.this, HomeActivity.class);
		startActivity(intent);
		finish();
	}
	
	private void setDotsSelected(int index){
		dotsList.size();
		for (int i = 0; i < dotsList.size(); i++) {
			if (i==index) {
				dotsList.get(i).setImageDrawable(getResources().getDrawable(R.drawable.page_now));
			}else {
				dotsList.get(i).setImageDrawable(getResources().getDrawable(R.drawable.page));
			}
		}
	}
}
