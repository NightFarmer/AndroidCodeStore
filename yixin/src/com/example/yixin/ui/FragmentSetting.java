package com.example.yixin.ui;

import com.example.yixin.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentSetting extends Fragment{
	
	private ImageButton mLoginOutButton = null;
	private Context mContext;
	
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_setting, container, false);
    	mContext=view.getContext();
    	
    	return view;
    }
}
