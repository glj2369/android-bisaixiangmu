package com.example.jun.bisaixiangmu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean20;
import com.example.jun.bisaixiangmu.utils.List_TiKu20Adapter;

import java.util.ArrayList;
import java.util.List;

public class TiKu20Activity extends BaseActivity {

    private ListView list_view_tiku20;
    private List<Bean20> bean20List;
    private  List_TiKu20Adapter adapter;

    @Override
    protected String getLayoutTitle() {
        return "个人中心";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku20;
    }

    @Override
    protected void initData() {
        Bean20 bean20_1=new Bean20(R.mipmap.ic_launcher_round,"辽A  123456",100);
        Bean20 bean20_2=new Bean20(R.mipmap.ic_launcher_round,"辽A  223456",100);
        bean20List.add(bean20_1);
        bean20List.add(bean20_2);
        adapter=new List_TiKu20Adapter(bean20List,this);
        list_view_tiku20.setAdapter(adapter);
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        list_view_tiku20=findViewById(R.id.list_view_tiku20);
        bean20List=new ArrayList<>();
    }
}
