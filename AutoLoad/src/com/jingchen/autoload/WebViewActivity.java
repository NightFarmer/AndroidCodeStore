package com.jingchen.autoload;

import com.jingchen.autoload.PullToRefreshLayout.OnRefreshListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;

public class WebViewActivity extends Activity{
	
	PullableWebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		webView = (PullableWebView) findViewById(R.id.content_view);
		webView.loadUrl("http://genebook.com.cn");
		
		((PullToRefreshLayout) findViewById(R.id.refresh_view))
		.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
				// TODO Auto-generated method stub
				// ����ˢ�²���
				webView.loadUrl("http://www.hao123.com");
				new Handler()
				{
					@Override
					public void handleMessage(Message msg)
					{
						// ǧ������˸��߿ؼ�ˢ�������Ŷ��
						pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
					}
				}.sendEmptyMessageDelayed(0, 1000);
			}
		});
	}
}
