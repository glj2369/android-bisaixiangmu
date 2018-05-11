package com.example.jun.bisaixiangmu.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;

public abstract class BaseActivity extends AppCompatActivity {
    protected SharedPreferences sp2;//网络设置
    protected TextView mTitleTV;
    protected String mBasURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(getLayouId());

        sp2=getSharedPreferences("wangluoshezhi",MODE_PRIVATE);
        String http = sp2.getString("http", "127.0.0.1");
        int duankou = sp2.getInt("duankou", 8080);
        mBasURL="http://"+http+":"+duankou+"/";
        initView();
        initData();

        setTitleLayout();
    }
    private void setTitleLayout() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.titlelayout);
        ImageView imageView = (ImageView) layout.findViewById(R.id.back);
        mTitleTV = (TextView) layout.findViewById(R.id.title);
        mTitleTV.setText(getLayoutTitle());
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onAfter();
            }
        });
    }

    protected abstract String getLayoutTitle();
    protected abstract int getLayouId();
    protected abstract void initData();
    protected abstract void onAfter();
    protected abstract void initView();
}
