package com.example.jun.bisaixiangmu.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.frament.TiKu16Fragment1;
import com.example.jun.bisaixiangmu.frament.TiKu16Fragment2;
import com.example.jun.bisaixiangmu.frament.TiKu16Fragment3;
import com.example.jun.bisaixiangmu.frament.WeiZhangImage;
import com.example.jun.bisaixiangmu.frament.WeiZhangMovice;

public class TiKu16Activity extends BaseActivity implements View.OnClickListener {
    //    private ListView list_view_tiku16;
//    private List<Bean20> bean16List;
//    private  List_TiKu20Adapter adapter;
    private TextView textViewInfo;
    private TextView textViewJiLu;
    private TextView textViewYuZhi;
    private FrameLayout fragment;
    private FragmentTabHost mTabHost;
    private Fragment f1;
    private Fragment f2;
    private Fragment f3;
    @Override
    protected String getLayoutTitle() {
        return "个人中心";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku16;
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

        textViewInfo = findViewById(R.id.tiku_16_info);
        textViewJiLu = findViewById(R.id.tiku_16_jilu);
        textViewYuZhi = findViewById(R.id.tiku_16_yuzhi);
        textViewInfo.setOnClickListener(this);
        textViewJiLu.setOnClickListener(this);
        textViewYuZhi.setOnClickListener(this);
        initFragment1();
    }
    private void initFragment1(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f1 == null){
            f1 = new TiKu16Fragment1();
            transaction.add(R.id.realtabcontent,f1);
        }
        hideFragment(transaction);
        transaction.show(f1);
        transaction.commit();
        textViewInfo.setBackgroundResource(R.drawable.tab_bg_on16);
    }
    private void initFragment2(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f2 == null){
            f2 = new TiKu16Fragment2();
            transaction.add(R.id.realtabcontent,f2);
        }
        hideFragment(transaction);
        transaction.show(f2);
        transaction.commit();
        textViewJiLu.setBackgroundResource(R.drawable.tab_bg_on16);
    }
    private void initFragment3(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f3 == null){
            f3 = new TiKu16Fragment3();
            transaction.add(R.id.realtabcontent,f3);
        }
        hideFragment(transaction);
        transaction.show(f3);
        transaction.commit();
        textViewYuZhi.setBackgroundResource(R.drawable.tab_bg_on16);
    }
    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(f1 != null){
            transaction.hide(f1);
        }
        if(f2 != null){
            transaction.hide(f2);
        }
        if(f3 != null){
            transaction.hide(f3);
        }
        textViewInfo.setBackgroundResource(R.drawable.tab_bg16);
        textViewJiLu.setBackgroundResource(R.drawable.tab_bg16);
        textViewYuZhi.setBackgroundResource(R.drawable.tab_bg16);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tiku_16_info:
                initFragment1();

                break;
            case R.id.tiku_16_jilu:
                initFragment2();

                break;
            case R.id.tiku_16_yuzhi:
                initFragment3();
                break;
        }
    }


}
