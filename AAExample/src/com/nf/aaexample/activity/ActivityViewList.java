package com.nf.aaexample.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nf.aaexample.R;
import com.nf.aaexample.activity.base.ActivityBase;
import com.nf.aaexample.adapter.AdapterListView1;

public class ActivityViewList extends ActivityBase{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appendMainBody(R.layout.listview);
		ListView  lv1 = (ListView) findViewById(R.id.lv_1);
		ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("tv", "yoo");
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		arrayList.add(hashMap);
		AdapterListView1 adp = new AdapterListView1(this, R.layout.listview_itme, arrayList);
		lv1.setAdapter(adp);
		lv1.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showMsg("itemClickÊÂ¼þ´¥·¢");
				Intent intent = new Intent();
				intent.setClass(ActivityViewList.this, MyActivity1.class);
				ActivityViewList.this.startActivity(intent);
			}
		});
	}

	@Override
	protected int getContainLayoutId() {
		return 0;
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void bindData() {
		// TODO Auto-generated method stub
		
	}
}
