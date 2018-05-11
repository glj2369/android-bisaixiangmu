package com.example.jun.bisaixiangmu.activity;


import android.graphics.Color;

import com.example.jun.bisaixiangmu.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TestChar extends BaseActivity {
    final List<String> mList = new ArrayList<>();

    @Override
    protected String getLayoutTitle() {
        return "";
    }

    @Override
    protected int getLayouId() {
        return R.layout.quxuan_layout;
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
        lineView();
        barView();
        preChart();
        horizontal_bar();
    }
    private void horizontal_bar(){
        Random random;//用于产生随机数
        HorizontalBarChart horizontal_bar=findViewById(R.id.horizontal_bar);
        BarData data;
        BarDataSet dataSet;
        ArrayList<BarEntry> entries = new ArrayList<>();//显示条目
        //横坐标标签mList
        random = new Random();//随机数
        for (int i = 0; i < 6; i++) {
            float profit = random.nextFloat() * 100;
            //entries.add(BarEntry(float val,int positon);
            entries.add(new BarEntry(profit, i));
        }
        dataSet = new BarDataSet(entries, "公司年利润报表");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        data = new BarData(mList, dataSet);
        horizontal_bar.getAxisLeft().setEnabled(false);
        //设置Y方向上动画animateY(int time);
        horizontal_bar.animateY(3000);
        XAxis xAxis = horizontal_bar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //图表描述
        horizontal_bar.setDescription("公司前半年财务报表(单位：万元)");
        horizontal_bar.setData(data);
        horizontal_bar.animateY(3000);
    }
    private void lineView() {
        //设置数据
        //LimitLine line = new LimitLine(10f);
        //    data.addLimitLine(line);//添加图表的限制线
        //dataset.setColors(ColorTemplate.COLORFUL_COLORS);//让数据集显示不同的颜色
        mList.add(0, "一月");
        mList.add(1, "二月");
        mList.add(2, "三月");
        mList.add(3, "四月");
        mList.add(4, "五月");
        mList.add(5, "六月");

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Entry entry = new Entry((float) ((Math.random()) * 80), i);
            entries.add(entry);
        }
        LineChart mLineChart = (LineChart) findViewById(R.id.line_cart);
        //一个LineDataSet就是一条线
        LineDataSet dataSet = new LineDataSet(entries, "温度");
        LineData data = new LineData(mList, dataSet);//对应横坐标标签和数据集
        XAxis xAxis = mLineChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//这个是设置轴是否从零开始，或者数据开始的时候，轴坐标开始
        //设置X轴值为字符串
        mLineChart.setNoDataText("暂无数据显示");
        mLineChart.setDescription("设置描述信息");
        xAxis.setValueFormatter(new XAxisValueFormatter() {
            @Override
            public String getXValue(String s, int i, ViewPortHandler viewPortHandler) {
                return mList.get((int) i); //mList为存有月份的集合
                //与设置自定义X轴类似，设置曲线显示值为整数
                //int IValue = (int) i;
                // return String.valueOf(IValue);
            }
        });
        mLineChart.setDrawBorders(false);//在折线图上添加边框
        mLineChart.setDrawGridBackground(false); //表格颜色
        mLineChart.setGridBackgroundColor(Color.GRAY & 0x70FFFFFF); //表格的颜色，设置一个透明度
        mLineChart.setTouchEnabled(true); //可点击
        mLineChart.setDragEnabled(true);  //可拖拽
        mLineChart.setScaleEnabled(true);  //可缩放
        mLineChart.setPinchZoom(false);
        mLineChart.setBackgroundColor(Color.WHITE);
        mLineChart.setVisibleXRange(0, 12);   //x轴可显示的坐标范围
        YAxis axisRight = mLineChart.getAxisRight(); //y轴右边标示
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);
        YAxis axisLeft= mLineChart.getAxisLeft(); //y轴右边标示
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawLabels(false);
        mLineChart.setData(data);//设置显示数据
        //设置动画效果
        mLineChart.animateY(2000, Easing.EasingOption.Linear);
        mLineChart.animateX(2000, Easing.EasingOption.Linear);
        mLineChart.invalidate();

    }

    private void barView() {
        /**图表具体设置*/
        Random random;//用于产生随机数

        BarChart chart = findViewById(R.id.bar_chart);
        BarData data;
        BarDataSet dataSet;
        ArrayList<BarEntry> entries = new ArrayList<>();//显示条目
        //横坐标标签mList
        random = new Random();//随机数
        for (int i = 0; i < 6; i++) {
            float profit = random.nextFloat() * 100;
            //entries.add(BarEntry(float val,int positon);
            entries.add(new BarEntry(profit, i));
        }
        dataSet = new BarDataSet(entries, "公司年利润报表");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        data = new BarData(mList, dataSet);

        //设置Y方向上动画animateY(int time);
        chart.animateY(3000);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisRight().setEnabled(false);
        //图表描述
        chart.setDescription("公司前半年财务报表(单位：万元)");
        chart.setData(data);
    }

    private PieChart mPieChart;

    private void preChart() {
        mPieChart = findViewById(R.id.pie_chart);
        // 显示百分比
        mPieChart.setUsePercentValues(true);
        TreeMap<String, Float> data = new TreeMap<>();
        data.put("data1", 0.5f);
        data.put("data2", 0.3f);
        data.put("data3", 0.1f);
        data.put("data4", 0.1f);
        setData(data);
        // 设置动画
//        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
//
//        // 设置显示的比例
//        Legend l = mPieChart.getLegend();
//        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
    }

    public void setData(TreeMap<String, Float> data) {
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        int i = 0;
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            // entry的输出结果如key0=value0等
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            float value = (float) entry.getValue();
            xVals.add(key);
            yVals1.add(new Entry(value, i++));
        }

        PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
        // 设置饼图区块之间的距离
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);

        // 添加颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);
//        colors.add(ColorTemplate.getHoloBlue());
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        dataSet.setColors(colors);
        dataSet.setSelectionShift(0f);

        PieData data1 = new PieData(xVals, dataSet);
        data1.setValueFormatter(new PercentFormatter());
        data1.setValueTextSize(10f);
        data1.setValueTextColor(Color.BLACK);
        mPieChart.setData(data1);

        // undo all highlights
        mPieChart.highlightValues(null);
        mPieChart.invalidate();
    }
}