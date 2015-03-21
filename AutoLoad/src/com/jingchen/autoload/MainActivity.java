package com.jingchen.autoload;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.jingchen.autoload.PullToRefreshLayout.OnRefreshListener;
import com.jingchen.autoload.PullableListView.OnLoadListener;

/**
 * 更多详解见博客http://blog.csdn.net/zhongkejingwang/article/details/38963177
 * @author chenjing
 *
 */
public class MainActivity extends Activity implements OnLoadListener
{

	private PullableListView listView;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
				.setOnRefreshListener(new OnRefreshListener() {
					
					@Override
					public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
						// TODO Auto-generated method stub
						// 下拉刷新操作
						new Handler()
						{
							@Override
							public void handleMessage(Message msg)
							{
								// 千万别忘了告诉控件刷新完毕了哦！
								if (adapter.getCount() > 30) {
									pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
									listView.setHasMoreData(false);
									return;
								}
								for (int i = 0; i < 4; i++)
								{
									adapter.addItem("这里是item " + i);
								}
								adapter.notifyDataSetChanged();
								pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
								listView.setHasMoreData(true);
							}
						}.sendEmptyMessageDelayed(0, 3000);
					}
				});
		listView = (PullableListView) findViewById(R.id.content_view);
		initListView();
		listView.setOnLoadListener(this);
		listView.setHasMoreData(false);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.action_test){
			startActivity(new Intent(this, TestActivity.class));
		}
		if(item.getItemId() == R.id.action_refresh){
			startActivity(new Intent(this, RefreshActivity.class));
		}
		
		if(item.getItemId() == R.id.action_list){
			startActivity(new Intent(this, TestListActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * ListView初始化方法
	 */
	private void initListView()
	{
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < 3; i++)
		{
			items.add("按菜单键查看更多，点击查看Webview下拉刷新 " + i);
		}
		adapter = new MyAdapter(this, items);
		listView.setAdapter(adapter);
		listView.setOnItemLongClickListener(new OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				startActivity(new Intent(MainActivity.this, WebViewActivity.class));
				return true;
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				startActivity(new Intent(MainActivity.this, WebViewActivity.class));
			}
		});
	}

	@Override
	public void onLoad(final PullableListView pullableListView)
	{
		new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				for (int i = 0; i < 8; i++){
					adapter.addItem("这里是自动加载进来的item");
				}
				pullableListView.finishLoading();
				adapter.notifyDataSetChanged();
				if (adapter.getCount() > 30) {
					listView.setHasMoreData(false);
				}
			}
		}.sendEmptyMessageDelayed(0, 2000);
	}

}
