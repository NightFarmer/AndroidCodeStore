package src.com.rcp.day;

import java.util.Calendar;

import rcp.com.other.ActivityMeg;
import rcp.com.receiver.rcpAlarmReceiver;
import rcp.com.src.brithUtil.otherUtil;
import rcp.com.src.volues.sqlVolue;
import src.com.rcp.Sql.sql_brith;
import src.com.rcp.photo.Rcp_IndexSearchActivity;
import src.com.rcp.wheelview.ScreenInfo;
import src.com.rcp.wheelview.WheelMain;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class rcp_msg_wish extends Activity {

	/** 短信内容 **/
	private String smsInfo, sms_colleat;

	/** 短信编辑框 **/
	private EditText ed_sms, ed_phone;

	/** 发送短信按钮 */
	private Button btnSend;
	/** 跳转到手机联系人* */
	private Button toPhone;

	private ToggleButton tb_dingshi;

	/** 打开收藏 */
	private Button soucang1;

	public static final int SEND_SEARCH_PHONE = 5;// 向联系人
	public static final int SEND_SEARCH_SMS = 6;// 向联系人

	private int brithID;

	private LinearLayout sendDate;

	private TextView tv_sendTime, tv_sendDate;

	private Calendar c;

	private String _phone;

	private Intent sms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.sendsms_edit);
		sms = getIntent();
		_phone = sms.getStringExtra("phone");
		viewInit();
	}

	public void dataInit() {
		getDateTime();
	};

	public void viewInit() {
		ed_sms = (EditText) this.findViewById(R.id.smscontent);
		ed_phone = (EditText) this.findViewById(R.id.tv_phone_number);
		btnSend = (Button) this.findViewById(R.id.btn_action);
		btnSend.setOnClickListener(onClickListener);
		toPhone = (Button) this.findViewById(R.id.importcontactphone);
		toPhone.setOnClickListener(onClickListener);
		soucang1 = (Button) this.findViewById(R.id.btn_favos11);
		soucang1.setOnClickListener(onClickListener);
		tb_dingshi = (ToggleButton) this.findViewById(R.id.timingToggle);
		tb_dingshi.setOnCheckedChangeListener(CheckedChangeListener);

		sendDate = (LinearLayout) this.findViewById(R.id.timing_setting_layout);
		tv_sendTime = (TextView) this.findViewById(R.id.tv_time);
		tv_sendTime.setOnClickListener(onClickListener);
		tv_sendDate = (TextView) this.findViewById(R.id.tv_day);
		tv_sendDate.setOnClickListener(onClickListener);
	}

	OnCheckedChangeListener CheckedChangeListener = new OnCheckedChangeListener() {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
				Log.e("===", "我是定时广播");
				// new Thread(rcp_msg_wish.this).start();
				sendDate.setVisibility(View.VISIBLE);
				btnSend.setText("保存定时短信");
			} else {
				sendDate.setVisibility(View.GONE);
				btnSend.setText("发送");
			}
		}

	};

	private OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {

			int id = v.getId();
			if (id == R.id.tv_time) {
				Log.e("bbbbbbbbbb", "bbbbbbbbbbbb");
				setmore_remind();
			} else if (id == R.id.btn_favos11) {
				Toast.makeText(rcp_msg_wish.this, "ext1", 0).show();
				Log.e("vvvvvv", "ffffff");
				// Intent intent = new Intent(rcp_msg_wish.this,
				// colleatACtivity.class);
				// rcp_msg_wish.this.startActivity(intent);
				Intent intent = new Intent();
				intent.setClass(rcp_msg_wish.this,
						colleatACtivity.class);
				startActivityForResult(intent, SEND_SEARCH_SMS);
			} else if (id == R.id.btn_action) {
				if (ed_phone.getText().toString().length() <= 10) {
					Toast.makeText(rcp_msg_wish.this, "你这填的是号码吗?", 1).show();
					return;
				}
				if (ed_sms.getText().toString().length() <= 0) {
					Toast.makeText(rcp_msg_wish.this, "哥,你总德发点东东吧.", 1).show();
					return;
				}
				if ("立即发送".equals(btnSend.getText().toString())) {
					Intent intent = new Intent(Intent.ACTION_VIEW);
					// 信使压入短信内容
					intent.putExtra("sms_body", ed_sms.getText().toString());
					// 友盟统计
					intent.putExtra("address", ed_phone.getText().toString());
					// 短信界面的类型
					intent.setType("vnd.android-dir/mms-sms");
					startActivity(intent);
					finish();
				} else {

					Log.e("=============", tv_sendTime.getText().toString());
					dingshiSendSMS();
					Intent intent = new Intent(rcp_msg_wish.this,
							Rcp_birthdayActivity.class);
					rcp_msg_wish.this.startActivity(intent);
				}
			} else if (id == R.id.importcontactphone) {
				Intent intent = new Intent();
				intent.putExtra("add_index_phone", 2);
				intent.setClass(rcp_msg_wish.this,
						Rcp_IndexSearchActivity.class);
				startActivityForResult(intent, SEND_SEARCH_PHONE);
			}

		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case SEND_SEARCH_PHONE: {
			if (data == null) {
				System.out.println("oooooooooooooooooooooooooooo");
				break;
			}
			Bundle b = data.getExtras(); // data为B中回传的Intent
			String str = b.getString("add_phone");
			_phone = str;
			Log.e("ggggggg", _phone);
			ed_phone.setText(str);
		}
			break;

		case SEND_SEARCH_SMS: {		
			if (data == null) {
				System.out.println("oooooooooooooooooooooooooooo");
				break;
			}
			Bundle b = data.getExtras(); // data为B中回传的Intent
			String str = b.getString("colleatInfo");		
			Log.e("gggggffffffff", str);
			ed_sms.setText(str);
		}
			break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/***
	 * 得到Sms_info传过来的短信内容
	 * 
	 */
	public void getSmsInfo() {

		smsInfo = sms.getStringExtra("sms");

		ed_phone.setText(_phone);
		brithID = sms.getIntExtra("brithID", -1);
		if (smsInfo != null) {
			ed_sms.setText(smsInfo);
		}
		System.out.println(smsInfo + "=====" + sms_colleat + "===" + ed_phone);
	}

	public void dingshiSendSMS() {

		Intent intent = new Intent(this, rcpAlarmReceiver.class);
		intent.putExtra("phone", ed_phone.getText().toString());
		intent.putExtra("sms", ed_sms.getText().toString());
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
		// 设置一个PendingIntent对象，发送广播
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		// 获取AlarmManager对象
		am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);

	}

	public void getDateTime() {
		int year = 0;
		int month = 0;
		int date = -1;
		int hours = -1;
		int min = -1;
		SharedPreferences sharedPreferences = getSharedPreferences(
				"dingshitixing", MODE_PRIVATE);
		hours = sharedPreferences.getInt("hours", -1);
		min = sharedPreferences.getInt("min", -1);
		Cursor cursor = readInfo(brithID + "");
		// 游标下标归零
		cursor.moveToPosition(0);
		// 取游标中的值
		while (true) {
			// 判断是否在最后
			if (cursor.isAfterLast()) {
				break;
			}
			month = Integer.parseInt(cursor.getString(6));
			date = Integer.parseInt(cursor.getString(7));
			cursor.moveToNext();
		}
		c = Calendar.getInstance();
		System.out.println(c.getTime().getYear());

		year = otherUtil.getCurYear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);// 也可以填数字，0-11,一月为0
		c.set(Calendar.DAY_OF_MONTH, date);
		if (hours != -1) {
			c.set(Calendar.HOUR_OF_DAY, hours);
			c.set(Calendar.MINUTE, min);
			c.set(Calendar.SECOND, 0);
		} else {
			c.set(Calendar.HOUR_OF_DAY, 12);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
		}
		Log.e("--", c.getTimeInMillis() + "---" + System.currentTimeMillis());

		Log.e("mmmmmmmmmm", month + "-" + date + "-" + hours + "-" + min);

		tv_sendDate.setText(year + "年" + month + "月" + date + "日(生日当天)");
		tv_sendTime.setText(hours + ":" + min);
	}

	/***
	 * 
	 * 读取生日内容
	 * 
	 */
	public Cursor readInfo(String ID) {

		Cursor cursor = new sql_brith(this).db.query(sqlVolue.TABLE_brith_name,
				null, "_id" + "=?", new String[] { ID }, null, null, null);

		return cursor;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		ActivityMeg.getInstance().removeActivity(this);
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		getSmsInfo();
		dataInit();
		super.onResume();
	}

	/***
	 * 
	 * 设置提醒方式
	 * 
	 */
	public void setmore_remind() {
		LayoutInflater inflater = LayoutInflater.from(rcp_msg_wish.this);
		View timepickerview = inflater.inflate(R.layout.selectbirthday, null);
		ScreenInfo screenInfo = new ScreenInfo(rcp_msg_wish.this);
		final WheelMain wheelMain = new WheelMain(timepickerview);
		wheelMain.screenheight = screenInfo.getHeight();
		Calendar calendar = c;
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
				tv_sendTime.setText(info);
				c.set(Calendar.HOUR_OF_DAY, wheelMain.getHours());
				c.set(Calendar.MINUTE, wheelMain.getMin());
				dialog1.dismiss();
			}
		});
	}

}
