package com.nightfarmer.draglayout.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class DragMainLayout extends RelativeLayout{
	
	public DragMainLayout(Context context){
		this(context, null);
	}
	
	public DragMainLayout(Context context, AttributeSet attrs){
		this(context, attrs, 0);
	}

	public DragMainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

}
