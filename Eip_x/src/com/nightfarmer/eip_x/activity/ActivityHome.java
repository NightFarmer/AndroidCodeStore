package com.nightfarmer.eip_x.activity;

import java.util.ArrayList;
import java.util.List;

import com.nightfarmer.eip_x.MainRadioGroupCheckedChangeListener;
import com.nightfarmer.eip_x.R;
import com.nightfarmer.eip_x.R.id;
import com.nightfarmer.eip_x.R.layout;
import com.nightfarmer.eip_x.fragment.FragmentGZT;
import com.nightfarmer.eip_x.fragment.FragmentMore;
import com.nightfarmer.eip_x.fragment.FragmentTXL;
import com.nightfarmer.eip_x.widget.FlowRadioGroup;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ActivityHome extends FragmentActivity{

	private int width;
	private FragmentManager mFragMgr;
	private FlowRadioGroup rg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initRadioGroup();
		
//		initFragement();
		
		List<Fragment> frgList = new ArrayList<Fragment>();
		frgList.add(new FragmentTXL());
		frgList.add(new FragmentGZT(this));
		frgList.add(new FragmentTXL());
		frgList.add(new FragmentMore());
		
		rg.setOnCheckedChangeListener(new MainRadioGroupCheckedChangeListener(this, frgList, R.id.layout_mainframe));
		rg.check(R.id.radiobt_main_a);
//		double x= getResources().getDisplayMetrics().heightPixels;
	}
	
	
	private void initRadioGroup() {
//		width = getWindowManager().getDefaultDisplay().getWidth();
		width = getResources().getDisplayMetrics().widthPixels;
		rg = (FlowRadioGroup) findViewById(R.id.radioGroup_main);
		rg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)(0.2D * width)));
		int radioButtonCount = rg.getRadioButtonCount();
		for (int i = 0; i < radioButtonCount; i++) {
			RadioButton radioButton = rg.getRadioButton(i);
			initRadioButtonSize(radioButton);
		}
	}

	private void initRadioButtonSize(TextView rb){
		Drawable[] drawables = rb.getCompoundDrawables(); //此处取的是android:drawableTop的图片
		drawables[1].setBounds(0, 0, (int)(0.08D * width), (int)(0.1D * width));
		rb.setCompoundDrawables(drawables[0],drawables[1],drawables[2],drawables[3]); 
		rb.setTextSize(0, (float)(0.048D * width));
	}
}
 