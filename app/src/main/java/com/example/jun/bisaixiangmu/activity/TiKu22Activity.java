package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean22;
import com.example.jun.bisaixiangmu.bean.Bean9;
import com.example.jun.bisaixiangmu.utils.List_item22Adapter;
import com.example.jun.bisaixiangmu.utils.List_item9Adapter;

import java.util.ArrayList;
import java.util.List;

public class TiKu22Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    private ListView listView;
    private List_item22Adapter adapter;
    private List<Bean22> datas;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_ku22);
        initView();
        initDatas();

    }

    private void initDatas() {
        datas=new ArrayList<>();
        datas.add(new Bean22(1,100));
        datas.add(new Bean22(2,99));
        datas.add(new Bean22(3,103));
        datas.add(new Bean22(4,1));
        adapter=new List_item22Adapter(datas,this);
        listView.setAdapter(adapter);
    }

    private void initView() {

        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        listView=findViewById(R.id.list_item22);
        textView=findViewById(R.id.bt_jilu);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TiKu22Activity.this,ZhangDan3Activity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
