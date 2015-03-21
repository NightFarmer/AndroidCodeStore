package com.smit.util;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private Context mcontext;
	private List<String> mLists;
	
	public ListAdapter(Context context,List<String> lists){
		this.mcontext=context;
		this.mLists=lists;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mLists.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		TextView text=new TextView(mcontext);
		text.setText(mLists.get(arg0));
	//	text.setTextColor(R.string.white);
		return text;
	}




}
