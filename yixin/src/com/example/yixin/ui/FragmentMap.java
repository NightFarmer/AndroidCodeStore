package com.example.yixin.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yixin.R;
public class FragmentMap extends Fragment{
	
	private Context mContext;
	
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_map, container, false);
    	mContext=view.getContext();
    	
    	return view;
    }
}
