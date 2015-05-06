package com.nightfarmer.eip_x.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightfarmer.eip_x.R;
import com.nightfarmer.eip_x.beans.Person;

public class TxlListViewAdapter extends MyBaseDapter<Person>{

	private List<Person> dataList;

	public TxlListViewAdapter(Context context, List<Person> dataList) {
		super(context, dataList);
		this.dataList = dataList;
	}

	@Override
	protected void initItemView(Person itemData, View itemView) {
		// TODO Auto-generated method stub
		char preChar = itemData.getSortLetters().charAt(0);
		for (int i = 0; i < dataList.size(); i++) {
			char charAt = dataList.get(i).getSortLetters().charAt(0);
			int indexOf = dataList.indexOf(itemData);
			if (preChar==charAt) {
				if (i==indexOf) {
					TextView py = (TextView) itemView.findViewById(R.id.txl_list_item_py);
					py.setText(String.valueOf(preChar).toUpperCase());
					py.setVisibility(View.VISIBLE);
				}else {
					TextView py = (TextView) itemView.findViewById(R.id.txl_list_item_py);
					py.setVisibility(View.GONE);
				}
			}
		}
		ImageView image = (ImageView) itemView.findViewById(R.id.txl_list_item_image);
		image.setImageResource(R.drawable.ic_launcher);
		TextView title = (TextView) itemView.findViewById(R.id.txl_list_item_title);
		title.setText(itemData.getName());
	}

	@Override
	protected int getListItemLayoutId() {
		return R.layout.layout_txl_list_item;
	}

}
