package com.nf.aaexample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.nf.aaexample.R;

public class DialogActivity extends Activity  
{  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        //setTheme(R.style.MyDialogStyle);//��Mainifest�ļ���ע�ᣬ�˴�����
        setContentView(R.layout.activity_main);  
        // ��������Խ���һЩ�ȴ�ʱ�Ĳ�������������8�����ʾToast����ȴ�����  
        new Handler().postDelayed(new Runnable(){  
            @Override  
            public void run(){  
                  
                DialogActivity.this.finish();  
                Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_SHORT).show();  
            }  
        }, 8000);  
    }  
}  