package com.nightfarmer.eip_x.utils;

import com.nightfarmer.eip_x.R;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ButtonWithIcon {
//	private LinearLayout parent;

	public ButtonWithIcon(ViewGroup parent, String title, int iconId) {
		View button = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_with_icon, null);
		LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, DensityUtil.dip2px(parent.getContext(), 50));
		layoutParams.bottomMargin = DensityUtil.dip2px(parent.getContext(), 8);
		layoutParams.topMargin = DensityUtil.dip2px(parent.getContext(), 8);
		ImageView icon = (ImageView) button.findViewById(R.id.button_with_icon_icon);
		icon.setImageResource(iconId);
		TextView titileView = (TextView) button.findViewById(R.id.button_with_icon_title);
		titileView.setText(title);
		parent.addView(button,layoutParams);
	}
	
	
}
