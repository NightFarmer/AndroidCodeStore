package com.nightfarmer.draglayout.layout;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nightfarmer.draglayout.R;

public class DragLayout extends FrameLayout{
	
	private int mainLeft;
	private int dragRange;
	
	private ViewDragHelper viewDragHelper;
	private ViewGroup viewMenu;
	private DragMainLayout viewMain;
//	private GestureDetectorCompat gestureDetector;
	private int width;
	private int height;
	private int range;


	public DragLayout(Context context){
		this(context, null);
	}

	public DragLayout(Context context, AttributeSet attrs){
		this(context, attrs, 0);
	}
	
	public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		viewDragHelper = ViewDragHelper.create(this, new DragHelperCallBack());
//		gestureDetector = new GestureDetectorCompat(context,
//				new YScrollDetector());
	}
	
//	class YScrollDetector extends SimpleOnGestureListener {
//		@Override
//		public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx,
//				float dy) {
//			return Math.abs(dy) <= Math.abs(dx);
//		}
//	}

	@Override
	protected void onFinishInflate() {
		viewMenu = (ViewGroup) findViewById(R.id.drag_view_menu);
		viewMain = (DragMainLayout) findViewById(R.id.drag_view_main);
		viewMenu.setClickable(true);
		viewMain.setClickable(true);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return viewDragHelper.shouldInterceptTouchEvent(ev);
//				&& gestureDetector.onTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		try {
			viewDragHelper.processTouchEvent(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
//	@Override
//	public void computeScroll() {
//		if (viewDragHelper.continueSettling(true)) {
//			ViewCompat.postInvalidateOnAnimation(this);
//		}
//	}
	
//	@Override
//	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//		super.onSizeChanged(w, h, oldw, oldh);
//		width = viewMenu.getMeasuredWidth();
//		height = viewMenu.getMeasuredHeight();
//		range = (int) (width * 0.6f);
//	}
//	
//	@Override
//	protected void onLayout(boolean changed, int left, int top, int right,
//			int bottom) {
//		viewMenu.layout(0, 0, width, height);
//		viewMain.layout(mainLeft, 0, mainLeft + width, height);
//	}
	
	private class DragHelperCallBack extends ViewDragHelper.Callback{

		@Override
		public boolean tryCaptureView(View arg0, int arg1) {
			return true;
		}
		
		@Override
		public int getViewHorizontalDragRange(View child) {
			return 1;
		}
		
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
//			if (mainLeft + dx < 0) {
//				return 0;
//			} else if (mainLeft + dx > dragRange) {
//				return dragRange;
//			} 
			return left;
		}
		
		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			return getPaddingTop();
		}
	}
}
