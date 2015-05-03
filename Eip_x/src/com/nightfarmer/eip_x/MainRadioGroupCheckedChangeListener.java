package com.nightfarmer.eip_x;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.Toast;

import com.nightfarmer.eip_x.widget.FlowRadioGroup;
import com.nightfarmer.eip_x.widget.FlowRadioGroup.OnCheckedChangeListener;

public class MainRadioGroupCheckedChangeListener implements OnCheckedChangeListener{

	
	private FragmentActivity fragmentActivity;
	private List<Fragment> fragementList;
	private int layoutId;

	private int currentIndex = 0;

	public MainRadioGroupCheckedChangeListener(FragmentActivity fragmentActivity, List<Fragment> fragementList, int layoutId) {
		this.fragmentActivity = fragmentActivity;
		this.fragementList = fragementList;
		this.layoutId = layoutId;
		FragmentTransaction localFragmentTransaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
	    localFragmentTransaction.add(layoutId, (Fragment)fragementList.get(0));
	    localFragmentTransaction.commit();
	}
	
	
	@Override
	public void onCheckedChanged(FlowRadioGroup group, int checkedId) {
		int radioButtonCount = group.getRadioButtonCount();
		int i = 0;
		for (; i < radioButtonCount; i++) {
			RadioButton radioButton = group.getRadioButton(i);
			if (radioButton.getId() == checkedId) {
				break;
			}
		}
		if (i>=fragementList.size()) {
			return;
		}
		if (i==currentIndex) {
			return;
		}
		FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
		FragmentTransaction transaction = supportFragmentManager.beginTransaction();
		Fragment fragment = fragementList.get(i);
		if (!fragment.isAdded()) {
			transaction.add(layoutId, fragment);
		}
		int adnim_in = R.anim.slide_right_in;
		int adnim_out = R.anim.slide_left_out;
		if (i < currentIndex) {
			adnim_in = R.anim.slide_left_in;
			adnim_out = R.anim.slide_right_out;
		}
		transaction.setCustomAnimations(adnim_in, adnim_out);
		transaction.show(fragment);
		for (int j = 0; j < fragementList.size(); j++) {
			if (j!=i) {
				transaction.hide(fragementList.get(j));
			}
		}
		transaction.commit();
		currentIndex = i;
	}

}
