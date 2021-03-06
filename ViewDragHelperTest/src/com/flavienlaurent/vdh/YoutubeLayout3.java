package com.flavienlaurent.vdh;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nineoldandroids.view.ViewHelper;

public class YoutubeLayout3 extends FrameLayout{

	private ViewDragHelper mDragHelper;
	private DragMainLayout header;
	private LinearLayout desc;
	
	private int maxLeft;
	public int headerLeft;
	public int menuMineLeft;
	
	/**
	 * 主界面缩放速率，0为不缩放，1为到屏幕右侧缩放到最小
	 */
	private final float MAINSCALERATE = 0.5f;
	/**
	 * 主界面移动比例，0.7为左边界能够移动到的最大屏幕比例，根据主界面缩小后的可视左边界为参照物
	 */
	private final float MAINLOCATPERSONT = 0.7f;
	/**
	 * 根据上面两个参数计算出主界面能移动到上面最大比例时，位移和屏幕宽度的比值
	 */
	private final float PARAM;
	
	private int x_down;
	private int y_down;
	private GestureDetectorCompat gestureDetector;

	public YoutubeLayout3(Context context) {
		this(context, null);
	}

	public YoutubeLayout3(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public YoutubeLayout3(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//经计算得出右边界公式为 2*MAINLOCATPERSONT*header.getWidth()/(2+MAINSCALERATE)
		PARAM = 2*MAINLOCATPERSONT/(2+MAINSCALERATE);
		mDragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
		gestureDetector = new GestureDetectorCompat(context,
				new YScrollDetector());
	}
	
	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx,
				float dy) {
			return Math.abs(dy) <= Math.abs(dx);
		}
	}
	
	@Override
	protected void onFinishInflate() {
		header = (DragMainLayout) findViewById(R.id.header);
//		desc = findViewById(R.id.desc);
		desc = (LinearLayout) getChildAt(0);
		if (header!=null) {
			header.setDragLayout(this);
		}
		desc.setClickable(true);
		header.setClickable(true);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		try {
			mDragHelper.processTouchEvent(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			x_down = (int) event.getX();
//			y_down = (int) event.getY();
//		}
//		if (event.getAction() == MotionEvent.ACTION_UP) {
//			int x = (int) event.getX();
//			int y = (int) event.getY();
//			if (Math.abs(x-x_down)==0 && Math.abs(y-y_down)==0) {
//				float mainLeft = header.getLeft()+(header.getWidth()/2)*(1-ViewHelper.getScaleX(header));
//				float mainTop = header.getHeight()/2*(1-ViewHelper.getScaleY(header));
//				float mainButtom = header.getHeight() - mainTop;
//				if (getState().equals(DragState.OPEN) && x>mainLeft && y>mainTop && y<mainButtom ) {
//					close();
//				}
//			}
//		}
		return true;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return mDragHelper.shouldInterceptTouchEvent(ev)&& gestureDetector.onTouchEvent(ev);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}
	
	@Override
	public void computeScroll() {
		if (mDragHelper.continueSettling(true)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}
	
	public void close(){
		if (mDragHelper.smoothSlideViewTo(header, 0, 0)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}
	
	public void open() {
		if (mDragHelper.smoothSlideViewTo(header, (int) (PARAM*header.getWidth()), 0)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}
	
	public DragState getState() {
		if (headerLeft==0) {
			return DragState.CLOSE;
		}else if (headerLeft==maxLeft) {
			return DragState.OPEN;
		}
		return DragState.DRAG;
	}
	
	public static enum DragState{
		CLOSE,DRAG,OPEN
	}
	
	
	private class DragHelperCallback extends ViewDragHelper.Callback{


		@Override
		public boolean tryCaptureView(View arg0, int arg1) {
			// TODO Auto-generated method stub
//			return arg0.getId()==header.getId();
			Log.i("qq", "v4="+arg0.getId());
			return true;
		}
		
		@Override
		public int getViewHorizontalDragRange(View child) {
			return 1;
		}
		
		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {

//			//设置view以哪个点为中心缩放
//			header.setPivotX(header.getWidth());
//			header.setPivotY(header.getHeight());
			
			//设置缩放级别
			if (header.getId() == changedView.getId()) {
				headerLeft=left;
				float scaleM = 1 - MAINSCALERATE*left/header.getWidth();
//			header.setScaleX(scaleM);
//          header.setScaleY(scaleM);
				ViewHelper.setScaleX(header, scaleM);
				ViewHelper.setScaleY(header, scaleM);
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
//			desc.setTranslationX(-(1-scaleMenu)*desc.getWidth());
//				ViewHelper.setTranslationX(desc, -(1-scaleMenu)*desc.getWidth());
				desc.layout((int)(-(1-scaleMenu)*desc.getWidth()), 0, (int) (Math.ceil(-(1-scaleMenu)*getWidth()+getWidth())), desc.getHeight());
//			desc.setScaleX(scaleMenu);
//			desc.setScaleY(scaleMenu);
				ViewHelper.setScaleX(desc, scaleMenu);
				ViewHelper.setScaleY(desc, scaleMenu);
//            desc.layout(0, 0, desc.getMeasuredWidth(), desc.getMeasuredHeight());
//            requestLayout();
			}else {
//				float scaleMenu = (desc.getWidth()+left*1f)/desc.getWidth();
				float scaleMenu = left*1f/desc.getWidth()+1;
				ViewHelper.setScaleX(desc, scaleMenu);
				ViewHelper.setScaleY(desc, scaleMenu);
				Log.i("py", left+ "="+scaleMenu);
				float mainLeft = (scaleMenu - (1-MAINLOCATPERSONT/2))*2*getWidth()*getWidth()*2/(getWidth()*2 + getWidth() * MAINSCALERATE);
				Log.i("py", ""+mainLeft);
				header.layout((int)mainLeft, 0, (int) (mainLeft+getWidth()), getHeight());
//				ViewHelper.setTranslationX(header, mainLeft);
				float scaleM = 1 - MAINSCALERATE*mainLeft/header.getWidth();
				ViewHelper.setScaleX(header, scaleM);
				ViewHelper.setScaleY(header, scaleM);
			}
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
//			double maxLeft = 0.56*header.getWidth();
			if (child.getId() == header.getId()) {
				maxLeft = (int) (PARAM*header.getWidth());
				if (child.getLeft()==0) {
					float scaleMenu = (1-MAINLOCATPERSONT/2);
					menuMineLeft = (int)(-(1-scaleMenu)*desc.getWidth());
				}
				if (left>maxLeft) {
					return (int) (PARAM*header.getWidth());
				}
				if (left<=0) {
					return 0;
				}
			}else {
				if (left>0) {
					return 0;
				}
				if (left<menuMineLeft) {
					return menuMineLeft;
				}
			}
			return left;
		}
		
		
		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			super.onViewReleased(releasedChild, xvel, yvel);
			Log.i("qq", "v="+xvel+"-"+yvel);
			if (xvel > 0) {
				//可跟进手势快慢过渡，但是不流畅
//				if (releasedChild == header ) {
//					mDragHelper.settleCapturedViewAt(maxLeft, 0);
//					invalidate();
//					return;
//				}
				open();
			} else if (xvel < 0) {
//				if (releasedChild == desc ) {
					close();
//					return;
//				}
				//可跟进手势快慢过渡，但是不流畅
//				mDragHelper.settleCapturedViewAt(0, 0);
//				invalidate();
			} else if (releasedChild == header && header.getLeft() > header.getWidth() * 0.4) {
				open();
			} else if (releasedChild == desc && header.getLeft() > header.getWidth() * 0.4) {
				open();
			} else {
				close();
			}
		}
	}
}
