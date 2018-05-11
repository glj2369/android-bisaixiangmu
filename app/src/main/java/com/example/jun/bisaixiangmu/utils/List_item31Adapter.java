package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean31;

import java.util.List;

public class List_item31Adapter extends BaseAdapter {
    private List<Bean31> list;
    private Context context;

    public List_item31Adapter(List<Bean31> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.list_item31,parent,false);
        TextView tijiao_title=convertView.findViewById(R.id.list_item31_tijiao_title);
        TextView tijiao_time=convertView.findViewById(R.id.list_item31_tijiao_time);
        TextView zhuantai=convertView.findViewById(R.id.list_item31_zhuantai);
        TextView huifunei=convertView.findViewById(R.id.list_item31_huifunei);
        TextView huifutime=convertView.findViewById(R.id.list_item31_huifutime);
        Bean31 bean31 = list.get(position);
        tijiao_title.setText("标题："+bean31.getTitle());
        tijiao_time.setText("提交时间："+bean31.getSave_time());
        return convertView;
    }
}
