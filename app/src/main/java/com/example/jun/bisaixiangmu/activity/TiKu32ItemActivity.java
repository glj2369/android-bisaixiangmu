package com.example.jun.bisaixiangmu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jun.bisaixiangmu.R;

public class TiKu32ItemActivity extends BaseActivity {


    @Override
    protected String getLayoutTitle() {
        return "地铁规划";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku32_item;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {

    }
}
