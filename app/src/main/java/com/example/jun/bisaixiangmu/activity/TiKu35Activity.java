package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.utils.Grid_item35Adapter;

public class TiKu35Activity extends BaseActivity {
    private GridView tiku_35_gridview;
    private Grid_item35Adapter adapter;

    @Override
    protected String getLayoutTitle() {
        return "旅行助手";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku35;
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
        tiku_35_gridview=findViewById(R.id.tiku_35_gridview);
        adapter=new Grid_item35Adapter(this);
        tiku_35_gridview.setAdapter(adapter);
        tiku_35_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TiKu35Activity.this, "我点击了"+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TiKu35Activity.this,TiKu35_infoActivity.class));
            }
        });
    }
}
