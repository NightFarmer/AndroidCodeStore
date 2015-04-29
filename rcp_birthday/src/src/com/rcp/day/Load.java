package src.com.rcp.day;

import java.io.File;
import java.io.FileInputStream;

import rcp.com.other.ActivityMeg;
import rcp.com.src.volues.activityVolues;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/***
 * 
 * µÇÂ¼
 * 
 * @author toshiba
 * 
 */
public class Load extends Activity {

	private Button btn_zhuce;

	private Button btn_login;

	private String name, password;

	private EditText et_name, et_password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.login);
		ActivityMeg.getInstance().addActivity(this);
		viewInit();
	}

	public void readNamePass() {
		String path1 = this.getFilesDir() + "/name.txt";

		File file = new File(path1);

		System.out.println(path1);
		try {
			FileInputStream inStream = new FileInputStream(file);

			int length = inStream.available();

			byte[] buffer = new byte[length];

			inStream.read(buffer);

			String info = new String(buffer);

			System.out.println("============>>>>" + info);

			int index = info.indexOf('-');
			name = info.substring(0, index);
			password = info.substring(index + 1, info.length());

			System.out.println(password + "------------" + name);
			inStream.close();

		} catch (Exception e) {
			// TODO: handle exception

			Toast.makeText(this, "¶ÁÈ¡ÎÄ¼þÊ§°Ü£¡", Toast.LENGTH_SHORT).show();
		}

	}

	public void viewInit() {
		btn_zhuce = (Button) this.findViewById(R.id.baidu_zhuce);
		btn_zhuce.setOnClickListener(onClickListener);
		btn_login = (Button) this.findViewById(R.id.btn_login);
		btn_login.setOnClickListener(onClickListener);
		et_name = (EditText) this.findViewById(R.id.load_name);
		et_name.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		et_password = (EditText) this.findViewById(R.id.load_password);

	}

	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.baidu_zhuce) {
				Intent intent = new Intent(Load.this, Zhuce.class);
				Load.this.startActivity(intent);
			} else if (id == R.id.btn_login) {
				login();
			}
		}

	};

	public void login() {

		readNamePass();

		if (!et_name.getText().toString().equals(name)) {
			Toast.makeText(this, "µÇÂ¼Ê§°Ü" + et_name.getText().toString() + "Ã»×¢²á",
					0).show();
			Intent intent = new Intent(this, Zhuce.class);
			startActivity(intent);
		} else if (!et_password.getText().toString().equals(password)) {
			Toast.makeText(this, "ÃÜÂë´íÎó", 0).show();
			et_password.setText("");
		} else {
			Intent intent = new Intent(this, Rcp_birthdayActivity.class);
			activityVolues.isLoadOK = true;
			this.startActivity(intent);
			finish();
		}

	}
}
