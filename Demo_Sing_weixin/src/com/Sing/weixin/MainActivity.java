package com.Sing.weixin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements
		OnPageChangeListener, OnClickListener {
	private ViewPager mViewPager;
	// 选项卡一Fragment
	private FrgReturnVisit returnVisit;

	// 选项卡一Fragment
	private FrgMicroVisit microVisit;

	private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<ChangeColorIconWithTextView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setOverflowShowingAlways();
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		initTabIndicator();

		mViewPager.setOnPageChangeListener(this);
 	}

	private void initTabIndicator() {
		ChangeColorIconWithTextView one = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_one);
		ChangeColorIconWithTextView two = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_two);

		mTabIndicator.add(one);
		mTabIndicator.add(two);

		one.setOnClickListener(this);
		two.setOnClickListener(this);

		one.setIconAlpha(1.0f);
	}

	@Override
	public void onPageSelected(int arg0) {
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

		if (positionOffset > 0) {
			ChangeColorIconWithTextView left = mTabIndicator.get(position);
			ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);

			left.setIconAlpha(1 - positionOffset);
			right.setIconAlpha(positionOffset);
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	@Override
	public void onClick(View v) {

		resetOtherTabs();

		switch (v.getId()) {
		case R.id.id_indicator_one:
			mTabIndicator.get(0).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(0, false);
			break;
		case R.id.id_indicator_two:
			mTabIndicator.get(1).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(1, false);
			break;
		}

	}

	/**
	 * 重置其他的Tab
	 */
	private void resetOtherTabs() {
		for (int i = 0; i < mTabIndicator.size(); i++) {
			mTabIndicator.get(i).setIconAlpha(0);
		}
	}

	private void setOverflowShowingAlways() {
		try {
			// true if a permanent menu key is present, false otherwise.
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		private final String[] titles = { "选项一", "选项二"};

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}

		@Override
		public int getCount() {
			return titles.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (returnVisit == null) {
					returnVisit = new FrgReturnVisit();
				}
				return returnVisit;
			case 1:
				if (microVisit == null) {
					microVisit = new FrgMicroVisit();
				}
				return microVisit;
			default:
				return null;
			}
		}
	}
}
