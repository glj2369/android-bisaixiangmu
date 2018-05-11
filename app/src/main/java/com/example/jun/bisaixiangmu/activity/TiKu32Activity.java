package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;

import java.util.ArrayList;
import java.util.List;

public class TiKu32Activity extends BaseActivity implements View.OnClickListener{
    private TextView tiku_32_guihua;
    private ListView listView;
    private List<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected String getLayoutTitle() {
        return "地铁查询";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku32;
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
        tiku_32_guihua = findViewById(R.id.tiku_32_guihua);
        listView = findViewById(R.id.tiku_32_list);
        list = new ArrayList<>();
        list.add("北京地铁1号线（M1）线路图");
        list.add("北京地铁2号线（M2）线路图");
        list.add("北京地铁3号线（M3）线路图");
        adapter = new ArrayAdapter<String>(this, R.layout.tiku32_item, list);
        adapter.setDropDownViewResource(R.layout.tiku32_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(TiKu32Activity.this,TiKu32ItemActivity.class));
            }
        });
        tiku_32_guihua.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_32_guihua:
                startActivity(new Intent(this,TiKu32ItemActivity.class));
                break;
            default:
                break;
        }
    }
}
