package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean3;

import java.util.List;

public class List_item45fragment3Adapter extends BaseAdapter {
    private Context context;
    private List<Bean3> bean3List;

    public List_item45fragment3Adapter(Context context, List<Bean3> bean3List) {
        this.context = context;
        this.bean3List = bean3List;
    }

    @Override
    public int getCount() {
        return bean3List.size();
    }

    @Override
    public Object getItem(int position) {
        return bean3List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.list_item45_fragment,parent,false);
        TextView textView4=convertView.findViewById(R.id.time);
        TextView textView3=convertView.findViewById(R.id.jine);
        TextView textView2=convertView.findViewById(R.id.carid);
        TextView textView1=convertView.findViewById(R.id.id);
        Bean3 bean3 = bean3List.get(position);
        textView1.setText((position+1)+"");
        textView2.setText(bean3.getChehao()+"");
        textView3.setText(bean3.getJine()+"");
        textView4.setText(bean3.getTime()+"");
        return convertView;
    }
}
