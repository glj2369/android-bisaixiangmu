package com.example.jun.bisaixiangmu.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class TiKu24Activity extends BaseActivity implements View.OnClickListener {
    private TextView mTvWendu;
    private TextView mTvToDay;
    private ImageView mIvGengXin;
    private LineChart lineChart;
    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private int oldPosition;//记录当前点的位置。
    private LayoutInflater mInflater;
    //模拟数据
    private List<Integer> listMax;
    private List<Integer> listMin;


    @Override
    protected String getLayoutTitle() {
        return "生活助手";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku24;
    }

    @Override
    protected void initData() {
        listMax = new ArrayList<>();
        listMin = new ArrayList<>();
        Random random;
        for (int i = 0; i < 6; i++) {
            random = new Random();
            listMax.add(random.nextInt(5) + 21);
            listMin.add(random.nextInt(5) + 12);
        }
        List<Entry> entrieMax=new ArrayList<>();
        List<Entry> entrieMix=new ArrayList<>();
        for (int i=0;i<listMax.size();i++){
            entrieMax.add(new Entry(listMax.get(i),i));
            entrieMix.add(new Entry(listMin.get(i),i));
        }
        List<String> stringXs = initX();
        LineDataSet lineDataSetMax=new LineDataSet(entrieMax,"最高");
        lineDataSetMax.setColor(Color.RED);
        LineDataSet lineDataSetMin=new LineDataSet(entrieMix,"最低");
        lineDataSetMin.setColor(Color.BLUE);
        LineData lineData=new LineData(stringXs);
        lineData.addDataSet(lineDataSetMax);
        lineData.addDataSet(lineDataSetMin);

        lineChart.getAxisRight().setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        lineChart.setNoDataText("暂无数据显示");
        lineChart.setDescription("");
        lineChart.getAxisLeft().setStartAtZero(false);
        lineChart.setDrawBorders(false);//在折线图上添加边框
        lineChart.setDrawGridBackground(false);
        lineChart.setData(lineData);
    }

    private List<String> initX() {
        List<String> mList = new ArrayList<>();
        mList.add("昨天");
        mList.add("今天");
        mList.add("明天");
        Calendar calendar = Calendar.getInstance();
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            //1表示星期天
            case 1:
                mList.add("周二");
                mList.add("周三");
                mList.add("周四");
                break;
            //2表示星期一
            case 2:
                mList.add("周三");
                mList.add("周四");
                mList.add("周五");
                break;
            //3表示星期二
            case 3:
                mList.add("周四");
                mList.add("周五");
                mList.add("周六");
                break;
            //4表示星期三
            case 4:
                mList.add("周五");
                mList.add("周六");
                mList.add("周日");
                break;
            //5表示星期四
            case 5:
                mList.add("周六");
                mList.add("周日");
                mList.add("周一");
                break;
            //6表示星期五
            case 6:
                mList.add("周日");
                mList.add("周一");
                mList.add("周二");
                break;
            //7表示星期六
            case 7:
                mList.add("周一");
                mList.add("周二");
                mList.add("周三");
                break;
        }
        return mList;
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        mTvWendu = findViewById(R.id.tiku_24_wendu);
        mTvToDay = findViewById(R.id.tiku_24_today);
        mIvGengXin = findViewById(R.id.tiku_24_gengxin);
        lineChart = findViewById(R.id.tiku_24_line_chart);
        mIvGengXin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_24_gengxin:
                Toast.makeText(this, "点击更新", Toast.LENGTH_SHORT).show();
                int max=listMax.get(1);
                int min=listMin.get(1);
                int avg=(max+min)/2;
                mTvWendu.setText(avg+"°");
                mTvToDay.setText("今天："+min+"-"+max+"℃");
                //lineChart.setData(data);
                break;
            default:
                break;
        }
    }
}
