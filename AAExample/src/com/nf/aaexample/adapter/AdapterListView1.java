package com.nf.aaexample.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nf.aaexample.R;
import com.nf.aaexample.activity.MyActivity1;
import com.nf.aaexample.adapter.base.AdapterBase;

public class AdapterListView1 extends AdapterBase<Map<String, Object>>{
	private Context context;
	
	public AdapterListView1(Context context, int resource,
			List<Map<String, Object>> dataList) {
		super(context, resource, dataList);
		this.context = context;
	}

	@Override
	protected void bindView(int position, View view,
			List<Map<String, Object>> dataList) {
		ImageView iv = (ImageView) view.findViewById(R.id.lv_img1);
		iv.setImageResource(R.drawable.ic_launcher);
		TextView tv = (TextView) view.findViewById(R.id.lv_tv1);
		tv.setText((CharSequence) dataList.get(position).get("tv"));
		Button bt = (Button) view.findViewById(R.id.lv_bt1);
		bt.setText((CharSequence) dataList.get(position).get("tv"));
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "buttonÊÂ¼þ´¥·¢", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(context, MyActivity1.class);
				context.startActivity(intent);
			}
		});
	}

}
