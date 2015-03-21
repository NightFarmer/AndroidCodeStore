package com.jingchen.autoload;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class TestListActivity extends Activity {

	private MyAdapter adapter;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_list);
		
		listView = (ListView) findViewById(R.id.content_view);
		
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < 15; i++)
		{
			items.add("ÕâÀïÊÇitem " + i);
		}
		adapter = new MyAdapter(this, items);
		listView.setAdapter(adapter);
	}
}
