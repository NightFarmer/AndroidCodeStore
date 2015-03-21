package com.Sing.weixin;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <dl>
 * <dt>FrgReference.java</dt>
 * <dd>Description:患教---随访---微访</dd>
 * <dd>Copyright: Copyright (C) 2011</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2014-11-18 上午10:19:23</dd>
 * </dl>
 * 
 * @author lihs
 */
public class FrgMicroVisit extends Fragment {

	private static final String TAG = FrgMicroVisit.class.getName();

	private View view;
	 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(getActivity()).inflate(R.layout.main, null);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		 
		 
	}

}
