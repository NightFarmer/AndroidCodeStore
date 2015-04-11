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
				builder.setTitle("��ʾ");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setMessage("����һ����ͨ�ĶԻ���");
				builder.setCancelable(false);
				builder.setPositiveButton("֪���ˣ�",
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
		// ����Ƿ��ؼ�,ֱ�ӷ��ص�����
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
		// *** ��Ҫ����������ʵ������Ч����.
		// ���ô��ڵ�����ҳ��,shrew_exit_dialog.xml�ļ��ж���view����
		window.setContentView(R.layout.dialogui);
		// Ϊȷ�ϰ�ť����¼�,ִ���˳�Ӧ�ò���
		ImageButton ok = (ImageButton) window.findViewById(R.id.btn_ok);
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
//				exitApp(); // �˳�Ӧ��...
				ActivityDialog.this.finish();
			}
		});

		// �ر�alert�Ի����
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
