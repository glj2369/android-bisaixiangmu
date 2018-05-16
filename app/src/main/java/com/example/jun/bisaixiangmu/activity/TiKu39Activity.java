package com.example.jun.bisaixiangmu.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.frament.TiKu38_fragment1;
import com.example.jun.bisaixiangmu.frament.TiKu38_fragment2;
import com.example.jun.bisaixiangmu.frament.TiKu38_fragment3;

public class TiKu39Activity extends BaseActivity implements View.OnClickListener {
//    private TextView textView1;
//    private TextView textView2;
//    private TextView textView3;
    private Fragment f1;
    private Fragment f2;
    private Fragment f3;

    @Override
    protected String getLayoutTitle() {
        return "新闻媒体";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku39;
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
//        textView1 = findViewById(R.id.tiku_39_keji);
//        textView2 = findViewById(R.id.tiku_39_jiaoyu);
//        textView3 = findViewById(R.id.tiku_39_tiyu);
//        textView1.setOnClickListener(this);
//        textView2.setOnClickListener(this);
//        textView3.setOnClickListener(this);
  //      initFragment1();

        FragmentTabHost tabHost=findViewById(android.R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec("1").setIndicator("科技"),TiKu38_fragment1.class,null);
        tabHost.addTab(tabHost.newTabSpec("2").setIndicator("教育"),TiKu38_fragment2.class,null);
        tabHost.addTab(tabHost.newTabSpec("3").setIndicator("教育"),TiKu38_fragment3.class,null);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tiku_39_keji:
//                initFragment1();
//                break;
//            case R.id.tiku_39_jiaoyu:
//                initFragment2();
//                break;
//            case R.id.tiku_39_tiyu:
//                initFragment3();
//                break;
        }
    }

    private void initFragment3() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (f3==null){
            f3=new TiKu38_fragment3();
            transaction.add(R.id.realtabcontent,f3);
        }
        hideFragment(transaction);
        transaction.show(f3);
        transaction.commit();
        //0xFFFDEA71
        //textView3.setTextColor(0xFFFDEA71);
    }

    private void initFragment2() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (f2==null){
            f2=new TiKu38_fragment2();
            transaction.add(R.id.realtabcontent,f2);
        }
        hideFragment(transaction);
        transaction.show(f2);
        transaction.commit();
        //0xFFFDEA71
        ///textView2.setTextColor(0xFFFDEA71);
    }

    private void initFragment1() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (f1==null){
            f1=new TiKu38_fragment1();
            transaction.add(R.id.realtabcontent,f1);
        }
        hideFragment(transaction);
        transaction.show(f1);
        transaction.commit();
        //0xFFFDEA71
        //textView1.setTextColor(0xFFFDEA71);
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (f1!=null){
            transaction.hide(f1);
        }
        if (f2!=null){
            transaction.hide(f2);
        }
        if (f3!=null){
            transaction.hide(f3);
        }
//        textView3.setTextColor(0xFF363636);
//        textView2.setTextColor(0xFF363636);
//        textView1.setTextColor(0xFF363636);
    }
}
