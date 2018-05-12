package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.ActivityCollector;
import com.example.jun.bisaixiangmu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TiKu38_3Activity extends BaseActivity implements View.OnClickListener {
    private GridView tiku_38_3_list;
    private Button tiku_38_3_bt_next;
    private CalendarView tiku_38_3_calendar;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    private Map<String ,String> map;

    @Override
    protected String getLayoutTitle() {
        return "定制班车";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku38_3;
    }

    @Override
    protected void initData() {
        tiku_38_3_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //String strTime=year+"-"+month+"-"+dayOfMonth;
                String strTime = getResources().getString(R.string.tiku_38_time, year, month, dayOfMonth);
                Log.e("calendarView",""+strTime);
                for (int i=0;i<list.size();i++){
                    if (strTime.equals(list.get(i))){
                        list.remove(i);
                    }
                }
                list.add(strTime);
                Log.e("list",""+list.size());
                adapter.notifyDataSetChanged();
                //timedia(strTime);
            }
        });
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        tiku_38_3_calendar=findViewById(R.id.tiku_38_3_calendar);
        tiku_38_3_bt_next=findViewById(R.id.tiku_38_3_bt_next);
        tiku_38_3_bt_next.setOnClickListener(this);
        tiku_38_3_list=findViewById(R.id.tiku_38_3_list);
        list=new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.tiku38_item, list);
        adapter.setDropDownViewResource(R.layout.tiku32_item);
        tiku_38_3_list.setAdapter(adapter);
        tiku_38_3_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(TiKu38_3Activity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tiku_38_3_bt_next:
                if (list.size()<=0){
                    Toast.makeText(this, "你至少需要选取一个时间", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(TiKu38_3Activity.this, TiKu38_4Activity.class);
                    intent.putStringArrayListExtra("timeList", (ArrayList<String>) list);
                    startActivity(intent);
                    ActivityCollector.addActivity(this);
                }
                break;
        }
    }
}
