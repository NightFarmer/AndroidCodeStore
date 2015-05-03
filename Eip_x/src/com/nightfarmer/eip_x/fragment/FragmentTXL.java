package com.nightfarmer.eip_x.fragment;

import android.view.View;

import com.nightfarmer.eip_x.R;
import com.nightfarmer.eip_x.base.FragmentWithTitle;
import com.nightfarmer.eip_x.utils.TitleBar;

public class FragmentTXL extends FragmentWithTitle{

	public FragmentTXL() {
		super(R.layout.layout_gzt, "ͨѶ¼");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTitleBar(TitleBar titleBar) {
		// TODO Auto-generated method stub
		super.initTitleBar(titleBar);
//		titleBar.showBackButton();
		titleBar.showOpitonButton("�Ǻ�");
	}
	
	@Override
	protected void initMainBodyContent(View layoutMainbodyContent) {
		// TODO Auto-generated method stub
		
	}
}
