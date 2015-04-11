package com.nf.aaexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.nf.aaexample.R;
import com.nf.aaexample.activity.base.ActivityBase;

public class ActivityHome extends ActivityBase {
//	private GridView gvAppGrid;
//	private AdapterAPPGrid adapterAPPGrid;
	private Button bp1;
	private Button bp2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		showMsg("yyyyyyyyyy");
	}
	
	@Override
	protected int getContainLayoutId() {
		return R.layout.home_layout;
	}

	protected void initVariable() {
		// TODO Auto-generated method stub
//		adapterAPPGrid = new AdapterAPPGrid(this);
	}

	protected void initView() {
		bp1 = (Button) findViewById(R.id.bp1);
		bp2 = (Button) findViewById(R.id.bp2);
	}

	protected void initListener() {
		// TODO Auto-generated method stub
		bp1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ActivityHome.this, ActivityViewList.class);
				startActivity(intent);
			}
		});
		
		bp2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openActivity(ActivityDialog.class);
			}
		});
	}

	protected void bindData() {
		// TODO Auto-generated method stub
//		gvAppGrid.setAdapter(adapterAPPGrid);
	}


}
