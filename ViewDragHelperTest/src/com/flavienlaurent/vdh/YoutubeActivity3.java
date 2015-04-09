package com.flavienlaurent.vdh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class YoutubeActivity3 extends Activity{

	private ListView menuListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youtube3);
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

	}
}
