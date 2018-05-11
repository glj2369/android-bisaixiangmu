package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.ActivityCollector;
import com.example.jun.bisaixiangmu.R;

import java.util.ArrayList;

public class TiKu38_5Activity extends BaseActivity implements View.OnClickListener {
    private Button tiku_38_4_bt_tijiao;
    private TextView tiku_38_date;
    private TextView tiku_38_address;
    private TextView tiku_38_phone;
    private TextView tiku_38_name;


    @Override
    protected String getLayoutTitle() {
        return "确认订单";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku38_5;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        ArrayList<String> timeList = intent.getStringArrayListExtra("timeList");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < timeList.size(); i++) {
            if (i == (timeList.size() - 1)) {
                stringBuilder.append(timeList.get(i));
            } else {
                stringBuilder.append(timeList.get(i) + ",");
            }
        }
        tiku_38_date.setText(stringBuilder.toString());
        tiku_38_address.setText(address);
        tiku_38_phone.setText(phone);
        tiku_38_name.setText(name);


    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        tiku_38_4_bt_tijiao = findViewById(R.id.tiku_38_4_bt_tijiao);
        tiku_38_date = findViewById(R.id.tiku_38_date);
        tiku_38_address = findViewById(R.id.tiku_38_address);
        tiku_38_phone = findViewById(R.id.tiku_38_phone);
        tiku_38_name = findViewById(R.id.tiku_38_name);
        ActivityCollector.addActivity(this);
        tiku_38_4_bt_tijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_38_4_bt_tijiao:
                Toast.makeText(this, "提交成功,你可以在我的订单中查询", Toast.LENGTH_SHORT).show();
                ActivityCollector.findshAll();
                break;
            default:
                break;
        }
    }
}
