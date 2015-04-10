package com.nightfarmer.draglayout.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.nightfarmer.draglayout.layout.DragLayout.DragState;

public class DragMainLayout extends RelativeLayout{

	private DragLayout dragLayout;
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
	
	public void setDragLayout(DragLayout dragLayout){
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
		
		
		if (DragState.OPEN==state) {
			if (event.getAction() != MotionEvent.ACTION_UP) {
				preAction = event.getAction();
			}else if(preAction != MotionEvent.ACTION_MOVE) {
				dragLayout.close();
			}
			return true;
		}
		if (DragState.DRAG == state) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				dragLayout.close();
			}
			return true;
		}
		return super.onTouchEvent(event);
	}
	
}
