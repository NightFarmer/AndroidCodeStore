package src.com.rcp.day;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rcp.com.backup.BaiduPCSAction;
import rcp.com.backup.PCSDemoInfo;
import rcp.com.other.ActivityMeg;
import rcp.com.src.volues.activityVolues;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

/***
 * 
 * зЂВс
 * 
 * @author toshiba
 * 
 */
public class Zhuce extends Activity {

	private Button btn_ok;

	String namePath;

	private EditText et_name, et_pass;

	private BaiduPCSAction action = new BaiduPCSAction();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.zhuce);
		ActivityMeg.getInstance().addActivity(this);
		viewInit();
	}

	public void viewInit() {
		btn_ok = (Button) this.findViewById(R.id.btn_login_ok);
		btn_ok.setOnClickListener(onClickListener);
		et_name = (EditText) this.findViewById(R.id.edit_name);
		et_name.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		et_pass = (EditText) this.findViewById(R.id.edit_password);
	}

	public void datainit() {

		activityVolues.loadName = et_name.getText().toString();

		String info = et_name.getText().toString() + "-"
				+ et_pass.getText().toString();
		namePath = this.getFilesDir() + "/name.txt";

		String saveFile = "name.txt";

		FileOutputStream outputStream;
		try {
			outputStream = this.openFileOutput(saveFile, MODE_PRIVATE);
			if (!info.equals("")) {
				// save file
				outputStream.write(info.getBytes());

			} else {
				byte bytes = 0;
				outputStream.write(bytes);
			}

			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	OnClickListener onClickListener = new OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.btn_login_ok) {
				datainit();
				PCSDemoInfo.index = 1;
				action.login(Zhuce.this);
			}
		}

	};

}
