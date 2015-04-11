package com.nf.aaexample.adapter;

import com.nf.aaexample.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterAPPGrid extends BaseAdapter{

	private Context context;
	
	public AdapterAPPGrid(Context context) {
		super();
		this.context = context;
	}

	private class Holder{
		ImageView ivIcon;
		TextView tvIcon;
	}
	
	private Integer[] imageIntegers = {
			R.drawable.bt,
			R.drawable.bt,
			R.drawable.bt,
			R.drawable.bt,
			R.drawable.bt,
			R.drawable.bt
	};
	
	private String[] imageNames = {
			"1",
			"2",
			"3",
			"4",
			"5",
			"6"
	};
	
	@Override
	public int getCount() {
		return imageIntegers.length;
	}

	@Override
	public Object getItem(int position) {
		return imageNames[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder ; 
		if (convertView == null) {
			LayoutInflater fi = LayoutInflater.from(context);
			convertView = fi.inflate(R.layout.main_body_item, null);
			holder = new Holder();
			holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
			holder.tvIcon = (TextView) convertView.findViewById(R.id.tvIcon);
			convertView.setTag(holder);
		}else {
			holder = (Holder) convertView.getTag();
		}
		holder.ivIcon.setImageResource(imageIntegers[position]);
		holder.tvIcon.setText(imageNames[position]);
		return convertView;
	}

}
