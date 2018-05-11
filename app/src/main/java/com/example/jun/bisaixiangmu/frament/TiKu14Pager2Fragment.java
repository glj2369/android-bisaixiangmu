package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.content.Entity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TiKu14Pager2Fragment extends Fragment {
    private Context context;
    private LineChart lineChart;
    private List<String> mList;
    private TextView textview_show;
    private int max=0;
    private int min=0;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        barView();

    }

    private void barView() {
        for (int i = 0; i < 20; i++) {
            int j = i * 3;
            mList.add(getString(R.string.time, j));
            Log.e("-----------", "" + mList.get(i));
        }
        /**图表具体设置*/
        Random random;//用于产生随机数
        List<Entry> entries = new ArrayList<>();//显示条目
        //横坐标标签mList
        random = new Random();//随机数
        for (int i = 0; i < 20; i++) {
            int profit = random.nextInt(10)+15;
            //entries.add(BarEntry(float val,int positon);
            if (i==0){
                max=profit;
                min=profit;
            }else {
                if (profit>max){
                    max=profit;
                }
                if(profit<min){
                    min=profit;
                }
            }
            entries.add( new Entry(profit, i));
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(Color.GRAY);
        LineData data = new LineData(mList, dataSet);//对应横坐标标签和数据集
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(8);
        xAxis.setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);//取消纵向的水平线
        lineChart.setNoDataText("暂无数据显示");
        lineChart.setData(data);//设置显示数据
        lineChart.setTouchEnabled(true); //可点击
        lineChart.setDragEnabled(true);  //可拖拽
        lineChart.setScaleEnabled(true);  //可缩放
        lineChart.setDrawBorders(false);//在折线图上添加边框
        lineChart.setDrawGridBackground(false); //表格颜色
        lineChart.setDescription("");
        lineChart.getAxisLeft().setStartAtZero(false);
        textview_show.setText(getString(R.string.pager2,max,min));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.tiku_14_pager2, null);
        lineChart = view.findViewById(R.id.line_cart);
        mList = new ArrayList<>();
        textview_show = view.findViewById(R.id.textview_show);
        return view;
    }
}
