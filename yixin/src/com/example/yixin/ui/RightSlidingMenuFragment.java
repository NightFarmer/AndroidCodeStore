package com.example.yixin.ui;

import com.example.yixin.App;
import com.example.yixin.R;
import com.example.yixin.config.Constants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author kince
 * 
 */
public class RightSlidingMenuFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int page = App.LEFT_MENU_PAGE;
		View view = null;
		switch (page) {
		case Constants.LEFT_MENU_OAGE_YIXIN:
			view = inflater.inflate(R.layout.main_right_fragment_yixin, container,
					false);
			break;
		case Constants.LEFT_MENU_OAGE_FRIDENT:
			view = inflater.inflate(R.layout.main_right_fragment_frident, container,
					false);
			break;
		default:
			break;
		}
		return view;
	}

}
