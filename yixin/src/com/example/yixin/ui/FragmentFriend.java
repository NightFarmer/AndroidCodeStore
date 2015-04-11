package com.example.yixin.ui;

import com.example.yixin.R;
import com.example.yixin.view.PathView;
import com.example.yixin.view.PathView.OnItemClickListener;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentFriend extends Fragment implements OnItemClickListener {

	//
	private View view;
	//
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_friend, container, false);
		context = inflater.getContext();
		setupView();
		return view;
	}

	/**
	 * 初始化菜单
	 */
	private void setupView() {
		PathView mPathView = (PathView) view
				.findViewById(R.id.mPathView_firdent);
		ImageButton startMenu = new ImageButton(context);
		startMenu.setBackgroundResource(R.drawable.start_menu_btn);
		mPathView.setStartMenu(startMenu);
		int[] drawableIds = new int[] { R.drawable.pathmenu_text_selector,
				R.drawable.pathmenu_picture_selector,
				R.drawable.pathmenu_photo_selector };
		View[] items = new View[drawableIds.length];
		for (int i = 0; i < drawableIds.length; i++) {
			ImageButton button = new ImageButton(context);
			button.setBackgroundResource(drawableIds[i]);
			items[i] = button;
		}
		mPathView.setItems(items);
		mPathView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(View view, int position) {
		switch (position) {
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		default:
			break;
		}
	}
}
