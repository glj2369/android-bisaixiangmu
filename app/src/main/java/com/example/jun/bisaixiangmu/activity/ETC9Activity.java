package com.example.jun.bisaixiangmu.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean9;
import com.example.jun.bisaixiangmu.utils.List_item9Adapter;

import java.util.ArrayList;
import java.util.List;

public class ETC9Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    private ListView listView;
    private List_item9Adapter adapter;
    private List<Bean9> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc9);
        initView();
        initDatas();

    }

    private void initDatas() {
        datas=new ArrayList<>();
        Bean9 bean9_1=new Bean9(R.mipmap.ic_launcher_round,
                "辽A10001","车主:张三",100,true);
        Bean9 bean9_2=new Bean9(R.mipmap.ic_launcher_round,
                "辽A10002","车主:李四",90,false);
        Bean9 bean9_3=new Bean9(R.mipmap.ic_launcher_round,
                "辽A10003","车主:王五",103,false);
        Bean9 bean9_4=new Bean9(R.mipmap.ic_launcher_round,
                "辽A10004","车主:赵六",1,false);
        datas.add(bean9_1);
        datas.add(bean9_2);
        datas.add(bean9_3);
        datas.add(bean9_4);

        adapter=new List_item9Adapter(this, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    private void initView() {

        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        listView=findViewById(R.id.list_item9);

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
