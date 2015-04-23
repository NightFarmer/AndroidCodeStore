package com.nightfarmer.dragformore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoadingTopLayout extends RelativeLayout{

	private TextView loadingMsg;
	private TextView loadedMsg;
	private View loading_view;
	public State state = null;
	private View loaded_result;
	private Context context;
	private View loading_icon;
	private RotateAnimation rotateAnimation_up;
	private RotateAnimation rotateAnimation_down_0;
	private RotateAnimation rotateAnimation_down;
	private View pull_icon;
	private RotateAnimation refreshingAnimation;

	public LoadingTopLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
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
		loadedMsg = (TextView) findViewById(R.id.loaded_msg);
		loading_view = findViewById(R.id.loading_view);
		loaded_result = findViewById(R.id.load_result);
		loading_icon = findViewById(R.id.loading_icon);
		pull_icon = findViewById(R.id.pull_icon);
		try {
			rotateAnimation_up = (RotateAnimation) AnimationUtils.loadAnimation(
					context, R.anim.reverse_anim);
			rotateAnimation_down = (RotateAnimation) AnimationUtils.loadAnimation(
					context, R.anim.reverse_anim2);
			refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
					context, R.anim.rotate_sircle);
			rotateAnimation_down_0 = (RotateAnimation) AnimationUtils.loadAnimation(
					context, R.anim.reverse_anim0);
			LinearInterpolator lir = new LinearInterpolator();
			refreshingAnimation.setInterpolator(lir);
			rotateAnimation_up.setInterpolator(lir);
			rotateAnimation_down.setInterpolator(lir);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 设置状态：释放刷新
	 */
	public void setStateToDown(){
//		if (State.UP == state) {
		pull_icon.startAnimation(rotateAnimation_up);
//		}
		
		state = State.DOWN;
		
		loaded_result.setVisibility(View.GONE);
		loading_view.setVisibility(View.VISIBLE);
		loading_icon.clearAnimation();
		loading_icon.setVisibility(View.GONE);
		pull_icon.setVisibility(View.VISIBLE);
		
		loadingMsg.setText("释放立即刷新");
	}
	
	/**
	 * 设置状态：下拉刷新
	 */
	public void setStateToUp(){
		if (state == null || state == State.LOADED) {
			pull_icon.startAnimation(rotateAnimation_down_0);
		}else {
			pull_icon.startAnimation(rotateAnimation_down);
		}
		state = State.UP;
		
		loaded_result.setVisibility(View.GONE);
		loading_view.setVisibility(View.VISIBLE);
		loading_icon.clearAnimation();
		loading_icon.setVisibility(View.GONE);
		pull_icon.setVisibility(View.VISIBLE);
		
		loadingMsg.setText("下拉刷新");
	}
	
	/**
	 * 设置状态：正在刷新
	 */
	public void setStateToLoading(){
		state = State.LOADING;
		loadingMsg.setText("正在刷新...");
		loading_icon.startAnimation(refreshingAnimation);
		loading_icon.setVisibility(View.VISIBLE);
		pull_icon.clearAnimation();
		pull_icon.setVisibility(View.GONE);
	}
	
	/**
	 * 设置状态：刷新完成
	 */
	public void setStateToLoaded(boolean success){
		state = State.LOADED;
		
		loading_view.setVisibility(View.GONE);
		loaded_result.setVisibility(View.VISIBLE);
		String msg = "刷新完成";
		if (!success) {
			msg = "刷新超时";
		}
		loadedMsg.setText(msg);
	}
	
	public enum State{
		UP,DOWN,LOADING,LOADED
	}
}
