package com.example.yixin.ui;

import com.example.yixin.App;
import com.example.yixin.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class LeftSlidingMenuFragment extends Fragment implements
		OnClickListener {

	private View yixinBtnLayout;
	private View circleBtnLayout;
	private View settingBtnLayout;
	private View mapBtnLayout;
	private View moreBtnLayout;
	private TextView mTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_left_fragment, container,
				false);
		yixinBtnLayout = view.findViewById(R.id.yixinBtnLayout);
		yixinBtnLayout.setOnClickListener(this);
		circleBtnLayout = view.findViewById(R.id.circleBtnLayout);
		circleBtnLayout.setOnClickListener(this);
		settingBtnLayout = view.findViewById(R.id.settingBtnLayout);
		settingBtnLayout.setOnClickListener(this);
		mapBtnLayout = view.findViewById(R.id.mapBtnLayout);
		mapBtnLayout.setOnClickListener(this);
		moreBtnLayout = view.findViewById(R.id.moreBtnLayout);
		moreBtnLayout.setOnClickListener(this);
		mTextView = YixinMainActivity.mTextView;
		return view;
	}

	@Override
	public void onClick(View v) {
		Fragment newContent = null;
		switch (v.getId()) {
		case R.id.yixinBtnLayout:
			App.LEFT_MENU_PAGE = 0;
			newContent = new FragmentYixin();
			settingBtnLayout.setSelected(false);
			circleBtnLayout.setSelected(false);
			yixinBtnLayout.setSelected(true);
			mapBtnLayout.setSelected(false);
			moreBtnLayout.setSelected(false);
			mTextView.setText("易信");

			break;
		case R.id.circleBtnLayout:
			App.LEFT_MENU_PAGE = 1;
			newContent = new FragmentFriend();
			settingBtnLayout.setSelected(false);
			circleBtnLayout.setSelected(true);
			yixinBtnLayout.setSelected(false);
			mapBtnLayout.setSelected(false);
			moreBtnLayout.setSelected(false);
			mTextView.setText("朋友圈");
			break;
		case R.id.settingBtnLayout:
			newContent = new FragmentSetting();
			settingBtnLayout.setSelected(true);
			circleBtnLayout.setSelected(false);
			yixinBtnLayout.setSelected(false);
			mapBtnLayout.setSelected(false);
			moreBtnLayout.setSelected(false);
			mTextView.setText("设置");
			break;
		case R.id.mapBtnLayout:
			newContent = new FragmentMap();
			settingBtnLayout.setSelected(false);
			circleBtnLayout.setSelected(false);
			yixinBtnLayout.setSelected(false);
			mapBtnLayout.setSelected(true);
			moreBtnLayout.setSelected(false);
			mTextView.setText("朋友地图");
			break;
		case R.id.moreBtnLayout:
			newContent = new FragmentMore();
			settingBtnLayout.setSelected(false);
			circleBtnLayout.setSelected(false);
			yixinBtnLayout.setSelected(false);
			mapBtnLayout.setSelected(false);
			moreBtnLayout.setSelected(true);
			mTextView.setText("更多应用");
			break;
		default:
			break;
		}

		if (newContent != null)
			switchFragment(newContent);

	}

	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		YixinMainActivity ra = (YixinMainActivity) getActivity();
		ra.switchContent(fragment);
	}

}
