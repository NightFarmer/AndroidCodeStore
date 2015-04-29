package src.com.rcp.day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rcp.com.other.ActivityMeg;
import rcp.com.src.brithUtil.otherUtil;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

public class sms extends TabActivity {

	private TabHost tabhost;

	private GridView smsgridView;

	private SimpleAdapter adapter;

	private RadioGroup radioGroup;

	private RadioButton rb_sms[] = new RadioButton[4];

	private int rb_smsID[] = { R.id.sms_btn1, R.id.sms_btn2, R.id.sms_btn3,
			R.id.sms_btn4 };

	private int c_img[] = { R.drawable.c1, R.drawable.c2, R.drawable.c3,
			R.drawable.c4, R.drawable.c5, R.drawable.c6, R.drawable.c7,
			R.drawable.c8, R.drawable.c9, R.drawable.c10, R.drawable.c11,
			R.drawable.c12, R.drawable.c13, R.drawable.c14, R.drawable.c15 };
	private String c_name[] = { "网络文体", "幽默搞笑", "犀利整蛊", "经典祝福", "迟到幸福", "温馨祝福",
			"藏头诗", "祝寿词", "诗词风", "生日对联", "红色语录", "影视经典", "广告经典", "商务祝福", "个性回复" };

	private int a_img[] = { R.drawable.a1, R.drawable.a2, R.drawable.a3,
			R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7,
			R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11,
			R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15,
			R.drawable.a16, R.drawable.a17, R.drawable.a18 };

	private String a_name[] = { "妈妈", "爸爸", "老婆", "老公", "恋人", "挚友", "朋友", "同事",
			"同学", "老师", "领导", "员工", "客户", "长辈", "晚辈", "兄弟", "姐妹", "宝宝" };

	private int b_img[] = { R.drawable.b1, R.drawable.b2, R.drawable.b3,
			R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7,
			R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11 };

	private String b_name[] = { "方言", "德语", "英语", "韩语", "法语", "西班牙语", "符号语言",
			"粤语", "繁体", "火星文", "手语" };

	private String d_name[] = { "腊八节", "元旦", "春节", "元宵节", "情人节", "愚人节", "母亲节",
			"儿童节", "父亲节", "端午节", "七夕节", "教师节", "中秋节", "国庆节", "光棍节", "圣诞节" };
	private int d_img[] = { R.drawable.d1, R.drawable.d2, R.drawable.d3,
			R.drawable.d4, R.drawable.d5, R.drawable.d6, R.drawable.d7,
			R.drawable.d8, R.drawable.d9, R.drawable.d10, R.drawable.d11,
			R.drawable.d12, R.drawable.d13, R.drawable.d14, R.drawable.d15,
			R.drawable.d16 };
	List<Map<String, Object>> smsData = new ArrayList<Map<String, Object>>();

	// 数据的键值
	public static String mapKey[] = { "icon", "title" };
	// 数据对应的布局ID
	public static int layoutID[] = { R.id.sms_grid_img, R.id.sms_grid_info };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_2);
		tanHostInit();
		ActivityMeg.getInstance().addActivity(this);
		dataInit();
		viewInit();
	}

	public void tanHostInit() {
		tabhost = getTabHost();
		tabhost.addTab(tabhost.newTabSpec("11").setIndicator("11")
				.setContent(R.id.sms_1));
		tabhost.addTab(tabhost.newTabSpec("22").setIndicator("22")
				.setContent(R.id.sms_2));
		tabhost.addTab(tabhost.newTabSpec("33").setIndicator("33")
				.setContent(R.id.sms_3));
		tabhost.addTab(tabhost.newTabSpec("44").setIndicator("44")
				.setContent(R.id.sms_4));

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

	public void dataInit() {

		addData(a_name, a_img);
	}

	public void addData(String name[], int img[]) {
		smsData.clear();
		for (int i = 0; i < img.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(mapKey[0], img[i]);
			map.put(mapKey[1], name[i]);
			smsData.add(map);
		}
	}

	public void viewInit() {
		for (int i = 0; i < rb_sms.length; i++) {
			rb_sms[i] = (RadioButton) this.findViewById(rb_smsID[i]);
			rb_sms[i].setOnClickListener(onClickListener);
		}

		radioGroup = (RadioGroup) this.findViewById(R.id.sms_rg);
		rb_sms[0].setChecked(true);
		smsgridView = (GridView) this.findViewById(R.id.sms_grid1);
		adapter = new SimpleAdapter(this, smsData, R.layout.smsgriditem,
				mapKey, layoutID);
		smsgridView.setAdapter(adapter);
		smsgridView.setOnItemClickListener(onItemClickListener);
	}

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long id) {

			Intent intent = new Intent(sms.this, sms_info.class);
			intent.putExtra("radiobutton", radioGroup.getCheckedRadioButtonId());
			intent.putExtra("position", position);
			sms.this.startActivity(intent);
		}

	};

	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.sms_btn1) {
				addData(a_name, a_img);
			} else if (id == R.id.sms_btn2) {
				addData(b_name, b_img);
			} else if (id == R.id.sms_btn3) {
				addData(c_name, c_img);
			} else if (id == R.id.sms_btn4) {
				addData(d_name, d_img);
			}
			adapter.notifyDataSetChanged();
		}

	};

}
