package com.example.jun.bisaixiangmu.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean31;
import com.example.jun.bisaixiangmu.db.YiJian31DB;
import com.example.jun.bisaixiangmu.utils.List_item31Adapter;

import java.util.ArrayList;
import java.util.List;

public class YiJian31Activity extends BaseActivity {
    private YiJian31DB yiJian31DB;
    private ListView tiku_31_listview;
    private List_item31Adapter adapter;
    private List<Bean31> bean31List;
    private TextView none;

    @Override
    protected String getLayoutTitle() {
        return "我的意见";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_yi_jian31;
    }

    @Override
    protected void initData() {
        bean31List=new ArrayList<>();
        SQLiteDatabase db = yiJian31DB.getWritableDatabase();
        Cursor cursor = db.query("myYiJian", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                bean31List.add(new Bean31(title, content, phone, time));
            } while (cursor.moveToNext());
            db.close();
        }
        adapter=new List_item31Adapter(bean31List,this);
        tiku_31_listview.setAdapter(adapter);
        if (bean31List.size()==0||bean31List==null){
            none.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        tiku_31_listview=findViewById(R.id.tiku_31_listview);
        none=findViewById(R.id.none);
        yiJian31DB=new YiJian31DB(this);
    }
}
