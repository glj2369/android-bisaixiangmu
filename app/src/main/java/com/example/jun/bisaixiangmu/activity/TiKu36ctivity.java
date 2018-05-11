package com.example.jun.bisaixiangmu.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean36;
import com.example.jun.bisaixiangmu.utils.Grid_item36Adpter;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TiKu36ctivity extends BaseActivity implements View.OnClickListener {
    private GridView gridView36;
    private ImageView tiku_36_gengxin;
    private TextView tiku_36_time;
    private TextView tiku_36_adress;
    private ImageView tiku_36_1_image;
    private Grid_item36Adpter adpter;
    private Calendar calendar;
    private List<Bean36> bean36List;

    private String[] tianQis = {"晴", "晴", "多云转阴"};
    private int[] colors = {0xFF5EB8FA, 0xFF208AE2, 0xFF95B3D0};
    private List<String> timeStrings;
    private int[] images = {R.drawable.tiku_36image1, R.drawable.tiku_36image2, R.drawable.tiku_36image3};

    @Override
    protected String getLayoutTitle() {
        return "天气信息";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku36ctivity;
    }

    @Override
    protected void initData() {
        String time = new SimpleDateFormat("yyyy年MM月dd日 EE").format(new Date());
        tiku_36_time.setText(time);
        timeStrings = new ArrayList<>();
        getSweek(calendar);
        getInfo(calendar);
        getNow();
    }

    private void getNow() {
        Bean36 bean36 = bean36List.get(0);
        int now = (bean36.getMaxWendu()+ bean36.getMinWendu()) / 2;
        tiku_36_adress.setText(getResources().getString(R.string.tiku_36_adress,"北京",now));
        tiku_36_1_image.setImageResource(bean36.getImage());
    }

    private void getInfo(Calendar calendar) {
        bean36List.clear();
        for (int x = 0; x < 5; x++) {
            Random random = new Random();
            int i = random.nextInt(3);
            bean36List.add(new Bean36(colors[i], calendar.get(Calendar.DAY_OF_MONTH),
                    timeStrings.get(x), images[i], tianQis[i],
                    random.nextInt(10) + 21, random.nextInt(10) + 10));
        }
    }

    private void getSweek(Calendar calendar) {
        timeStrings.add("今天");
        timeStrings.add("明天");
        timeStrings.add("后天");
        //4 是星期三  5是星期四 6是星期五
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                //星期天 1 2    3  4
                timeStrings.add("周三");
                timeStrings.add("周四");
                break;
            case 2:
                //星期一 2 3     4  5
                timeStrings.add("周四");
                timeStrings.add("周五");
                break;
            case 3:
                timeStrings.add("周五");
                timeStrings.add("周六");
                break;
            case 4:

                timeStrings.add("周六");
                timeStrings.add("周日");
                break;
            case 5:
                timeStrings.add("周日");
                timeStrings.add("周一");
                break;
            case 6:
                timeStrings.add("周一");
                timeStrings.add("周二");
                break;
            case 7:

                timeStrings.add("周二");
                timeStrings.add("周三");
                break;
        }
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        tiku_36_time=findViewById(R.id.tiku_36_time);
        tiku_36_adress=findViewById(R.id.tiku_36_adress);
        tiku_36_1_image=findViewById(R.id.tiku_36_1_image);

        gridView36 = findViewById(R.id.gridview36);
        tiku_36_gengxin = findViewById(R.id.tiku_36_gengxin);
        calendar = Calendar.getInstance();
        bean36List = new ArrayList<>();
        adpter = new Grid_item36Adpter(bean36List, this);
        gridView36.setAdapter(adpter);
        tiku_36_gengxin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_36_gengxin:
                getInfo(calendar);
                adpter.notifyDataSetChanged();
                getNow();
                break;
            default:
                break;
        }
    }
}
