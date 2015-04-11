package com.nf.aaexample.adapter.base;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AdapterBase<T> extends BaseAdapter{
	
	private List<T> dataList; 
	private LayoutInflater mInflater;
	private int resource;
	
	public AdapterBase(Context context, int resource, List<T> dataList) {
		this.dataList = dataList;
		this.mInflater = LayoutInflater.from(context);
		this.resource = resource;
	}

	@Override
	public int getCount() {
		return dataList!=null?dataList.size():0;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
        if (convertView == null) {
            v = mInflater.inflate(resource, parent, false);
        } else {
            v = convertView;
        }
        bindView(position, v, dataList);
        return v;
	}

	protected abstract void bindView(int position, View view, List<T> dataList);

}
