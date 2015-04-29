package src.com.rcp.day;

import java.util.HashMap;

import rcp.com.other.ActivityMeg;
import rcp.com.src.brithUtil.otherUtil;
import rcp.src.com.adapter.brithPerlistviewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.umeng.fb.NotificationType;
import com.umeng.fb.UMFeedbackService;

public class brith extends Activity implements Runnable {

	//
	/** 标题栏添加按钮 */
	private Button title_add;
	/** 标题栏视图切换按钮 **/
	private Button title_edit;

	/** 生日界面ListView **/
	private ListView brithListview;

	private boolean isEdit = false;

	private HashMap<Integer, Bitmap> hashMap;

	private brithPerlistviewAdapter brithPerlistviewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_1);
		UMFeedbackService.enableNewReplyNotification(this,
				NotificationType.AlertDialog);
		ActivityMeg.getInstance().addActivity(this);
		viewInit();
	}

	private LayoutAnimationController getListAnim() {
		AnimationSet set = new AnimationSet(true);
		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(300);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(500);
		set.addAnimation(animation);
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.5f);
		return controller;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new Thread(this).start();
		// System.gc();
	}

	public void viewInit() {

		hashMap = new HashMap<Integer, Bitmap>();
		brithListview = (ListView) this.findViewById(R.id.brith_listview);
		brithListview.setOnItemClickListener(onItemClickListener);
		brithListview.setOnLongClickListener(onLongClickListener);
		brithPerlistviewAdapter = new brithPerlistviewAdapter(
				getApplicationContext(), hashMap);
		brithListview.setAdapter(brithPerlistviewAdapter);
		// brithListview.setLayoutAnimation(getListAnim());
		TextView emptyView = new TextView(this);
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		emptyView.setGravity(Gravity.CENTER);
		emptyView.setText("赶紧添加属于你的生日管家吧、、");
		emptyView.setTextSize(20.0f);
		emptyView.setVisibility(View.GONE);
		((ViewGroup) brithListview.getParent()).addView(emptyView);
		brithListview.setEmptyView(emptyView);
		title_add = (Button) this.findViewById(R.id.title_addPeople);
		title_add.setOnClickListener(onClickListener);
		title_edit = (Button) this.findViewById(R.id.title_edit);
		title_edit.setOnClickListener(onClickListener);

	}

	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.title_addPeople) {
				// 离开当前Activity要关闭数据库
				// sqlDatebase.closeSQl();
				Intent intent = new Intent();
				intent.setClass(brith.this, rcp_addBrithday.class);
				startActivity(intent);
			} else if (id == R.id.title_edit) {
				if (!isEdit) {
					isEdit = true;
					title_edit.setText("完成");
				} else {
					isEdit = false;
					title_edit.setText("编辑");
				}
				brithPerlistviewAdapter.setEdit(isEdit);
				brithPerlistviewAdapter.notifyDataSetChanged();
			}
		}

	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			System.out.println("--------------------------");
			otherUtil.ExitApp(getApplicationContext());
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	OnLongClickListener onLongClickListener = new OnLongClickListener() {

		public boolean onLongClick(View v) {

			return false;
		}
	};

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent1 = new Intent(brith.this, brithInfo.class);
			intent1.putExtra("ItemID",
					brithPerlistviewAdapter.getItemID(position));
			brith.this.startActivity(intent1);
		}

	};

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			brithPerlistviewAdapter.dataInit();
			Log.e("bbbbbbbbb", "AAAAAAAAAAAAAAAA");
			brithPerlistviewAdapter.notifyDataSetChanged();

		}

	};

	public void run() {
		handler.sendEmptyMessage(0);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e("brith", "消亡");
		// freeBitmap(hashMap);

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("uuuuuuuuu" + hashMap.size());

	}

	private void freeBitmap(HashMap<Integer, Bitmap> cache) {

		if (cache.isEmpty()) {

			return;

		}

		Log.e("freeBitmap", "cache size=" + cache.size());

		for (Bitmap bitmap : cache.values()) {

			if (bitmap != null && !bitmap.isRecycled()) {
				bitmap.recycle();
				bitmap = null;// 这里最好加上这一句

				Log.e("freeBitmap", "=============recycle bitmap=======");
			}
			cache.clear();
		}
	}
}
