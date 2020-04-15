package com.example.a14.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.a14.R;
import com.example.a14.utils.SharedPreferencesUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final int HANDLER_MSG_OPEN_NEW = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            if (msg.what == HANDLER_MSG_OPEN_NEW){
                //根据登录状态给hasLogin赋值
                //打开列表界面或登录界面
                Intent intent = null;
                if (SharedPreferencesUtil.getInstance(SplashActivity.this).isLogin()){
                    //打开登录界面
                    intent = new Intent(SplashActivity.this,MainActivity.class);

                }else{
                    //打开列表界面
                    intent = new Intent(SplashActivity.this,LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //设置定时器
        new  Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(HANDLER_MSG_OPEN_NEW);
            }
        }, 3000);
    }
}
