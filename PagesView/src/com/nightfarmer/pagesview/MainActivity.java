package com.nightfarmer.pagesview;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("InflateParams")
public class MainActivity extends ActionBarActivity {

	private View layout1;
	private View layout2;
	private View laytou3;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTab); 
        pagerTabStrip.setTabIndicatorColor(0xff00ff);  
		
		LayoutInflater inflater = LayoutInflater.from(this);
		layout1 = inflater.inflate(R.layout.layout1, null);	
		layout2 = inflater.inflate(R.layout.layout2, null);
		laytou3 = inflater.inflate(R.layout.layout3, null);
		
		final ArrayList<View> arrayList = new ArrayList<View>();
		arrayList.add(layout1);
		arrayList.add(layout2);
		arrayList.add(laytou3);
		
		final ArrayList<String> titleList = new ArrayList<String>();
		titleList.add("标题1");
		titleList.add("标题2");
		titleList.add("标题3");
		
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setAdapter(new PagerAdapter() {
			
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
				View child = arrayList.get(position);
				container.addView(child, 0);
				return child;
			}
			
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(arrayList.get(position));
			}
			
			@Override
			public CharSequence getPageTitle(int position) {
				return titleList.get(position);
			}
		});
	}

}
