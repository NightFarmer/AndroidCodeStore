package com.jingchen.autoload;

import com.jingchen.autoload.OverScrollView.OverScrollListener;
import com.jingchen.autoload.OverScrollView.OverScrollTinyListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class RefreshActivity extends Activity implements OverScrollListener, OverScrollTinyListener{

	/**
	 * 初始化填充值，控制图片被隐藏的边缘值
	 */
	private static final int PADDING = -100;
	
	private ImageView mHeaderImage;
	OverScrollView scrollView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refresh);
		
		mHeaderImage = (ImageView) findViewById(R.id.image);
		
		scrollView = (OverScrollView) findViewById(R.id.layout);
		scrollView.setOverScrollListener(this);
		scrollView.setOverScrollTinyListener(this);
		
		// 隐藏图片的初始边界
		// 保证下拉能出现放大图片的效果
		scrollLoosen();
	}

	@Override
	public void scrollDistance(int tinyDistance, int totalDistance) {
		if (totalDistance < 0 || tinyDistance == 0
				|| mHeaderImage.getPaddingBottom() == 0) {
			return;
		}
		int padding = PADDING + totalDistance / 2;
		if (padding > 0) {
			padding = 0;
		}
		mHeaderImage.setPadding(padding, 0, padding, padding);
	}

	@Override
	public void scrollLoosen() {
		int padding = PADDING;
		mHeaderImage.setPadding(padding, 0, padding, padding);
		// 滑动处理松开时回调
	}

	@Override
	public void headerScroll() {
		Toast.makeText(getApplicationContext(), "开始刷新", Toast.LENGTH_SHORT).show();
		
		// 处理下拉超过一定临界值时 的回调
	}

	@Override
	public void footerScroll() {
		// 处理上拉超过一定临界值时 的回调
		Toast.makeText(getApplicationContext(), "开始加载", Toast.LENGTH_SHORT).show();
	}

}
