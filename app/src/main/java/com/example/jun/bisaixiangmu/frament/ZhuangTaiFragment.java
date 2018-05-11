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

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.db.HuanJing4DB;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ZhuangTaiFragment extends Fragment {
    private Context context;
    private LineChart lineChart;
    private HuanJing4DB huanJing4DB;
    private List<Integer> zhuangtais;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SQLiteDatabase db = huanJing4DB.getReadableDatabase();
        zhuangtais=new ArrayList<>();
        Cursor cursor = db.query("zhibiao", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                int zhuangtai = cursor.getInt(cursor.getColumnIndex("zhuangtai"));
                zhuangtais.add(zhuangtai);

            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }else {
            cursor.close();
            db.close();
        }

        List<Entry> entries = new ArrayList<>();
        for (int i=0;i<zhuangtais.size();i++) {
            Entry entry = new Entry(zhuangtais.get(i), i);
            entries.add(entry);
        }

        LineDataSet dataSet = new LineDataSet(entries, "道路状态");
        dataSet.setColor(Color.GRAY);

        List<String> mList=new ArrayList<>();
        for (int i=0;i<20;i++){
            int j=i*3;
            mList.add(Calendar.getInstance().get(Calendar.MINUTE)+":"+getString(R.string.time,j));
            Log.e("-----------",""+ mList.get(i));
        }

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
        lineChart.setDrawBorders(false);
        lineChart.getAxisLeft().setStartAtZero(false);
        //lineChart.getAxisLeft().setDrawGridLines(false); //取消横向的水平线

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhuangtai_viewpager, null);
        lineChart = view.findViewById(R.id.line_cart_fragment);
        huanJing4DB = new HuanJing4DB(context);
        return view;
    }
}
