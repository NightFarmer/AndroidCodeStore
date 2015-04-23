package com.nightfarmer.dragformore;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nightfarmer.dragformore.Dragable.LoadedListener;
import com.nightfarmer.dragformore.Dragable.State;

public class DragForMoreLayout extends FrameLayout{

	private ViewDragHelper dragHelper;
	private Context context;
	private View dragableView;
	private RelativeLayout topLayout;
	
	private int preTop = 0;
	private int loadingTopHeight;
	private LoadingTopLayout topLoading;
	private Handler handler;
	private boolean isFirst = true;
	private boolean drageReleased = false;
	private boolean scrollStop; 

	private boolean needStopScroleToLoading = true;
	
	public DragForMoreLayout(Context context) {
		this(context, null);
	}

	public DragForMoreLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DragForMoreLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		dragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallBack());
		handler = new Handler();
	}

	@Override
	protected void onFinishInflate() {
		LayoutInflater inflater = LayoutInflater.from(context);
		topLayout = (RelativeLayout) inflater.inflate(R.layout.loading_top, null);
//		topLayout = (RelativeLayout) getChildAt(0);
		addView(topLayout, 0);
		topLoading = (LoadingTopLayout) topLayout.getChildAt(0);
		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		topLayout.measure(w, h); 
		loadingTopHeight = topLayout.getMeasuredHeight();
		dragableView = this.getChildAt(1);
		
		((Dragable)dragableView).setLoadedListener(new LoadedListener() {
			
			@Override
			public void loaded(final boolean success) {
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						topLoading.setStateToLoaded(success);
					}
				});
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						if (topLoading.state==LoadingTopLayout.State.LOADED) {
							close();
							needStopScroleToLoading = false;
						}
					}
				}, 1000);
			}
		});
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		if (isFirst) {
			super.onLayout(changed, left, top, right, bottom);
			isFirst=false;
			return;
		}
		//topLayout.layout(0, preTop-topLayout.getMeasuredHeight(), topLayout.getMeasuredWidth(), preTop);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// if (!((Dragable)dragableView).canDragDown()) {
		// 	false;
		// }
		return dragHelper.shouldInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		dragHelper.processTouchEvent(event);
		return true;
	}
	
	@Override
	public void computeScroll() {
		scrollStop = false;
		if (needStopScroleToLoading && drageReleased && preTop<=loadingTopHeight) {
			scrollStop = true;
		}
		if (!scrollStop && dragHelper.continueSettling(true)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}
	
	public void close(){
		State state = ((Dragable)dragableView).getState();
		int top = 0;
		if (State.NORMAL == state) {
			top = 0;
		}else {
			top = loadingTopHeight/2;
			topLoading.setStateToLoading();
			needStopScroleToLoading = true;
		}
		if (dragHelper.smoothSlideViewTo(dragableView, 0, top)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}
	
	private class DragHelperCallBack extends ViewDragHelper.Callback{

		@Override
		public boolean tryCaptureView(View arg0, int arg1) {
			return arg0==dragableView;
		}
		
		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			super.onViewPositionChanged(changedView, left, top, dx, dy);
			if (changedView==dragableView) {
				if (!drageReleased) {
					if (top>loadingTopHeight && topLoading.state!=LoadingTopLayout.State.DOWN) {
						topLoading.setStateToDown();
						if (((Dragable)dragableView).getState()==Dragable.State.LOADING) {
							((Dragable)dragableView).cancle();
						}
					}
					if (top<loadingTopHeight && topLoading.state!=LoadingTopLayout.State.UP
							&& topLoading.state!=LoadingTopLayout.State.LOADED) {
						topLoading.setStateToUp();
					}
				}
				if (0==top) {
					topLoading.setStateToUp();
				}
				preTop = top;
				topLayout.layout(0, top-topLayout.getMeasuredHeight(), topLayout.getMeasuredWidth(), top);
			}
		}
		
		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			drageReleased = false;
			needStopScroleToLoading = false;
			float radio = (float) (2 + 3 * Math.tan(Math.PI / 2 / getMeasuredHeight()
					* (preTop)));
			int newTop = (int) (preTop + dy/radio);
			preTop = newTop;
			return newTop>=0?newTop:0;
		}
		
		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			super.onViewReleased(releasedChild, xvel, yvel);
			drageReleased = true;
			if (preTop>loadingTopHeight) {
				((Dragable)dragableView).load();
			}
			if (((Dragable)dragableView).getState()==Dragable.State.LOADING && preTop<loadingTopHeight) {
				((Dragable)dragableView).cancle();
//				needStopScroleToLoading = false;
			}
			close();
		}
	}

	
}
