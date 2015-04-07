package com.flavienlaurent.vdh;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class YoutubeLayout3 extends FrameLayout{

	private ViewDragHelper mDragHelper;
	private View header;
	private View desc;
	private int headerLeft;

	public YoutubeLayout3(Context context) {
		this(context, null);
	}

	public YoutubeLayout3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YoutubeLayout3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mDragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
	}
	
	@Override
	protected void onFinishInflate() {
		header = findViewById(R.id.header);
		desc = findViewById(R.id.desc);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		mDragHelper.processTouchEvent(event);
		return true;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return mDragHelper.shouldInterceptTouchEvent(ev);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		headerLeft = header.getLeft();
	}
	
	private class DragHelperCallback extends ViewDragHelper.Callback{

		@Override
		public boolean tryCaptureView(View arg0, int arg1) {
			// TODO Auto-generated method stub
			return arg0.getId()==header.getId();
		}
		
		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {

//			//设置view以哪个点为中心缩放
			header.setPivotX(header.getWidth());
			header.setPivotY(header.getHeight());
			
			//设置缩放级别
			Log.i("ss", String.valueOf(1 - left/getWidth() / 2));
//            header.setScaleX(1 - 1 / 2);
//            header.setScaleY(1 - 1 / 2);
//            requestLayout();
		}
		
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			return left;
		}
		
	}
}
