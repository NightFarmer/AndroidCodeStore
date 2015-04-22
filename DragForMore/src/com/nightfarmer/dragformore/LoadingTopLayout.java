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
	 * ����״̬���ͷ�ˢ��
	 */
	public void setStateToDown(){
		state = State.DOWN;
		loadingMsg.setText("�ͷ�ˢ��");
	}
	
	/**
	 * ����״̬������ˢ��
	 */
	public void setStateToUp(){
		state = State.UP;
		loadingMsg.setText("����ˢ��");
	}
	
	/**
	 * ����״̬������ˢ��
	 */
	public void setStateToLoading(){
		state = State.LOADING;
		loadingMsg.setText("����ˢ��");
	}
	
	/**
	 * ����״̬��ˢ�����
	 */
	public void setStateToLoaded(boolean success){
		state = State.LOADED;
		String msg = "ˢ�����";
		if (!success) {
			msg = "ˢ�³�ʱ";
		}
		loadingMsg.setText(msg);
	}
	
	public enum State{
		UP,DOWN,LOADING,LOADED
	}
}
