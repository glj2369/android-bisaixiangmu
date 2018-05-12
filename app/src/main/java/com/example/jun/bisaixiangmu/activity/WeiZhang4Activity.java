package com.example.jun.bisaixiangmu.activity;


import android.content.Intent;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.frament.WeiZhangImage;
import com.example.jun.bisaixiangmu.frament.WeiZhangMovice;

public class WeiZhang4Activity extends BaseActivity {
    private FragmentTabHost mTabHost;
    @Override
    protected String getLayoutTitle() {
        return "车辆违章";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_wei_zhang4;
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
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        addTab();
    }

    private void addTab() {
        String[] tag = {
                "movie", "image"
        };
        Class<?>[] cls = {
                WeiZhangMovice.class, WeiZhangImage.class
        };
        View[] views = {
                getTabView("违章视频"), getTabView("违章图片")
        };
        for (int i = 0; i < tag.length; i++) {
            mTabHost.addTab(
                    mTabHost.newTabSpec(tag[i]).setIndicator(views[i]),
                    cls[i], null);
        }
    }
    private View getTabView(String resId) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab, null);
        TextView textView = (TextView) view.findViewById(R.id.content);
        textView.setText(resId);
        return view;
    }
}
