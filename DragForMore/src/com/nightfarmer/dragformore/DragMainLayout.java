package com.nightfarmer.dragformore;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class DragMainLayout extends RelativeLayout implements Dragable{
	private State state;
	private LoadedListener listener;

	public DragMainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public DragMainLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DragMainLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		state = Dragable.State.LOADING;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (State.LOADING == state) {
					state = Dragable.State.NORMAL;
					listener.loaded(true);
				}
			}
		}).start();
	}


	@Override
	public State getState() {
		return state;
	}

	@Override
	public void setLoadedListener(LoadedListener l) {
		listener = l;
	}

	@Override
	public void cancle() {
		state = State.NORMAL;
	}

	@Override
	public boolean canDragDown() {
		return true;
	}


}
