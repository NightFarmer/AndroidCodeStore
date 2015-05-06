package com.nightfarmer.eip_x.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.nightfarmer.eip_x.R;
import com.nightfarmer.eip_x.utils.TitleBar;

public class FragmentWithTitle extends Fragment{
	private int bodyLayoutId;
	private String title;
	private View layoutMainbodyContent;
	
	public FragmentWithTitle(int bodyLayoutId, String title) {
		this.bodyLayoutId = bodyLayoutId;
		this.title = title;
	}
	
	public FragmentWithTitle(View layoutMainbodyContent, String title) {
		this.layoutMainbodyContent = layoutMainbodyContent;
		this.title = title;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.layout_with_title, null);
		ViewGroup mainBody = (ViewGroup)layout.findViewById(R.id.layout_with_tile_body);
		if (layoutMainbodyContent == null) {
			layoutMainbodyContent = inflater.inflate(bodyLayoutId, null);
		}
		android.widget.LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mainBody.addView(layoutMainbodyContent, layoutParams);
		TitleBar titleBar = new TitleBar(layout, title);
		initTitleBar(titleBar);
		initMainBodyContent(layoutMainbodyContent);
		return layout;
	}
	
	protected void initTitleBar(TitleBar titleBar){
		
	}
	
	protected void initMainBodyContent(View layoutMainbodyContent) {
		
	}
	
	protected View findViewById(int id) {
		return layoutMainbodyContent.findViewById(id);
	}
	
	protected Context getContext() {
		return layoutMainbodyContent.getContext();
	}
}
