package com.flavienlaurent.vdh;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class DragLayout2 extends LinearLayout{

	private final ViewDragHelper mDragHelper;
	
	public DragLayout2(Context context) {
        this(context, null);
    }

    public DragLayout2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public DragLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, 1f, new Callback() {
			
			@Override
			public boolean tryCaptureView(View arg0, int arg1) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
	        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
//	            if (mDragEdge) {
	                mDragHelper.captureChildView(findViewById(R.id.drag1x), pointerId);
//	            }
	        }
			
			@Override
	        public int clampViewPositionVertical(View child, int top, int dy) {
//	            if (mDragVertical) {
	                final int topBound = getPaddingTop();
	                final int bottomBound = getHeight() - child.getHeight();

	                final int newTop = Math.min(Math.max(top, topBound), bottomBound);

	                return newTop;
//	            }
//	            return super.clampViewPositionVertical(child, top, dy);
	        }

	        @Override
	        public int clampViewPositionHorizontal(View child, int left, int dx) {
//	            if (mDragHorizontal || mDragCapture || mDragEdge) {
	                final int leftBound = getPaddingLeft();
	                final int rightBound = getWidth() - child.getWidth();

	                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

	                return newLeft;
//	            }
//	            return super.clampViewPositionHorizontal(child, left, dx);
	        }
			
		});
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT | ViewDragHelper.EDGE_ALL);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
    	// TODO Auto-generated method stub
//    	final int action = MotionEventCompat.getActionMasked(ev);
//        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//            mDragHelper.cancel();
//            return false;
//        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
//    	return super.onInterceptTouchEvent(ev);
    }
	
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	// TODO Auto-generated method stub
    	mDragHelper.processTouchEvent(event);
    	return true;
    }
}
