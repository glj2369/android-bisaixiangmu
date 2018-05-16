package com.example.jun.bisaixiangmu.activity;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.frament.TiKu14Pager1Fragment;
import com.example.jun.bisaixiangmu.frament.TiKu14Pager2Fragment;
import com.example.jun.bisaixiangmu.frament.TiKu14Pager3Fragment;
import com.example.jun.bisaixiangmu.frament.TiKu14Pager4Fragment;
import com.example.jun.bisaixiangmu.utils.MyFragmentPagerAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class TiKu14Activity extends BaseActivity implements View.OnClickListener {
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
        return R.layout.activity_ti_ku14;
    }

    @Override
    protected void initData() {
        gengXin();
        List<Fragment> viewList = new ArrayList<>();
        viewList.add(new TiKu14Pager1Fragment());
        viewList.add(new TiKu14Pager2Fragment());
        viewList.add(new TiKu14Pager3Fragment());
        viewList.add(new TiKu14Pager4Fragment());
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), viewList);
        viewPager.setAdapter(adapter);

        final List<TextView> textViewList = new ArrayList<>();
        TextView dotFirst = (TextView) findViewById(R.id.image_1);
        TextView dotFSecond = (TextView) findViewById(R.id.image_2);
        TextView dotThrid = (TextView) findViewById(R.id.image_3);
        TextView dotFours = (TextView) findViewById(R.id.image_4);
        textViewList.add(dotFirst);
        textViewList.add(dotFSecond);
        textViewList.add(dotThrid);
        textViewList.add(dotFours);
        oldPosition = 0;
        textViewList.get(oldPosition).setBackgroundResource(R.drawable.bt_border);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                textViewList.get(oldPosition).setBackgroundResource(R.drawable.bt_border_off);
                textViewList.get(position).setBackgroundResource(R.drawable.bt_border);
                oldPosition = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void gengXin() {
        listMax = new ArrayList<>();
        listMin = new ArrayList<>();
        Random random;
        for (int i = 0; i < 6; i++) {
            random = new Random();
            listMax.add(random.nextInt(5) + 21);
            listMin.add(random.nextInt(5) + 12);
        }
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < listMax.size(); i++) {
            Entry entry = new Entry(listMax.get(i), i);
            entries.add(entry);
        }
        List<Entry> entries2 = new ArrayList<>();
        for (int i = 0; i < listMin.size(); i++) {
            Entry entry = new Entry(listMin.get(i), i);
            entries2.add(entry);
        }
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
        LineDataSet dataSetMax = new LineDataSet(entries, "");
        dataSetMax.setColor(Color.RED);
        LineDataSet dataSetMin = new LineDataSet(entries2, "");
        dataSetMin.setColor(Color.BLUE);
        LineData data = new LineData(mList);
        data.addDataSet(dataSetMax);
        data.addDataSet(dataSetMin);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        // xAxis.setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setNoDataText("暂无数据显示");
        lineChart.setDescription("");
        lineChart.setDrawBorders(false);//在折线图上添加边框
        lineChart.setDrawGridBackground(false); //表格颜色
        lineChart.setData(data);//设置显示数据
        lineChart.getAxisLeft().setStartAtZero(false);

    }


    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        mTvWendu = findViewById(R.id.tiku_14_wendu);
        mTvToDay = findViewById(R.id.tiku_14_today);
        mIvGengXin = findViewById(R.id.tiku_14_gengxin);
        lineChart = findViewById(R.id.tiku_14_line_chart);
        viewPager = findViewById(R.id.tiku_14_viewpager);
        mInflater = getLayoutInflater();
        mIvGengXin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_14_gengxin:
                Toast.makeText(this, "点击更新", Toast.LENGTH_SHORT).show();
                int max = listMax.get(1);
                int min = listMin.get(1);
                int avg = (max + min) / 2;
                mTvWendu.setText(avg + "°");
                mTvToDay.setText("今天：" + min + "-" + max + "℃");
                //lineChart.setData(data);
                break;
            default:
                break;
        }
    }
}
