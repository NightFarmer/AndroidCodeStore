package src.com.rcp.day;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rcp.com.other.ActivityMeg;
import rcp.com.src.brithUtil.imageUtil;
import rcp.com.src.brithUtil.otherUtil;
import rcp.com.src.volues.activityVolues;
import rcp.com.src.volues.sqlVolue;
import src.com.rcp.Sql.sql_brith;
import src.com.rcp.photo.Rcp_IndexSearchActivity;
import src.com.rcp.wheelview.ScreenInfo;
import src.com.rcp.wheelview.WheelMain;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/***
 * 添加生日界面
 * 
 * @author toshiba
 * 
 */
public class rcp_addBrithday extends Activity {

	/*** 自定义Dialog */
	private AlertDialog dialog;

	private WheelMain wheelMain;
	/** 需要隐藏的布局 */
	private LinearLayout layout;

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private AlertDialog alertDialog;

	/** 生日联系人属性容器 **/
	brith_ListItem data1 = new brith_ListItem();

	private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果

	public static final int SEND_SEARCH_NAME = 4;// 向联系人
	public static final int SEND_SEARCH_PHONE = 5;// 向联系人
	// 创建一个以当前时间为名称的文件
	File tempFile = new File(Environment.getExternalStorageDirectory()
			+ "/brithPhoto/", getPhotoFileName());

	String path = Environment.getExternalStorageDirectory() + "/brithPhoto/";

	private TextView tv_title;

	/** 设置生日按钮 */
	private Button btn_setBrith;
	/** 查找联系人按钮 **/
	private Button btn_lianxiren_name;

	/** 查找联系人按钮 **/
	private Button btn_lianxiren_phone;
	/** 保存按钮 **/
	private Button btn_save;
	/** 发送短信按钮 */
	private Button btn_sendSMS;
	/** 数据库操作对象 **/
	private sql_brith db_brith;
	/** add界面三个EditText */
	private EditText add_edit[] = new EditText[3];
	/** 是否成功保存 **/
	private boolean isOK;

	/** add界面三个EditTextID */
	private int EditTextID[] = { R.id.add_brith_name,
			R.id.add_brith_zhufuduanxin, R.id.add_brith_beizhu };
	private RadioButton nan, nv;

	private RadioGroup group;
	/** 修改头像 */
	private ImageView ib_upphoto;

	/** 修改头像自定义Dialog中的按钮 **/
	private RadioButton rb_dialog[] = new RadioButton[3];

	private int RadioButtonID[] = { R.id.rb_setPhoto1, R.id.rb_setPhoto2,
			R.id.rb_setPhoto3 };

	/** 通过centerIndex来决定采用那种存储方式 **/
	private int centerIndex;

	// 获得编辑权限
	SharedPreferences.Editor editor;

