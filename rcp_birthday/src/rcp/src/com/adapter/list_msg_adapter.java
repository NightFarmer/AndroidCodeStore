package rcp.src.com.adapter;

import java.util.List;

import rcp.com.Item.smsItem;
import src.com.rcp.Sql.sql_brith;
import src.com.rcp.day.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * @author toshiba
 * 
 */
public class list_msg_adapter extends BaseAdapter {

	private List<smsItem> data;

	private sql_brith db;

	private LayoutInflater layoutInflater;

	public list_msg_adapter(List<smsItem> data, LayoutInflater layoutInflater,
			sql_brith db) {
		this.data = data;
		this.layoutInflater = layoutInflater;
		this.db = db;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		listOptimize list1;
		System.out.println(position);
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.msg_list_item, null);
			list1 = new listOptimize();

			list1.tb_colleat = (ToggleButton) convertView
					.findViewById(R.id.shoucang);
			list1.msg = (TextView) convertView
					.findViewById(R.id.msg_list_item_msg);
			list1.renqi = (TextView) convertView.findViewById(R.id.sms_renqi);

			list1.renqi_info = (TextView) convertView
					.findViewById(R.id.sms_renqi_info);
			convertView.setTag(list1);

		} else {
			list1 = (listOptimize) convertView.getTag();
		}

		final String info = data.get(position).getInfo();
		list1.msg.setText(info);
		list1.renqi_info.setText(data.get(position).getRenqi());
		list1.tb_colleat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							db.insert_sms(info);
						} else {
//							db.delate_sms(sms_id);
						}
					}

				});
		return convertView;
	}

	public class listOptimize {

		/** 短信 */
		TextView msg;
		/** 人气 **/
		TextView renqi;
		/** 人气内容 **/
		TextView renqi_info;
		/** 收藏 */
		ToggleButton tb_colleat;
	}

}
