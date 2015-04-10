package com.nightfarmer.draglayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nightfarmer.draglayout.layout.DragLayout;

public class MainActivity extends ActionBarActivity {

	private DragLayout dragLayout;
	private ListView menuListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dragLayout = (DragLayout) findViewById(R.id.dragLayout);
		// 生成测试菜单选项
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 5; i++) {
			Map<String, Object> item;
			item = new HashMap<String, Object>();
			item.put("item", "选项" + (i + 1));
			data.add(item);
		}
		menuListView = (ListView) findViewById(R.id.menu_listview);
		menuListView.setAdapter(new SimpleAdapter(this, data,
				R.layout.menulist_item_text, new String[] { "item" },
				new int[] { R.id.menu_text }));
		findViewById(R.id.drag_open).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dragLayout.open();
			}
		});
	}

}
