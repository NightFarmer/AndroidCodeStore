package com.nf.aaexample.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nf.aaexample.R;

public class LoadingDialog {

	public static Dialog createLoadingDialog(final Context context, String msg) {  
		  
        LayoutInflater inflater = LayoutInflater.from(context);  
        View v = inflater.inflate(R.layout.loading_dialog, null);// �õ�����view  
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// ���ز���  
        // main.xml�е�ImageView  
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);  
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// ��ʾ����  
        // ���ض���  
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
                context, R.anim.loading_animation);   
        // ʹ��ImageView��ʾ����  
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
        tipTextView.setText(msg);// ���ü�����Ϣ  
  
        final Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// �����Զ�����ʽdialog  
        loadingDialog.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "qqqq", Toast.LENGTH_SHORT).show();
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					loadingDialog.cancel();
				}
				return false;
			}
		});
        loadingDialog.setCancelable(false);//(false);// �������á����ؼ���ȡ��  
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.MATCH_PARENT,  
                LinearLayout.LayoutParams.MATCH_PARENT));// ���ò���  
        return loadingDialog;  
    }  
	
	
	public static Dialog createLoadingDialog2(final Context context, String msg) {  
		  
		final Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// �����Զ�����ʽdialog  
		loadingDialog.setContentView(R.layout.loading_dialog);
        ImageView spaceshipImage = (ImageView) loadingDialog.findViewById(R.id.img);  
        TextView tipTextView = (TextView) loadingDialog.findViewById(R.id.tipTextView);// ��ʾ����  
        // ���ض���  
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
                context, R.anim.loading_animation);   
        // ʹ��ImageView��ʾ����  
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
        tipTextView.setText(msg);// ���ü�����Ϣ  
  
        loadingDialog.setCancelable(false);//(false);// �������á����ؼ���ȡ��,�����������ص�ȡ�����������������ж��Ƿ�ر�  
        loadingDialog.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					Toast.makeText(context, "���ص��˶Ի���ȡ�����ֶ��ر�", Toast.LENGTH_SHORT).show();
					loadingDialog.cancel();
				}
				return false;
			}
		});
        
//        loadingDialog.setCanceledOnTouchOutside(true);//���õ��Dialog�ⲿ��������ر�Dialog
        
        return loadingDialog;  
    }  
}
