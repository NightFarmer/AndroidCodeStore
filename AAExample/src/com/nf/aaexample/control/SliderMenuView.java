package com.nf.aaexample.control;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.nf.aaexample.R;

public class SliderMenuView {
	private List<SliderMenuItem> mItemList;
	private Activity mAcivity;
	private boolean mIsClosed;
	private RelativeLayout mRelativeLayout;

	private void initVariable() {
		// TODO Auto-generated method stub
		mItemList = new ArrayList<SliderMenuItem>();
		mIsClosed = true;
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		mRelativeLayout = (RelativeLayout) mAcivity.findViewById(R.id.bottom_box);
	}
	
	private void initListener() {
		// TODO Auto-generated method stub
		mRelativeLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mAcivity, String.valueOf(mIsClosed), Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void open() {
		// TODO Auto-generated method stub
		LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.BELOW, R.id.title_box);
		mRelativeLayout.setLayoutParams(layoutParams);
	}

	private void close() {
		LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.BELOW, R.id.title_box);
		mRelativeLayout.setLayoutParams(layoutParams);
	}
	
	private void toggle() {
		// TODO Auto-generated method stub

	}
	
	private void add() {
		// TODO Auto-generated method stub

	}
	
	private void bindList() {
		// TODO Auto-generated method stub

	}
	
	private void onSlideMenuClick() {
		// TODO Auto-generated method stub

	}
}
