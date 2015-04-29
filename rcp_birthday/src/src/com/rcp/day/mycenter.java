package src.com.rcp.day;

import java.util.Calendar;

import rcp.com.receiver.rcpAlarmReceiver;
import rcp.com.src.brithUtil.imageUtil;
import rcp.com.src.brithUtil.otherUtil;
import rcp.com.src.volues.activityVolues;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class mycenter extends Activity implements Runnable {

	private boolean isread = false;

	/** 备份 **/
	private Button center[] = new Button[3];
	private int centerID[] = { R.id.show_mybirthday, R.id.backupcenter,
			R.id.show_lucky };

	/** 登录 */
	private Button btn_login;

	private Button btn_up;

	/** 未备份的数量 */
	private TextView HintNum;

	private ImageView img;

	private TextView name;

	private ImageView sex;

	private TextView email;

	private TextView brithday;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_3);
		Viewinit();

		// fasongReceiver();
	}

	public void Viewinit() {

		for (int i = 0; i < center.length; i++) {
			center[i] = (Button) this.findViewById(centerID[i]);
			center[i].setOnClickListener(onClickListener);
		}
		btn_login = (Button) this.findViewById(R.id.btn_modifydengl);
		btn_login.setOnClickListener(onClickListener);
		img = (ImageView) this.findViewById(R.id.avatar);
		name = (TextView) this.findViewById(R.id.backup_name);
		sex = (ImageView) this.findViewById(R.id.gender);
		email = (TextView) this.findViewById(R.id.email);
		brithday = (TextView) this.findViewById(R.id.backup_birthday);
		HintNum = (TextView) this.findViewById(R.id.hint_num);
		btn_up = (Button) this.findViewById(R.id.btn_modify);
		btn_up.setOnClickListener(onClickListener);
		// 当登录成功之后备份按钮才能点击

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

	/***
	 * 
	 * 读取用户信息
	 * 
	 */
	public void readShape() {

		SharedPreferences sharedPreferences = getSharedPreferences(
				activityVolues.shape_name, MODE_PRIVATE);

		String img1 = sharedPreferences.getString("photo", "null");

		String sex1 = sharedPreferences.getString("sex", "男");

		Bitmap bitmap = new imageUtil().getBitmapTodifferencePath(img1, this);
		if (bitmap != null) {
			img.setImageBitmap(bitmap);
		} else {
			img.setBackgroundResource(sex1.equals("男") ? R.drawable.defaultboy
					: R.drawable.defaultgirl);
		}
		String name1 = sharedPreferences.getString("name", "我");
		name.setText(name1);
		String laodname1 = sharedPreferences.getString("email", "1111111111");
		email.setText(laodname1);
		String year = sharedPreferences.getString("year", "1992");

		String month = sharedPreferences.getString("month", "02");

		String date = sharedPreferences.getString("date", "26");
		brithday.setText(year + "年" + month + "月" + date + "日");

		sex.setBackgroundResource(sex1.equals("男") ? R.drawable.boy
				: R.drawable.grid);
		// 当未备份的人数为0的时候,不显示小红点
		if (activityVolues.backupCount <= 0) {
			HintNum.setVisibility(View.GONE);
		} else {
			HintNum.setText(activityVolues.backupCount + "");
		}
	}

	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.show_mybirthday) {
				Intent intent=new Intent(mycenter.this, brithInfo.class);
				mycenter.this.startActivity(intent);
			} else if (id == R.id.backupcenter) {
				if (activityVolues.isLoadOK) {
					Intent intent = new Intent(mycenter.this,
							backupActivity.class);
					mycenter.this.startActivity(intent);
				} else {
					Toast.makeText(mycenter.this, "请先登录", 0).show();
				}
			} else if (id == R.id.show_lucky) {
			} else if (id == R.id.btn_modifydengl) {
				Intent intent = new Intent(mycenter.this, Load.class);
				mycenter.this.startActivity(intent);
			} else if (id == R.id.btn_modify) {
				if (activityVolues.isLoadOK) {
					Intent intent = new Intent(mycenter.this,
							rcp_addBrithday.class);
					intent.putExtra("center", 100);
					mycenter.this.startActivity(intent);
				} else {
					Toast.makeText(mycenter.this, "请先登录", 0).show();
				}
			}

		}

	};

	public void fasongReceiver() {

		if (activityVolues.isLoadOK) {
			Calendar c = Calendar.getInstance();
			// c.set(Calendar.YEAR, 2013);
			// c.set(Calendar.MONTH, Calendar.JUNE);// 也可以填数字，0-11,一月为0
			// c.set(Calendar.DAY_OF_MONTH, 4);
			// c.set(Calendar.HOUR_OF_DAY, 12);
			// c.set(Calendar.MINUTE, 20);
			// c.set(Calendar.SECOND, 28);
			// 设定时间为 2011年6月28日19点50分0秒
			c.set(otherUtil.getCurYear(), 4, 16, 22, 50, 0);
			// 也可以写在一行里

			Intent intent = new Intent(this, rcpAlarmReceiver.class);
			intent.setAction("src.com.rcp.AlarmReceiver");
			PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
			// 设置一个PendingIntent对象，发送广播
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			// 获取AlarmManager对象
			am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);

			// 时间到时，执行PendingIntent，只执行一次
			// AlarmManager.RTC_WAKEUP休眠时会运行，如果是AlarmManager.RTC,在休眠时不会运行
			// am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
			// 10*1000, pi);
			// 如果需要重复执行，使用上面一行的setRepeating方法，倒数第二参数为间隔时间,单位为毫秒

		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// isread = getIntent().getBooleanExtra("isread", false);

		System.out.println("=====----------" + activityVolues.isLoadOK);
		new Thread(this).start();
		System.gc();
		Log.e("11111111111111111", isread + "");
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			readShape();
		}

	};

	public void run() {
		if (activityVolues.isLoadOK) {
			handler.sendEmptyMessage(0);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
		Log.e("----------uuu", "myCenter_消亡");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e("----------uuu", "myCenter_暂停");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e("----------uuu", "myCenter_start");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		Log.e("----------uuu", "myCenter_stop");
	}

}
