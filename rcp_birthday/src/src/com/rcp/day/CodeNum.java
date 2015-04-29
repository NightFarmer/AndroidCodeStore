package src.com.rcp.day;

import rcp.com.other.ActivityMeg;
import rcp.com.src.brithUtil.otherUtil;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/***
 * 
 * 密码设定
 * 
 * @author toshiba
 * 
 */
public class CodeNum extends Activity {

	private EditText mEditText;

	private TextView tv_title;

	/** 自定义数字按键 */
	private Button btn_num[] = new Button[11];

	private StringBuffer sb = new StringBuffer();

	private String temp1;

	private Button btn_ok;
	/** 按下好的次数 */
	private int count = 0;

	private int index;

	private int numID[] = { R.id.num_yi, R.id.num_er, R.id.num_san,
			R.id.num_si, R.id.num_wu, R.id.num_liu, R.id.num_qi, R.id.num_ba,
			R.id.num_jiu, R.id.num_ling, R.id.num_del };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		index = getIntent().getIntExtra("kaiguan", -1);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.numcode);
		ActivityMeg.getInstance().addActivity(this);

		viewInit();

	}

	public void viewInit() {
		tv_title = (TextView) this.findViewById(R.id.num_code_title);
		for (int i = 0; i < btn_num.length; i++) {
			btn_num[i] = (Button) this.findViewById(numID[i]);
			btn_num[i].setOnClickListener(onClickListener);
		}
		btn_ok = (Button) this.findViewById(R.id.numcode_ok);
		btn_ok.setOnClickListener(onClickListener);
		mEditText = (EditText) findViewById(R.id.numcode_et);
		mEditText.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int inType = mEditText.getInputType(); // backup the input type
				mEditText.setInputType(InputType.TYPE_NULL); // disable soft
																// input
				mEditText.onTouchEvent(event); // call native handler
				mEditText.setInputType(inType); // restore input type
				mEditText.setSelection(mEditText.getText().length());
				return true;
			}
		});
	}

	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.numcode_ok) {
				// 打开密码服务
				if (index == 0) {
					count++;
					if (count == 1) {
						temp1 = sb.toString();
						tv_title.setText("请再次输入密码");
						sb.delete(0, sb.length());
					}
					if (count == 2) {
						String temp2 = sb.toString();

						System.out.println();
						if (temp1.equals(temp2)) {
							saveData();
							Toast.makeText(CodeNum.this, "密码设置成功", 0).show();
							Intent intent = new Intent(CodeNum.this,
									Rcp_birthdayActivity.class);
							CodeNum.this.startActivity(intent);
							finish();
						} else {
							count = 0;
							sb.delete(0, sb.length());
							tv_title.setText("请输入密码");
							Toast.makeText(CodeNum.this, "二次输入的密码不同,请重新输入", 0)
									.show();
						}
					}
					// 关闭密码服务
				} else if (index == 1) {
					readDataToCancel();
					// 主界面验证
				} else if (index == 2) {
					readDataToMain();
				}
			} else if (id == R.id.num_yi) {
				sb.append("1");
			} else if (id == R.id.num_er) {
				sb.append("2");
			} else if (id == R.id.num_san) {
				sb.append("3");
			} else if (id == R.id.num_si) {
				sb.append("4");
			} else if (id == R.id.num_wu) {
				sb.append("5");
			} else if (id == R.id.num_liu) {
				sb.append("6");
			} else if (id == R.id.num_qi) {
				sb.append("7");
			} else if (id == R.id.num_ba) {
				sb.append("8");
			} else if (id == R.id.num_jiu) {
				sb.append("9");
			} else if (id == R.id.num_ling) {
				sb.append("0");
			} else if (id == R.id.num_del) {
				if (sb.length() > 0) {
					sb.delete(sb.length() - 1, sb.length());
				}
			}
			mEditText.setText(sb.toString());
		}

	};
	
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if((index == 2)&&(keyCode==KeyEvent.KEYCODE_BACK)){
			otherUtil.ExitApp(this);
			return true;
		}
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	/***
	 * 
	 * b保存数据
	 * 
	 */
	public void saveData() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);
		// 获得编辑权限
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("password", sb.toString());
		editor.putInt("iskaiqi", 1);
		editor.commit();
	}

	public void readDataToMain() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);

		String password = sharedPreferences.getString("password", "");
		if (password.equals(sb.toString())) {
			Intent intent = new Intent(CodeNum.this, Rcp_birthdayActivity.class);
			CodeNum.this.startActivity(intent);
			finish();
		} else {
			Toast.makeText(CodeNum.this, "密码不正确", 0).show();
			sb.delete(0, sb.length());
		}
	}

	/**
	 * 读数据To关闭服务
	 * 
	 */
	public void readDataToCancel() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				"kaiqipassword", MODE_PRIVATE);

		String password = sharedPreferences.getString("password", "");

		System.out.println(password + "---------------" + sb.toString());

		if (password.equals(sb.toString())) {
			Toast.makeText(CodeNum.this, "密码取消成功", 0).show();
			// 获得编辑权限
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putInt("iskaiqi", -1);
			editor.commit();
			Intent intent = new Intent(CodeNum.this, Rcp_birthdayActivity.class);
			CodeNum.this.startActivity(intent);
			finish();
		} else {

			Toast.makeText(CodeNum.this, "密码不正确", 0).show();
			sb.delete(0, sb.length());
		}
	}

}
