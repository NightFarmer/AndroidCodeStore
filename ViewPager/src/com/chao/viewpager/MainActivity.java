package com.chao.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chao.adapter.FragmentAdapter;
import com.chao.fragment.AccountFragment01;
import com.chao.fragment.AccountFragment02;
import com.chao.fragment.AccountFragment03;



public class MainActivity extends FragmentActivity {
	private LinearLayout[] linearLayouts;
	private TextView[] textViews;

	private ViewPager viewPagers;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setlinearLayouts();
		settextview();		
		
		viewPagers = (ViewPager) findViewById(R.id.viewPager);
		List<Fragment> totalFragment = new ArrayList<Fragment>();
		//把页面添加到ViewPager里
		totalFragment.add(new AccountFragment01());
		totalFragment.add(new AccountFragment02());
		totalFragment.add(new AccountFragment03());
		
		viewPagers.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
				totalFragment));
		//设置显示哪页
		viewPagers.setCurrentItem(0);
		
		viewPagers.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				resetlaybg();
				linearLayouts[arg0]
						.setBackgroundResource(R.drawable.linearlayout01s);
				textViews[arg0].setTextColor(getResources().getColor(
						R.color.textcolor));

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/** 初始化linerlayout */
	public void setlinearLayouts() {
		linearLayouts = new LinearLayout[3];
		linearLayouts[0] = (LinearLayout) findViewById(R.id.lay1);
		linearLayouts[1] = (LinearLayout) findViewById(R.id.lay2);
		linearLayouts[2] = (LinearLayout) findViewById(R.id.lay3);
		linearLayouts[0].setBackgroundResource(R.drawable.linearlayout01s);

	}

	/** 初始化textview */
	public void settextview() {
		textViews = new TextView[3];
		textViews[0] = (TextView) findViewById(R.id.fratext1);
		textViews[1] = (TextView) findViewById(R.id.fratext2);
		textViews[2] = (TextView) findViewById(R.id.fratext3);
		textViews[1].setTextColor(getResources()
				.getColor(R.color.textcolor));
	}

	/** 点击linerlayout实现切换fragment的效果 */
	public void LayoutOnclick(View v) {
		// 每次点击都重置linearLayouts的背景、textViews字体颜色
		switch (v.getId()) {
		case R.id.lay1:
			resetlaybg();
			viewPagers.setCurrentItem(0);
			linearLayouts[0].setBackgroundResource(R.drawable.linearlayout01s);
			textViews[0].setTextColor(getResources().getColor(
					R.color.textcolor));
			break;

		case R.id.lay2:
			resetlaybg();
			viewPagers.setCurrentItem(1);
			linearLayouts[1].setBackgroundResource(R.drawable.linearlayout01s);
			textViews[1].setTextColor(getResources().getColor(
					R.color.textcolor));

			break;
		case R.id.lay3:
			resetlaybg();
			viewPagers.setCurrentItem(2);
			linearLayouts[2].setBackgroundResource(R.drawable.linearlayout01s);
			textViews[2].setTextColor(getResources().getColor(
					R.color.textcolor));

			break;

		}
	}

	/** 重置linearLayouts、textViews */
	public void resetlaybg() {
		for (int i = 0; i < 3; i++) {
			// linearLayouts[i].setBackgroundResource(R.drawable.ai);
			textViews[i].setTextColor(getResources().getColor(R.color.black));
			linearLayouts[i].setBackgroundResource(R.drawable.linearlayout01);
		}

	}

}
