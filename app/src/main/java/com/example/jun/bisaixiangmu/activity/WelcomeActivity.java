package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jun.bisaixiangmu.MainActivity;
import com.example.jun.bisaixiangmu.R;

public class WelcomeActivity extends AppCompatActivity {
    public Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                boolean is_First = getSharedPreferences("config", MODE_PRIVATE).getBoolean("is_First", true);
                if (is_First) {
                    Intent intent = new Intent(getBaseContext(), SplashActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Message msg = Message.obtain();
        handler.sendEmptyMessageDelayed(0, 3000);
    }
}
