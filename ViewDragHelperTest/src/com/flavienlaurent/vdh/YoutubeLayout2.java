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
//		����specMode��ֵ�������AT_MOST��specSize �����������ɻ�õĿռ�
//		�����EXACTLY��specSize �������Ǿ�ȷ�ĳߴ磻
//		�����UNSPECIFIED�����ڿؼ��ߴ���˵��û���κβο����塣
//		int specMode = MeasureSpec.getMode(widthMeasureSpec);
//		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
	}

	/**
	 * �ֶ��ػ�
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
	
	// �������϶��Ĺ�������Ҫ
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

			//����view���ĸ���Ϊ��������
			header.setPivotX(header.getWidth());
			header.setPivotY(header.getHeight());
			
			//�������ż���
            header.setScaleX(1 - mDragOffset / 2);
            header.setScaleY(1 - mDragOffset / 2);
            
			requestLayout();
		}
		
		/**
		 * ���ÿɴ�ֱ�϶���y����Ϊ�϶�����ʵ��y���꣬
		 */
		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			// TODO Auto-generated method stub
			return top;
		}
		
		/**
		 * ���ÿ�ˮƽ�϶���x����Ϊ�϶�����ʵ��x����
		 */
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			int top = getPaddingTop();
			//y�����������ٶȣ������ٶ�Ϊ0������Ļ�°벿��
			if (yvel > 0 || (yvel == 0 && mDragOffset > 0.5f)) {
				top += mDragRange;
			}
			mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
			invalidate();
		}
	}
}