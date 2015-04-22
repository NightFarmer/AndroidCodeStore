package com.nightfarmer.dragformore;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoadingTopLayout extends RelativeLayout{

	private TextView loadingMsg;
	public State state;

	public LoadingTopLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public LoadingTopLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LoadingTopLayout(Context context) {
		this(context, null);
	}

	@Override
	protected void onFinishInflate() {
		loadingMsg = (TextView) findViewById(R.id.loading_msg);
	}
	
	/**
	 * 设置状态：释放刷新
	 */
	public void setStateToDown(){
		state = State.DOWN;
		loadingMsg.setText("释放刷新");
	}
	
	/**
	 * 设置状态：下拉刷新
	 */
	public void setStateToUp(){
		state = State.UP;
		loadingMsg.setText("下拉刷新");
	}
	
	/**
	 * 设置状态：正在刷新
	 */
	public void setStateToLoading(){
		state = State.LOADING;
		loadingMsg.setText("正在刷新");
	}
	
	/**
	 * 设置状态：刷新完成
	 */
	public void setStateToLoaded(boolean success){
		state = State.LOADED;
		String msg = "刷新完成";
		if (!success) {
			msg = "刷新超时";
		}
		loadingMsg.setText(msg);
	}
	
	public enum State{
		UP,DOWN,LOADING,LOADED
	}
}
