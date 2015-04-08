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
	
//	private int headerLeft;
	
	/**
	 * 主界面缩放速率，0为不缩放，1为到屏幕右侧缩放到最小
	 */
	private final float MAINSCALERATE = 0.5f;
	/**
	 * 主界面移动比例，0.7为左边界能够移动到的最大屏幕比例
	 */
	private final float MAINLOCATPERSONT = 0.7f;

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
//		headerLeft = header.getLeft();
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
//			header.setPivotX(header.getWidth());
//			header.setPivotY(header.getHeight());
			
			//设置缩放级别
			
            float scaleM = 1 - MAINSCALERATE*left/header.getWidth();
			header.setScaleX(scaleM);
            header.setScaleY(scaleM);
//            desc.setPivotX(0);
//            desc.setPivotY(desc.getMeasuredHeight()/2f);
            /**
             * 计算出主界面缩放后left的真实坐标
             * 使主界面的left位于屏幕0.7位置时，菜单缩放级别为1
             */
			float scaleMenu = (1-MAINLOCATPERSONT/2)
					+ (left + header.getWidth() * (1 - scaleM) / 2)
					/ header.getWidth()/2;
			/**
			 * 负值为向左偏移，左菜单缩放级别为1时，偏移量为0，偏移量为菜单的宽度比例
			 */
			desc.setTranslationX(-(1-scaleMenu)*desc.getWidth());
			desc.setScaleX(scaleMenu);
			desc.setScaleY(scaleMenu);
//            desc.layout(0, 0, desc.getMeasuredWidth(), desc.getMeasuredHeight());
//            requestLayout();
		}
		
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
//			float scaleM = 1 - 0.5f*left/header.getWidth();
//			float scaleMenu = 0.65f
//					+ (left + header.getWidth() * (1 - scaleM) / 2)
//					/ header.getWidth()/2;
			//经计算得出右边界公式为 2*0.7*header.getWidth()/(2+0.5)
			//经计算得出右边界公式为 2*MAINLOCATPERSONT*header.getWidth()/(2+MAINSCALERATE)
//			double maxLeft = 2*0.7*header.getWidth()/(2+0.5);
			double maxLeft = 0.56*header.getWidth();
			if (left>maxLeft) {
				return (int) (0.56*header.getWidth());
			}
			if (left<=0) {
				return 0;
			}
			return left;
		}
		
	}
}
