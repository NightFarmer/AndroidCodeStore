package com.flavienlaurent.vdh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.flavienlaurent.vdh.YoutubeLayout3.DragState;

public class DragMainLayout extends RelativeLayout{

	private YoutubeLayout3 dragLayout;
	private int preAction;

	public DragMainLayout(Context context) {
		this(context, null);
	}

	public DragMainLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DragMainLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setDragLayout(YoutubeLayout3 dragLayout){
		this.dragLayout = dragLayout;
	}
	
	/**
	 * 如果点击到本viewgroup的子控件，是否把touch转交给子控件。返回true则拦截，拦截够会触发这个group的touch事件
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		DragState state = dragLayout.getState();
		if (!DragState.CLOSE.equals(state)) {
			return true;
		}
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		DragState state = dragLayout.getState();
		Log.i("qq", ""+state+"-"+event.getAction()+"-"+dragLayout.headerLeft);
		if (DragState.OPEN.equals(state)) {
			if (event.getAction() != MotionEvent.ACTION_UP) {
				preAction = event.getAction();
			}else if(preAction != MotionEvent.ACTION_MOVE) {
				dragLayout.close();
			}
			return true;
		}
		if (!DragState.CLOSE.equals(state)) {
			if (event.getAction() != MotionEvent.ACTION_UP) {
				preAction = event.getAction();
			}
//			if(preAction != MotionEvent.ACTION_MOVE) {
			if (event.getAction() == MotionEvent.ACTION_CANCEL || (event.getAction() == MotionEvent.ACTION_UP && preAction != MotionEvent.ACTION_MOVE)) {
				dragLayout.close();
			}
//			}
			return true;
		}
		return super.onTouchEvent(event);
	}
	
}
