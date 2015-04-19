package com.example.tabhost;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	private String[] m_tabTitle = new String[] { "新贴", "主页", "安全资讯", "设置" };
	private Class<?>[] activities = new Class<?>[]{
			ActivityPage1.class,
			ActivityPage2.class,
			ActivityPage3.class,
			ActivityPage4.class
	};

	private int[] m_tabIcon = new int[] { 
			R.drawable.collections_view_as_list,
			R.drawable.collections_view_as_grid, 
			R.drawable.coffee,
			R.drawable.action_settings };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TabHost tabHost = getTabHost();//由TabActivity提供
		for (int i = 0; i < m_tabTitle.length; i++) {
			TabSpec spec = tabHost.newTabSpec(m_tabTitle[i]);//指定页签标题
//			spec.setIndicator(tab);
//			spec.setIndicator(tab, icon);
			View tab = getLayoutInflater().inflate(R.layout.forum_tab, null);
			ImageView imgView = (ImageView) tab.findViewById(R.id.tabIcon);
			imgView.setImageResource(m_tabIcon[i]);
			spec.setIndicator(tab);//指定按钮布局
			spec.setContent(new Intent(this, activities[i]));//指定页签主容器
			tabHost.addTab(spec);
		}
	}
}
