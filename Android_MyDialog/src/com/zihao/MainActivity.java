package com.zihao;

import com.zihao.utils.CustomDialog;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	/** 退出按钮 **/
	private Button exitBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// 根据id在布局中找到控件对象
		exitBtn = (Button) findViewById(R.id.exit_btn);

		// 为控件绑定点击事件监听器
		exitBtn.setOnClickListener((OnClickListener) this);
	}

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.exit_btn:// 如果点击退出按钮,显示退出对话框
			CustomDialog dialog = new CustomDialog(this, R.style.mystyle,
					R.layout.customdialog);
			dialog.show();
			break;

		default:
			break;
		}
	}

}