	private int brithID;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.add_brithday);
		ActivityMeg.getInstance().addActivity(this);
		SharedPreferences sharedPreferences = getSharedPreferences(
				activityVolues.shape_name, MODE_PRIVATE);
		editor = sharedPreferences.edit();
		Intent center = getIntent();
		centerIndex = center.getIntExtra("center", -100);
		brithID = center.getIntExtra("brithID", 0);
		dataInit();
		viewInit();
		newCreateFile();
		if (brithID != 0) {
			isOK = true;
			setInfo();
		}
	}

	public void newCreateFile() {
		File file1 = new File(path);
		// 没有目录先建立目录
		if (!file1.exists()) {

			System.out.println("============" + file1.mkdirs());
		}

	}

	public void dataInit() {
		db_brith = new sql_brith(this);
	}

	/**
	 * View的初始化
	 * 
	 */
	public void viewInit() {

		layout = (LinearLayout) this.findViewById(R.id.add_bottom);

		btn_setBrith = (Button) this.findViewById(R.id.add_brith_brith);
		btn_setBrith.setOnClickListener(onClickListener);
		btn_lianxiren_name = (Button) this
				.findViewById(R.id.add_brith_name_tolianxiren);
		btn_lianxiren_name.setOnClickListener(onClickListener);
		btn_lianxiren_phone = (Button) this
				.findViewById(R.id.add_brith_phone_tolianxiren);
		btn_lianxiren_phone.setOnClickListener(onClickListener);
		btn_save = (Button) this.findViewById(R.id.add_brith_baocun);
		btn_save.setOnClickListener(onClickListener);
		btn_sendSMS = (Button) this
				.findViewById(R.id.add_brith_brith_toduanxin);
		btn_sendSMS.setOnClickListener(onClickListener);
		for (int i = 0; i < add_edit.length; i++) {
			add_edit[i] = (EditText) this.findViewById(EditTextID[i]);
		}
		group = (RadioGroup) this.findViewById(R.id.add_radioGourp);
		nan = (RadioButton) this.findViewById(R.id.add_brith_nan);
		nv = (RadioButton) this.findViewById(R.id.add_brith_nv);
		nan.setChecked(true);
		ib_upphoto = (ImageView) this.findViewById(R.id.reviseHead);
		ib_upphoto.setOnClickListener(onClickListener);
		tv_title = (TextView) this.findViewById(R.id.add_title);
		if (centerIndex == 100) {
			tv_title.setText("请设置您的基本信息");
			layout.setVisibility(View.GONE);
			btn_sendSMS.setVisibility(View.GONE);
		} else {
			layout.setVisibility(View.VISIBLE);
		}
	}

	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.add_brith_brith) {
				showDateTimePicker();
			} else if (id == R.id.add_brith_name_tolianxiren) {
				// 离开当前Activity要关闭数据库
				// sqlDatebase.closeSQl();
				Intent intent = new Intent();
				intent.putExtra("add_index_name", 1);
				intent.setClass(rcp_addBrithday.this,
						Rcp_IndexSearchActivity.class);
				startActivityForResult(intent, SEND_SEARCH_NAME);
			} else if (id == R.id.add_brith_phone_tolianxiren) {
				Intent intent = new Intent();
				intent.putExtra("add_index_phone", 2);
				intent.setClass(rcp_addBrithday.this,
						Rcp_IndexSearchActivity.class);
				startActivityForResult(intent, SEND_SEARCH_PHONE);
			} else if (id == R.id.add_brith_brith_toduanxin) {
			} else if (id == R.id.add_brith_baocun) {
				if (isOK && (add_edit[0].getText().toString().length() > 0)) {
					System.out.println("kkkkkkkkkkkkkkk" + centerIndex);
					if (brithID != 0) {
						update();
						Intent intent4 = new Intent(rcp_addBrithday.this,
								brithInfo.class);
						rcp_addBrithday.this.startActivity(intent4);
					} else {
						if (centerIndex == 100) {
							saveToShape();

						} else {
							savetTodb();
						}
					}
				} else {
					Toast.makeText(rcp_addBrithday.this, "亲,请完善信息再保存", 0)
							.show();
				}
				Intent intent4 = new Intent(rcp_addBrithday.this,
						Rcp_birthdayActivity.class);
				rcp_addBrithday.this.startActivity(intent4);
				finish();
			} else if (id == R.id.reviseHead) {
				showSetPhotoDialog();
			} else if (id == R.id.rb_setPhoto1) {
				alertDialog.dismiss();
				// 调用系统的拍照功能
				Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// 指定调用相机拍照后照片的储存路径
				intent1.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(tempFile));
				startActivityForResult(intent1, PHOTO_REQUEST_TAKEPHOTO);
			} else if (id == R.id.rb_setPhoto2) {
				alertDialog.dismiss();
				Intent intent2 = new Intent(Intent.ACTION_PICK, null);
				intent2.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent2, PHOTO_REQUEST_GALLERY);
			} else if (id == R.id.rb_setPhoto3) {
			}

		}

	};

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

	public void setInfo() {
		Cursor cursor = null;
		try {
			cursor = readInfo(brithID + "");
			if (cursor != null) {
				// 游标下标归零
				cursor.moveToPosition(0);
				// 取游标中的值
				while (true) {
					// 判断是否在最后
					if (cursor.isAfterLast()) {
						break;
					}
					String name = cursor.getString(1);
					add_edit[0].setText(name);
					String sex = cursor.getString(3);
					String photo = cursor.getString(4);
					data1.setBrithPer_photo(photo);
					Bitmap map = new imageUtil().getBitmapTodifferencePath(
							photo + "", this);
					if (map != null) {
						ib_upphoto.setImageBitmap(map);
					} else {
						ib_upphoto
								.setBackgroundResource(sex.equals("男") ? R.drawable.defaultboy
										: R.drawable.defaultgirl);
					}
					int year = cursor.getInt(5);
					int month = cursor.getInt(6);
					int date = cursor.getInt(7);
					data1.setGregorianYear(year);
					data1.setGregorianDate(date);
					data1.setGregorianMouth(month);
					String gongli = year + "-" + month + "-" + date;
					btn_setBrith.setText(gongli);
					// tv_birth_1stline.setText(gongli);
					// String year_ = otherUtil.cyclicalm(year);
					//
					// String day_ = calendarUtil.getChineseDay(year, month,
					// date);
					// String month_ = calendarUtil.getChineseMonth(year, month,
					// date);
					// tv_birth_2ndline.setText(year_ + "年(" + year + ")" +
					// month_ +
					// day_);
					int ind = sex.equals("男") ? 0 : 1;
					if (ind == 1) {
						nv.setChecked(true);
					} else {
						nan.setChecked(true);
					}
					add_edit[1].setText(cursor.getString(8));
					add_edit[2].setText(cursor.getString(11));

					data1.setBrithPer_name(add_edit[0].getText().toString());
					// data1.setBrithPer_age(otherUtil.getCurYear() -
					// data1.gregorianYear);
					// data1.setBrithPer_sex((group.getCheckedRadioButtonId() ==
					// R.id.add_brith_nan) ? "男"
					// : "女");

					// data1.setBrithPer_phone(add_edit[1].getText().toString());
					// data1.setBrithPer_animals(otherUtil.getAnimals(data1.gregorianYear));
					// data1.setBrithPer_constellation(otherUtil.getconstellation(
					// data1.gregorianMouth, data1.gregorianDate));
					data1.setBrithPer_beizhuInfo(add_edit[2].getText()
							.toString());
					cursor.moveToNext();
				}
			}
		} finally {
			if (cursor != null) {

				try {

					cursor.close();

				} catch (Exception e) {

				}

			}

		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	/***
	 * 
	 * 保存用户数据
	 * 
	 */
	public void saveToShape() {
		isOK = false;
		editor.putString("name", add_edit[0].getText().toString());
		editor.putString("sex",
				(group.getCheckedRadioButtonId() == R.id.add_brith_nan) ? "男"
						: "女");
		editor.putString("email", activityVolues.loadName);
		boolean id = editor.commit();
		Log.e("huhuhuhu", id + "");
	}

	/**
	 * 
	 * 保存数据到DB
	 * 
	 */
	public void savetTodb() {
		// 每次只能保存一次
		isOK = false;
		db_brith.insert(set());
		// 未备份的数量++
		activityVolues.backupCount++;
		Log.e(".............>>>", activityVolues.backupCount + "");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		switch (requestCode) {

		case SEND_SEARCH_NAME: {
			if (data == null) {
				System.out.println("oooooooooooooooooooooooooooo");
				break;
			}
			Bundle b = data.getExtras(); // data为B中回传的Intent
			String str = b.getString("add_name");
			add_edit[0].setText(str);
		}
			break;
		case SEND_SEARCH_PHONE: {
			if (data == null) {
				System.out.println("oooooooooooooooooooooooooooo");
				break;
			}
			Bundle b = data.getExtras(); // data为B中回传的Intent
			String str = b.getString("add_phone");
			add_edit[1].setText(str);
		}
			break;
		case PHOTO_REQUEST_TAKEPHOTO:
			startPhotoZoom(Uri.fromFile(tempFile), 150);
			if (centerIndex == 100) {
				editor.putString("photo", tempFile.getPath());
			}
			data1.setBrithPer_photo(tempFile.getPath());
			break;

		case PHOTO_REQUEST_GALLERY:
			if (data != null) {

				startPhotoZoom(data.getData(), 150);
				// 传一个内部地址

				if (centerIndex == 100) {
					editor.putString("photo", data.getDataString());
				}
				data1.setBrithPer_photo(data.getDataString());
				System.out.println("----2222222----------------->>"
						+ data.getDataString());

			}
			break;

		case PHOTO_REQUEST_CUT:
			if (data != null)
				setPicToView(data);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

	public void update() {
		isOK = false;
		db_brith.update(set(), brithID + "");
		Log.e("oooooooooo", data1.getBrithPer_name());
	}

	public brith_ListItem set() {
		data1.setBrithPer_name(add_edit[0].getText().toString());
		data1.setBrithPer_age(otherUtil.getCurYear() - data1.gregorianYear);
		data1.setBrithPer_sex((group.getCheckedRadioButtonId() == R.id.add_brith_nan) ? "男"
				: "女");

		data1.setBrithPer_phone(add_edit[1].getText().toString());
		data1.setBrithPer_animals(otherUtil.getAnimals(data1.gregorianYear));
		data1.setBrithPer_constellation(otherUtil.getconstellation(
				data1.gregorianMouth, data1.gregorianDate));
		data1.setBrithPer_beizhuInfo(add_edit[2].getText().toString());

		return data1;

	}

	/***
	 * 
	 * 时间滚动器
	 * 
	 */
	public void showDateTimePicker() {
		LayoutInflater inflater = LayoutInflater.from(rcp_addBrithday.this);
		View timepickerview = inflater.inflate(R.layout.selectbirthday, null);
		timepickerview.setMinimumWidth(getWindowManager().getDefaultDisplay()
				.getWidth());
		ScreenInfo screenInfo = new ScreenInfo(rcp_addBrithday.this);
		wheelMain = new WheelMain(timepickerview);
		wheelMain.screenheight = screenInfo.getHeight();
		Calendar calendar = Calendar.getInstance();
		// calendar.setTime(dateFormat.parse(time));设置指定时间
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		wheelMain.setTime(year, month, day);
		dialog = new AlertDialog.Builder(this).setView(timepickerview).show();

		Window window = dialog.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystyle); // 添加动画

		Button btn = (Button) timepickerview
				.findViewById(R.id.btn_datetime_sure);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				isOK = true;
				btn_setBrith.setText(wheelMain.getTime());
				data1.setGregorianYear(wheelMain.getYear());
				data1.setGregorianMouth(wheelMain.getMonth());
				data1.setGregorianDate(wheelMain.getDay());

				editor.putString("year", wheelMain.getYear() + "");
				editor.putString("month", wheelMain.getMonth() + "");
				editor.putString("date", wheelMain.getDay() + "");
				btn_setBrith.setHint(wheelMain.getTime());
				dialog.dismiss();
			}
		});

	}

	/**
	 * 显示设置头像的Dialog
	 * 
	 */
	private void showSetPhotoDialog() {
		// 初始化自定义布局参数
		LayoutInflater layoutInflater = getLayoutInflater();
		// 为了能在下面的OnClickListener中获取布局上组件的数据，必须定义为final类型.
		View customLayout = layoutInflater.inflate(
				R.layout.showsetphototdialog,
				(ViewGroup) findViewById(R.id.customDialog));

		for (int i = 0; i < rb_dialog.length; i++) {
			rb_dialog[i] = (RadioButton) customLayout
					.findViewById(RadioButtonID[i]);
			rb_dialog[i].setOnClickListener(onClickListener);
		}

		alertDialog = new AlertDialog.Builder(this).setView(customLayout)
				.show();

		Window window = alertDialog.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.mystyle); // 添加动画
	}

	private void startPhotoZoom(Uri uri, int size) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");

		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", size);
		intent.putExtra("outputY", size);
		intent.putExtra("return-data", true);

		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

	// 将进行剪裁后的图片显示到UI界面上
	private void setPicToView(Intent picdata) {
		Bundle bundle = picdata.getExtras();

		if (bundle != null) {
			Bitmap photo = bundle.getParcelable("data");
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);//
			// Drawable drawable = new BitmapDrawable(photo);
			ib_upphoto.setImageBitmap(photo);
		}
	}

	// 使用系统当前日期加以调整作为照片的名称
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";

	}
}
