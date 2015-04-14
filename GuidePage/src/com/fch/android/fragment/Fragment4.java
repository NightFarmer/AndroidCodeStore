package com.fch.android.fragment;

import com.fch.android.guide.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment4 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_4, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		TextView textView = (TextView) getView().findViewById(R.id.tvInNew);

		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 保存数据到SharePreference中以此判断是不是第一次登陆
				// getActivity().finish();
				Toast.makeText(getActivity(), "跳转到首页", Toast.LENGTH_LONG)
						.show();
			}
		});
	}

}
