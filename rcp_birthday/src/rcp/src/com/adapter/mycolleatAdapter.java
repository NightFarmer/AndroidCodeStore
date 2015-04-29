package rcp.src.com.adapter;

import java.util.List;

import rcp.com.Item.colleatItem;
import src.com.rcp.Sql.sql_brith;
import src.com.rcp.day.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class mycolleatAdapter extends BaseAdapter {

	private List<colleatItem> info;

	private Context context;

	private sql_brith db;

	public mycolleatAdapter(List<colleatItem> info, Context context,
			sql_brith db) {
		this.info = info;
		this.context = context;
		this.db = db;
	};

	public int getCount() {
		// TODO Auto-generated method stub
		return info.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return info.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Item item;

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.colleatitem, null);
			item = new Item();
			item.info = (TextView) convertView.findViewById(R.id.colleatInfo);
			item.tb = (ToggleButton) convertView
					.findViewById(R.id.colleat_shoucang);
			convertView.setTag(item);
		} else {
			item = (Item) convertView.getTag();
		}
		item.info.setText(info.get(position).getInfo());
		final int index = position;
		item.tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
//					Log.e("jjjjjjjjjjjjj---??", isChecked + "");
//					db.delate_sms(info.get(index).getId());
//					info.remove(index);
//					notifyDataSetChanged();
				} else{
					Log.e("jjjjjjjjjjjjj---??", isChecked + "");
					db.delate_sms(info.get(index).getId());
//					info.remove(index);
					notifyDataSetChanged();
				}
			}
		});

		return convertView;
	}

	class Item {
		TextView info;

		ToggleButton tb;
	}
}
