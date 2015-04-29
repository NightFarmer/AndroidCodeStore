package src.com.rcp.day;

import rcp.com.other.ActivityMeg;
import rcp.com.src.volues.activityVolues;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.umeng.update.UmengUpdateAgent;

//import dalvik.system.VMRuntime;

public class Rcp_birthdayActivity extends TabActivity implements Runnable {

	// ======================================================
	/** 选项卡对象 */
	private TabHost tabhost;
	/** 选项卡按钮 */
	private RadioButton radioButton[] = new RadioButton[4];

	/** 选项卡按钮ID数组 **/
	private int radioButtonId[] = { R.id.tab_item_1, R.id.tab_item_2,
			R.id.tab_item_3, R.id.tab_item_4 };

	// ===========================================================

	/** 是否今日主界面 */
	private int isEnter;

	private ImageView img_appLoad;

	// private final static float TARGET_HEAP_UTILIZATION = 0.75f;

	private final static int CWJ_HEAP_SIZE = 30 * 1024 * 1024;

	// 在程序onCreate时就可以调用

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		// System.out.println("=================="
		// + deleteDatabase("rcpBrithday.db"));
		// VMRuntime.getRuntime()
		// .setTargetHeapUtilization(TARGET_HEAP_UTILIZATION);
		UmengUpdateAgent.setOnDownloadListener(null);
		UmengUpdateAgent.update(this);
//		VMRuntime.getRuntime().setMinimumHeapSize(CWJ_HEAP_SIZE);

		ActivityMeg.getInstance().addActivity(this);
		tabhostInit();
		viewInit();

		new Thread(this).start();

	}

	public void readPass() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);

		isEnter = sharedPreferences.getInt("iskaiqi", -1);

		if (isEnter == 1) {
			Intent intent = new Intent(this, CodeNum.class);
			intent.putExtra("kaiguan", 2);
			this.startActivity(intent);
		}
	}

	/***
	 * 视图的初始化
	 * 
	 */
	public void viewInit() {
		img_appLoad = (ImageView) this.findViewById(R.id.applaoding);
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			img_appLoad.setVisibility(View.GONE);
		}

	};

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readbackUp();
		readPass();
		handler.sendEmptyMessage(0);
	}

	/***
	 * 选项卡的初始化
	 * 
	 */
	public void tabhostInit() {
		tabhost = this.getTabHost();
		tabhost.addTab(tabhost.newTabSpec("brith").setIndicator("11")
				.setContent(new Intent(this, brith.class)));
		tabhost.addTab(tabhost.newTabSpec("sms").setIndicator("22")
				.setContent(new Intent(this, sms.class)));
		tabhost.addTab(tabhost.newTabSpec("center").setIndicator("33")
				.setContent(new Intent(this, mycenter.class)));

		tabhost.addTab(tabhost.newTabSpec("more").setIndicator("44")
				.setContent(new Intent(this, more.class)));
		for (int i = 0; i < radioButton.length; i++) {
			radioButton[i] = (RadioButton) this.findViewById(radioButtonId[i]);
			radioButton[i].setOnClickListener(onClickListener);
		}
		tabhost.setCurrentTabByTag("brith");
		radioButton[0].setChecked(true);
		// ---------------------------------------------
	}

	/****
	 * 单击监听
	 * 
	 */
	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.tab_item_1) {
				tabhost.setCurrentTabByTag("brith");
			} else if (id == R.id.tab_item_2) {
				tabhost.setCurrentTabByTag("sms");
			} else if (id == R.id.tab_item_3) {
				tabhost.setCurrentTabByTag("center");
			} else if (id == R.id.tab_item_4) {
				tabhost.setCurrentTabByTag("more");
			}

		}

	};

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("被恢复");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		System.out.println("暂停");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("消亡");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("开始");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		System.out.println("重新开始");
		saveData();
	}

	public void readbackUp() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"backupCount", MODE_PRIVATE);
		activityVolues.backupCount = sharedPreferences.getInt("count", -1);
		activityVolues.isLoadOK = sharedPreferences.getBoolean("isloadok",
				false);
	}

	public void saveData() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"backupCount", MODE_PRIVATE);
		// 获得编辑权限
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean("isloadok", activityVolues.isLoadOK);
		editor.putInt("count", activityVolues.backupCount);
		editor.commit();
	}

}
