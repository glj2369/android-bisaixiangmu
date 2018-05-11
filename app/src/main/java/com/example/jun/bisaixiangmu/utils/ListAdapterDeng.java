package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.DengBean;


import java.util.List;

public class ListAdapterDeng extends BaseAdapter {
    private Context context;
    private List<DengBean> list;

    public ListAdapterDeng(Context context, List<DengBean> list) {
        this.context = context;
        this.list = list;
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
        DengBean dengBean=list.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        TextView t1=convertView.findViewById(R.id.t1);
        TextView t2=convertView.findViewById(R.id.t2);
        TextView t3=convertView.findViewById(R.id.t3);
        TextView t4=convertView.findViewById(R.id.t4);
        t1.setText(dengBean.getId()+"");
        t2.setText(dengBean.getHong()+"");
        t3.setText(dengBean.getLv()+"");
        t4.setText(dengBean.getHuang()+"");
        return convertView;
    }

}
