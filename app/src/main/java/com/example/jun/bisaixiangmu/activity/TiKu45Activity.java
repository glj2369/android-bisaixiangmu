package com.example.jun.bisaixiangmu.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.frament.TiKu45_fragment1;
import com.example.jun.bisaixiangmu.frament.TiKu45_fragment2;
import com.example.jun.bisaixiangmu.frament.TiKu45_fragment3;

public class TiKu45Activity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtControl;
    private Button mBtBalance;
    private Button mBtRecord;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_ku45);
        initView();
        initFragment();
    }

    private void initFragment() {
        initFragment1();
    }


    private void initFragment3() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment3==null){
            fragment3=new TiKu45_fragment3();
            transaction.add(R.id.tiku_45_frament,fragment3);
        }
        hideFragment(transaction);
        transaction.show(fragment3);
        transaction.commit();
        mBtRecord.setTextColor(0xFF00FF00);
    }
    private void initFragment2() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment2==null){
            fragment2=new TiKu45_fragment2();
            transaction.add(R.id.tiku_45_frament,fragment2);
        }
        hideFragment(transaction);
        transaction.show(fragment2);
        transaction.commit();
        mBtControl.setTextColor(0xFF00FF00);
    }
    private void initFragment1() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment1==null){
            fragment1=new TiKu45_fragment1();
            transaction.add(R.id.tiku_45_frament,fragment1);
        }
        hideFragment(transaction);
        transaction.show(fragment1);
        transaction.commit();
        mBtBalance.setTextColor(0xFF00FF00);
    }
    private void hideFragment(FragmentTransaction transaction) {
        if (fragment1!=null){
            transaction.hide(fragment1);
        }
        if (fragment2!=null){
            transaction.hide(fragment2);
        }
        if (fragment3!=null){
            transaction.hide(fragment3);
        }
        mBtBalance.setTextColor(0xFF000000);
        mBtControl.setTextColor(0xFF000000);
        mBtRecord.setTextColor(0xFF000000);
    }
    private void initView() {
        mBtBalance = findViewById(R.id.bt_balance);
        mBtControl = findViewById(R.id.bt_control);
        mBtRecord = findViewById(R.id.bt_record);
        mBtBalance.setOnClickListener(this);
        mBtControl.setOnClickListener(this);
        mBtRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_balance:
                initFragment1();
                break;
            case R.id.bt_control:
                initFragment2();
                break;
            case R.id.bt_record:
                initFragment3();
                break;
        }
    }
}
