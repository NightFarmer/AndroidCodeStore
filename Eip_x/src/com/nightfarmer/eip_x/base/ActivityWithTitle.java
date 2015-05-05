package com.nightfarmer.eip_x.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.nightfarmer.eip_x.R;
import com.nightfarmer.eip_x.utils.TitleBar;

public class ActivityWithTitle extends Activity{
	
	private int bodyLayoutId;
	private String title;
	private View layoutMainbodyContent;
	
	public ActivityWithTitle(int bodyLayoutId, String title) {
		this.bodyLayoutId = bodyLayoutId;
		this.title = title;
	}
	
	public ActivityWithTitle(View layoutMainbodyContent, String title) {
		this.layoutMainbodyContent = layoutMainbodyContent;
		this.title = title;
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_with_title);
		if (layoutMainbodyContent == null) {
			LayoutInflater inflater = LayoutInflater.from(this);
			layoutMainbodyContent = inflater.inflate(bodyLayoutId, null);
		}
		LinearLayout body = (LinearLayout) findViewById(R.id.layout_with_tile_body);
		android.widget.LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		body.addView(layoutMainbodyContent, layoutParams);
		TitleBar titleBar = new TitleBar(getWindow().getDecorView(), title);
		initTitleBar(titleBar);
		initMainBodyContent(layoutMainbodyContent);
	}
	
	protected void initTitleBar(TitleBar titleBar){
		
	}
	
	protected void initMainBodyContent(View layoutMainbodyContent) {
		
	}
	
	protected void startActivity(Class<? extends ActivityWithTitle> cls) {
		Intent intent = new Intent(this, cls);
		startActivity(intent);
//		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
}
