package com.chao.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter{

	List<Fragment> list ;
	
	public FragmentAdapter(FragmentManager fm,List<Fragment> list) {
		super(fm);
		
		this.list = list;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
