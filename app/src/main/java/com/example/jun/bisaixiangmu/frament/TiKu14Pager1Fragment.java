package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.jun.bisaixiangmu.db.HuanJing4DB;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TiKu14Pager1Fragment extends Fragment {
    private Context context;
    private BarChart barChart;
    private List<String> mList;
    private TextView textview_show;
    private int max=0;
    private int min=0;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        barView();
    }
    private void barView() {
        for (int i=0;i<20;i++){
            int j=i*3;
            mList.add(getString(R.string.time,j));
        }
        /**图表具体设置*/
        Random random;//用于产生随机数
        BarData data;
        BarDataSet dataSet;
        ArrayList<BarEntry> entries = new ArrayList<>();//显示条目
        //横坐标标签mList
        random = new Random();//随机数
        for (int i = 0; i < 20; i++) {
            int profit = random.nextInt(30)+70 ;

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
            entries.add(new BarEntry(profit, i));
        }
        dataSet = new BarDataSet(entries, "");
        dataSet.setColors(Collections.singletonList(Color.GRAY));
        data = new BarData(mList, dataSet);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //图表描述
        barChart.setDescription("");
        barChart.setDrawBorders(false);//在折线图上添加边框
        barChart.setDrawGridBackground(false); //表格颜色
        barChart.setDescription("");
        barChart.setDrawBorders(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.setData(data);
        textview_show.setText(getString(R.string.pager1,max-min));
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(context).inflate(R.layout.tiku_14_pager1,null);
        barChart = view.findViewById(R.id.bar_chart);
        textview_show = view.findViewById(R.id.textview_show);

        mList=new ArrayList<>();
        return view;
    }
}
