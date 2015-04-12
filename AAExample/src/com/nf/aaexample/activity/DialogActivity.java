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
        //setTheme(R.style.MyDialogStyle);//在Mainifest文件中注册，此处不行
        setContentView(R.layout.activity_main);  
        // 这里你可以进行一些等待时的操作，我这里用8秒后显示Toast代理等待操作  
        new Handler().postDelayed(new Runnable(){  
            @Override  
            public void run(){  
                  
                DialogActivity.this.finish();  
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();  
            }  
        }, 8000);  
    }  
}  