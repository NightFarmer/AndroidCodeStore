package com.flavienlaurent.vdh;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

public class YoutubeLayout2 extends ViewGroup{

	private View header;
	private View desc;
	private ViewDragHelper mDragHelper;
	
	private int mTop;
	private int mLeft;
	private int mDragRange;
	private float mDragOffset;

	public YoutubeLayout2(Context context) {
		this(context, null);
	}

	public YoutubeLayout2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YoutubeLayout2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mDragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		依据specMode的值，如果是AT_MOST，specSize 代表的是最大可获得的空间
//		如果是EXACTLY，specSize 代表的是精确的尺寸；
//		如果是UNSPECIFIED，对于控件尺寸来说，没有任何参考意义。
//		int specMode = MeasureSpec.getMode(widthMeasureSpec);
//		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
	}

	/**
	 * 手动重绘
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		mDragRange = getHeight() - header.getHeight();
		
		header.layout(mLeft, mTop, mLeft+header.getMeasuredWidth(), mTop + header.getMeasuredHeight());
		desc.layout(l, mTop+header.getMeasuredHeight(), r, b);
	}
	
	@Override
	protected void onFinishInflate() {
		header = findViewById(R.id.header);
		desc = findViewById(R.id.desc);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mDragHelper.processTouchEvent(event);
		
		if (mDragOffset == 0) {
			smoothSlideTo(1f);
		} else {
			smoothSlideTo(0f);
		}
		
		return true;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return mDragHelper.shouldInterceptTouchEvent(ev);
	}
	
	// 单击和拖动的滚动都需要
	@Override
	public void computeScroll() {
		if (mDragHelper.continueSettling(true)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}
	
	boolean smoothSlideTo(float slideOffset) {
        final int topBound = getPaddingTop();
        int y = (int) (topBound + slideOffset * mDragRange);

        if (mDragHelper.smoothSlideViewTo(header, header.getLeft(), y)) {
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }


	private class DragHelperCallback extends Callback{


		@Override
		public boolean tryCaptureView(View arg0, int arg1) {
			// TODO Auto-generated method stub
			return arg0.getId()==header.getId();
		}
		
		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			// TODO Auto-generated method stub
			mTop = top;
			mLeft = left;
			
			mDragOffset = (float) top / mDragRange;

			//设置view以哪个点为中心缩放
			header.setPivotX(header.getWidth());
			header.setPivotY(header.getHeight());
			
			//设置缩放级别
            header.setScaleX(1 - mDragOffset / 2);
            header.setScaleY(1 - mDragOffset / 2);
            
			requestLayout();
		}
		
		/**
		 * 设置可垂直拖动，y坐标为拖动到的实际y坐标，
		 */
		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			// TODO Auto-generated method stub
			return top;
		}
		
		/**
		 * 设置可水平拖动，x坐标为拖动到的实际x坐标
		 */
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			int top = getPaddingTop();
			//y方向有向下速度，或者速度为0且在屏幕下半部分
			if (yvel > 0 || (yvel == 0 && mDragOffset > 0.5f)) {
				top += mDragRange;
			}
			mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
			invalidate();
		}
	}
}
