package src.com.rcp.day;

import java.util.Calendar;

import com.umeng.fb.NotificationType;
import com.umeng.fb.UMFeedbackService;
import com.umeng.update.UmengDownloadListener;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

import rcp.com.other.ActivityMeg;
import rcp.com.src.brithUtil.otherUtil;
import src.com.rcp.wheelview.ScreenInfo;
import src.com.rcp.wheelview.WheelMain;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class more extends Activity {

	/** 提醒设置 */
	private LinearLayout more_remind;

	private TextView tv_remindTime;
	/** 帐号设置 */
	private LinearLayout more_account;

	/** 密码 */
	private ToggleButton tb_password;

	/*** 通知栏常驻 */
	private ToggleButton tb_notification;

	private int isEnter;

	/** 检查更新 */
	private LinearLayout update;
	/** 用户反馈 */
	private LinearLayout Fbhome;
	private static final String LOG_TAG = more.class.getName();

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_4);
		ActivityMeg.getInstance().addActivity(this);
		viewInit();

	}

	public void viewInit() {
		more_remind = (LinearLayout) this.findViewById(R.id.more_remind);
		more_remind.setOnClickListener(onClickListener);
		tv_remindTime = (TextView) this.findViewById(R.id.more_tixingTime);
		more_account = (LinearLayout) this.findViewById(R.id.more_account);
		more_account.setOnClickListener(onClickListener);
		tb_password = (ToggleButton) this.findViewById(R.id.moreset_uselogcode);
		tb_password.setOnCheckedChangeListener(onCheckedChangeListener);
		tb_password.setOnClickListener(onClickListener);
		tb_notification = (ToggleButton) this
				.findViewById(R.id.moreset_notification);
		tb_notification.setOnCheckedChangeListener(onCheckedChangeListener);
		update = (LinearLayout) this.findViewById(R.id.more_about);
		update.setOnClickListener(listener);
		Fbhome = (LinearLayout) this.findViewById(R.id.more_feedback);
		Fbhome.setOnClickListener(onClickListener);

	}

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

	OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			int id = buttonView.getId();
			if (id == R.id.moreset_notification) {
				if (isChecked) {
					setNotification();
				} else {
					cancelNotification();
				}
			} else if (id == R.id.moreset_uselogcode) {
				if ((isEnter == -1) && isChecked) {
					Intent code = new Intent(more.this, CodeNum.class);
					code.putExtra("kaiguan", 0);
					more.this.startActivity(code);
				} else if ((isEnter == 1) && (isChecked == false)) {
					Intent code = new Intent(more.this, CodeNum.class);
					code.putExtra("kaiguan", 1);
					more.this.startActivity(code);
				}
			}
		}
	};

	public void isKaiqiPassword() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);

		isEnter = sharedPreferences.getInt("iskaiqi", -1);

		if (isEnter == 1) {
			tb_password.setChecked(true);
		} else {
			tb_password.setChecked(false);
		}

		SharedPreferences sharedPre = getSharedPreferences("dingshitixing",
				MODE_PRIVATE);
		String time = sharedPre.getInt("hours", -1) + "-"
				+ sharedPre.getInt("min", -1);
		if (sharedPre.getInt("hours", -1) != -1) {

			tv_remindTime.setText("生日提醒时间:" + time);
		} else {
			tv_remindTime.setText("提醒时间设置");
		}
		Log.e("isEnter====>>>>", time);
	}

	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.more_remind) {
				setmore_remind();
				Log.e("----------->>>", "more_remind被点击");
			} else if (id == R.id.more_feedback) {
				Log.e("9999", "66666");
				UMFeedbackService.enableNewReplyNotification(more.this,
						NotificationType.NotificationBar);
				UMFeedbackService.setGoBackButtonVisible();
				UMFeedbackService.openUmengFeedbackSDK(more.this);
			}

		}

	};

	private View.OnClickListener listener = new View.OnClickListener() {
		public void onClick(View v) {

			// 如果想程序启动时自动检查是否需要更新， 把下面两行代码加在Activity 的onCreate()函数里。
			com.umeng.common.Log.LOG = true;
			UmengUpdateAgent.setUpdateOnlyWifi(false); // 目前我们默认在Wi-Fi接入情况下才进行自动提醒。如需要在其他网络环境下进行更新自动提醒，则请添加该行代码
			UmengUpdateAgent.setUpdateAutoPopup(false);
			UmengUpdateAgent.setUpdateListener(updateListener);

			UmengUpdateAgent.setOnDownloadListener(new UmengDownloadListener() {

				public void OnDownloadEnd(int result) {
					Log.i(LOG_TAG, "download result : " + result);
					Toast.makeText(more.this, "download result : " + result,
							Toast.LENGTH_SHORT).show();
				}

			});

			UmengUpdateAgent.update(more.this);
		}
	};

	UmengUpdateListener updateListener = new UmengUpdateListener() {

		public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
			switch (updateStatus) {
			case 0: // has update
				Log.i("--->", "callback result");
				UmengUpdateAgent.showUpdateDialog(more.this, updateInfo);
				break;
			case 1: // has no update
				Toast.makeText(more.this, "没有更新", Toast.LENGTH_SHORT).show();
				break;
			case 2: // none wifi
				Toast.makeText(more.this, "没有wifi连接， 只在wifi下更新",
						Toast.LENGTH_SHORT).show();
				break;
			case 3: // time out
				Toast.makeText(more.this, "超时", Toast.LENGTH_SHORT).show();
				break;
			case 4: // is updating
				/*
				 * Toast.makeText(mContext, "正在下载更新...", Toast.LENGTH_SHORT)
				 * .show();
				 */
				break;
			}

		}
	};

	// 添加常驻通知
	private void setNotification() {
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher,
				getString(R.string.app_name), System.currentTimeMillis());
		Intent intent = new Intent(this, Rcp_birthdayActivity.class);
		notification.flags = Notification.FLAG_ONGOING_EVENT; // 设置常驻 Flag
		PendingIntent contextIntent = PendingIntent.getActivity(this, 0,
				intent, 0);
		notification.setLatestEventInfo(getApplicationContext(),
				getString(R.string.app_name), "点击查看", contextIntent);
		notificationManager.notify(R.string.app_name, notification);
	}

	// 取消通知
	private void cancelNotification() {
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(R.string.app_name);
	}

	/***
	 * 
	 * 设置提醒方式
	 * 
	 */
	public void setmore_remind() {
		LayoutInflater inflater = LayoutInflater.from(more.this);
		View timepickerview = inflater.inflate(R.layout.selectbirthday, null);
		ScreenInfo screenInfo = new ScreenInfo(more.this);
		final WheelMain wheelMain = new WheelMain(timepickerview);
		wheelMain.screenheight = screenInfo.getHeight();
		Calendar calendar = Calendar.getInstance();
		wheelMain.showHours(calendar.get(Calendar.HOUR),
				calendar.get(Calendar.MINUTE));
		final Dialog dialog1 = new AlertDialog.Builder(this).setView(
				timepickerview).show();
		Window window = dialog1.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystyle); // 添加动画

		Button btn = (Button) timepickerview
				.findViewById(R.id.btn_datetime_sure);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String info = wheelMain.getHours() + "-" + wheelMain.getMin();
				tv_remindTime.setText("生日提醒时间:" + info);

				SharedPreferences sharedPreferences = getSharedPreferences(
						"dingshitixing", MODE_PRIVATE);
				// 获得编辑权限
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putInt("hours", wheelMain.getHours());
				editor.putInt("min", wheelMain.getMin());
				editor.commit();
				dialog1.dismiss();
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		isKaiqiPassword();
	}

}
