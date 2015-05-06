package com.nightfarmer.eip_x.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseDapter<T> extends BaseAdapter{

	private List<T> dataList; 
	private int listItemLayout;
	private LayoutInflater layoutInflater;
	
	public MyBaseDapter(Context context, List<T> dataList) {
		this.dataList = dataList;
		this.listItemLayout = getListItemLayoutId();
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null) {
			convertView = layoutInflater.inflate(listItemLayout, null);
		}
		initItemView(dataList.get(position), convertView);
		return convertView;
	}

	protected abstract void initItemView(T itemData, View itemView);
	
	protected abstract int getListItemLayoutId();
}
