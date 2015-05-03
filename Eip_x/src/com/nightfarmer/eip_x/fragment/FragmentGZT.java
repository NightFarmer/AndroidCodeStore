package com.nightfarmer.eip_x.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.nightfarmer.eip_x.R;
import com.nightfarmer.eip_x.base.FragmentWithTitle;
import com.nightfarmer.eip_x.utils.ButtonWithIcon;
import com.nightfarmer.eip_x.utils.DensityUtil;

public class FragmentGZT extends FragmentWithTitle{

	public FragmentGZT(Context context) {
		super(new LinearLayout(context), "工作台");
	}

	@Override
	protected void initMainBodyContent(View layoutMainbodyContent) {
		// TODO Auto-generated method stub
		LinearLayout linearparent = (LinearLayout) layoutMainbodyContent;
		Context context = linearparent.getContext();
		ScrollView scrollView = new ScrollView(context);
		linearparent.addView(scrollView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		LinearLayout parent = new LinearLayout(context);
		scrollView.addView(parent,  new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		parent.setOrientation(LinearLayout.VERTICAL);
		int padding = DensityUtil.dip2px(getActivity(), 10);
		parent.setPadding(padding, padding, padding, padding);
//		parent.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
		new ButtonWithIcon(parent,"公告通知",R.drawable.icon_more_about);
		new ButtonWithIcon(parent,"代表事项",R.drawable.icon_more_share);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
		new ButtonWithIcon(parent,"预警信息",R.drawable.icon_more_softsetting);
	}
}
