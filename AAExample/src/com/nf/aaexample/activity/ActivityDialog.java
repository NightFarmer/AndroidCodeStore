package com.nf.aaexample.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.nf.aaexample.R;
import com.nf.aaexample.activity.base.ActivityBase;
import com.nf.aaexample.util.LoadingDialog;

public class ActivityDialog extends ActivityBase{

	private Button bt_dlg;
	private Button bt_dlg2;
	private Button bt_dlg3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	protected int getContainLayoutId() {
		return R.layout.dialog;
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		this.bt_dlg = (Button) findViewById(R.id.bt_dlg);
		this.bt_dlg2 = (Button) findViewById(R.id.bt_dlg2);
		this.bt_dlg3 = (Button) findViewById(R.id.bt_dlg3);
	}

	@Override
	protected void initListener() {
		// TODO Auto-generated method stub
		bt_dlg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
                        ActivityDialog.this);
				builder.setTitle("提示");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setMessage("这是一个普通的对话框！");
				builder.setCancelable(false);
				builder.setPositiveButton("知道了！",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
				builder.create().show();
			}
		});
		
		bt_dlg2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ActivityDialog.this, DialogActivity.class);
				startActivity(intent);
			}
		});
		
		bt_dlg3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Dialog loadingDialog = LoadingDialog.createLoadingDialog(ActivityDialog.this, "yoyoyo");
				Dialog loadingDialog = LoadingDialog.createLoadingDialog2(ActivityDialog.this, "yoyoyo");
				loadingDialog.show();
				
			}
		});
	}

	@Override
	protected void bindData() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 如果是返回键,直接返回到桌面
		if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
			showExitGameAlert();
		}

		return super.onKeyDown(keyCode, event);
	}

	private void showExitGameAlert() {
		final AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.show();
		Window window = dlg.getWindow();
		// *** 主要就是在这里实现这种效果的.
		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		window.setContentView(R.layout.dialogui);
		// 为确认按钮添加事件,执行退出应用操作
		ImageButton ok = (ImageButton) window.findViewById(R.id.btn_ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
//				exitApp(); // 退出应用...
				ActivityDialog.this.finish();
			}
		});

		// 关闭alert对话框架
		ImageButton cancel = (ImageButton) window.findViewById(R.id.btn_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dlg.cancel();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
