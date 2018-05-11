package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jun.bisaixiangmu.ActivityCollector;
import com.example.jun.bisaixiangmu.R;

public class TiKu38_2Activity extends BaseActivity implements View.OnClickListener {
    private Button tiku_38_2_bt_next;

    @Override
    protected String getLayoutTitle() {
        return "定制班车";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku38_2;
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
        tiku_38_2_bt_next = findViewById(R.id.tiku_38_2_bt_next);
        tiku_38_2_bt_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_38_2_bt_next:
                startActivity(new Intent(TiKu38_2Activity.this,TiKu38_3Activity.class));
                ActivityCollector.addActivity(TiKu38_2Activity.this);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(TiKu38_2Activity.this);
    }
}
