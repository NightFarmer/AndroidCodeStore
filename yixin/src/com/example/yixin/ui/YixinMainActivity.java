package com.example.yixin.ui;

import com.example.yixin.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @author kince
 * 
 */
public class YixinMainActivity extends SlidingFragmentActivity implements
		OnClickListener {

	//
	protected SlidingMenu mSlidingMenu;
	//
	private ImageButton ivTitleBtnLeft;
	//
	private ImageButton ivTitleBtnRigh;
	//
	private Fragment mContent;
	//
	public static TextView mTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSlidingMenu();
		setContentView(R.layout.activity_main);
		initView();

	}

	private void initView() {
		ivTitleBtnLeft = (ImageButton) this.findViewById(R.id.ivTitleBtnLeft);
		ivTitleBtnLeft.setOnClickListener(this);
		ivTitleBtnRigh = (ImageButton) this.findViewById(R.id.ivTitleBtnRigh);
		ivTitleBtnRigh.setOnClickListener(this);
		mTextView = (TextView) this.findViewById(R.id.ivTitleName);

	}

	private void initSlidingMenu() {
		// customize the SlidingMenu
		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置是左滑还是右滑
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// 设置菜单宽度
		mSlidingMenu.setFadeDegree(0.5f);// 设置淡入淡出的比例
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置手势模式
		mSlidingMenu.setShadowDrawable(R.drawable.shadow);// 设置左菜单阴影图片
		mSlidingMenu.setFadeEnabled(true);// 设置滑动时菜单的是否淡入淡出
		mSlidingMenu.setBehindScrollScale(0.5f);// 设置滑动时拖拽效果
		
		mContent = new FragmentYixin();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mContent).commit();

		setBehindContentView(R.layout.main_left_layout);
		 getSupportFragmentManager()
				.beginTransaction()
		.replace(R.id.main_left_fragment, new LeftSlidingMenuFragment())
		.commit();

		mSlidingMenu.setSecondaryMenu(R.layout.main_right_layout); // 右边侧滑的布局
		mSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadow);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.main_right_fragment,
						new RightSlidingMenuFragment()).commit(); //
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivTitleBtnLeft:
			mSlidingMenu.showMenu(true);
			break;
		case R.id.ivTitleBtnRigh:
			mSlidingMenu.showSecondaryMenu(true);
			break;
		default:
			break;
		}

	}

	/**
	 * 左侧菜单点击切换首页的内容
	 */
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
	}

}
