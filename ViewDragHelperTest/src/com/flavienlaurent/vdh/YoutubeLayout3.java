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
	 * �������������ʣ�0Ϊ�����ţ�1Ϊ����Ļ�Ҳ����ŵ���С
	 */
	private final float MAINSCALERATE = 0.5f;
	/**
	 * �������ƶ�������0.7Ϊ��߽��ܹ��ƶ����������Ļ����
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

//			//����view���ĸ���Ϊ��������
//			header.setPivotX(header.getWidth());
//			header.setPivotY(header.getHeight());
			
			//�������ż���
			
            float scaleM = 1 - MAINSCALERATE*left/header.getWidth();
			header.setScaleX(scaleM);
            header.setScaleY(scaleM);
//            desc.setPivotX(0);
//            desc.setPivotY(desc.getMeasuredHeight()/2f);
            /**
             * ��������������ź�left����ʵ����
             * ʹ�������leftλ����Ļ0.7λ��ʱ���˵����ż���Ϊ1
             */
			float scaleMenu = (1-MAINLOCATPERSONT/2)
					+ (left + header.getWidth() * (1 - scaleM) / 2)
					/ header.getWidth()/2;
			/**
			 * ��ֵΪ����ƫ�ƣ���˵����ż���Ϊ1ʱ��ƫ����Ϊ0��ƫ����Ϊ�˵��Ŀ�ȱ���
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
			//������ó��ұ߽繫ʽΪ 2*0.7*header.getWidth()/(2+0.5)
			//������ó��ұ߽繫ʽΪ 2*MAINLOCATPERSONT*header.getWidth()/(2+MAINSCALERATE)
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
