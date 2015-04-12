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
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view  
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局  
        // main.xml中的ImageView  
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);  
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字  
        // 加载动画  
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
                context, R.anim.loading_animation);   
        // 使用ImageView显示动画  
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
        tipTextView.setText(msg);// 设置加载信息  
  
        final Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog  
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
        loadingDialog.setCancelable(false);//(false);// 不可以用“返回键”取消  
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.MATCH_PARENT,  
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局  
        return loadingDialog;  
    }  
	
	
	public static Dialog createLoadingDialog2(final Context context, String msg) {  
		  
		final Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog  
		loadingDialog.setContentView(R.layout.loading_dialog);
        ImageView spaceshipImage = (ImageView) loadingDialog.findViewById(R.id.img);  
        TextView tipTextView = (TextView) loadingDialog.findViewById(R.id.tipTextView);// 提示文字  
        // 加载动画  
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
                context, R.anim.loading_animation);   
        // 使用ImageView显示动画  
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
        tipTextView.setText(msg);// 设置加载信息  
  
        loadingDialog.setCancelable(false);//(false);// 不可以用“返回键”取消,这样才能拦截到取消操作，根据需求判断是否关闭  
        loadingDialog.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					Toast.makeText(context, "拦截到了对话框被取消，手动关闭", Toast.LENGTH_SHORT).show();
					loadingDialog.cancel();
				}
				return false;
			}
		});
        
//        loadingDialog.setCanceledOnTouchOutside(true);//设置点击Dialog外部任意区域关闭Dialog
        
        return loadingDialog;  
    }  
}
