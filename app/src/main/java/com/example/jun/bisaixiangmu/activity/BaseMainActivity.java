package com.example.jun.bisaixiangmu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;

public abstract class BaseMainActivity extends AppCompatActivity {
    protected TextView mTitleTV;
    protected String mBasURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(getLayouId());
        initView();
        initData();
        setTitleLayout();

    }
    private void setTitleLayout() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.titlelayout);

        mTitleTV = (TextView) layout.findViewById(R.id.title);
        mTitleTV.setText(getLayoutTitle());

    }

    protected abstract String getLayoutTitle();
    protected abstract int getLayouId();
    protected abstract void initData();
    protected abstract void initView();
}
