package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.ActivityCollector;
import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.utils.List_item38Adapter_1;

public class TiKu38Activity extends BaseActivity {
    private TextView tiku_38_dingdan;
    private ListView tiku_38_list;
    private List_item38Adapter_1 adapter_1;

    @Override
    protected String getLayoutTitle() {
        return null;
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku38;
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
        tiku_38_dingdan=findViewById(R.id.tiku_38_dingdan);
        tiku_38_list=findViewById(R.id.tiku_38_list);
        adapter_1=new List_item38Adapter_1(this);
        tiku_38_list.setAdapter(adapter_1);
        tiku_38_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(TiKu38Activity.this,TiKu38_2Activity.class));
                //ActivityCollector.addActivity(TiKu38Activity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // ActivityCollector.removeActivity(TiKu38Activity.this);
    }
}
