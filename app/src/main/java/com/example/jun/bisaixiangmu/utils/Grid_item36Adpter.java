package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean36;

import java.util.List;


public class Grid_item36Adpter extends BaseAdapter {
    private List<Bean36> bean36List;
    private Context context;

    public Grid_item36Adpter(List<Bean36> bean36List, Context context) {
        this.bean36List = bean36List;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean36List.size();
    }

    @Override
    public Object getItem(int position) {
        return bean36List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bean36 bean36 = bean36List.get(position);
        convertView= LayoutInflater.from(context).inflate((R.layout.grid_item36),parent,false);
        LinearLayout layout=convertView.findViewById(R.id.tiku_36_layout_bg1);
        TextView textView1=convertView.findViewById(R.id.tiku_36_time_1);
        ImageView imageView=convertView.findViewById(R.id.tiku_36_image_1);
        TextView textView2=convertView.findViewById(R.id.tiku_36_tianqi_1);
        TextView textView3=convertView.findViewById(R.id.tiku_36_wendu_1);
        layout.setBackgroundColor(bean36.getBgColorInt());
        textView1.setText(context.getResources().getString(R.string.tiku_36_time,
                bean36.getTimeInt(),bean36.getTimeString()));
        imageView.setImageResource(bean36.getImage());
        textView2.setText(context.getResources().getString(R.string.tiku_36_tianqi,bean36.getTianqi()));
        textView3.setText(context.getResources().getString(R.string.tiku_36_wendu,
                bean36.getMaxWendu(),bean36.getMinWendu()));
        return convertView;
    }
}
