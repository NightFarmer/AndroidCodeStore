package com.nightfarmer.eip_x.utils;

import android.app.Instrumentation;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.nightfarmer.eip_x.R;

public class TitleBar {

	private View parentView;
	private String title;
	public TitleBar(View parentView, String title) {
		this.parentView = parentView;
		this.title = title;
		ViewStub titleBar = (ViewStub) parentView.findViewById(R.id.title_bar_viewstub);
		titleBar.setLayoutResource(R.layout.titlebar);
		titleBar.inflate();
		this.title = title;
		TextView titleView = (TextView) parentView.findViewById(R.id.titleBar_title);
		titleView.setText(title);
	}

	public Button showOpitonButton(String title){
		Button optionbutton = (Button)parentView.findViewById(R.id.titleBar_optionbutton);
		optionbutton.setText(title);
		optionbutton.setVisibility(View.VISIBLE);
		return optionbutton;
	}
	
	public Button showBackButton(){
		Button optionbutton = (Button)parentView.findViewById(R.id.titleBar_backbt);
		optionbutton.setVisibility(View.VISIBLE);
		optionbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						Instrumentation inst = new Instrumentation();
						inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
					}
				}).start();
			}
		});
		return optionbutton;
	}
}
