package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean16_fragment2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class List16_adapter_frag2 extends BaseAdapter {
    private List<Bean16_fragment2> datas;
    private Context context;

    public List16_adapter_frag2(List<Bean16_fragment2> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bean16_fragment2 data = datas.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.list_item16fragment2,parent,false);
        TextView textView1=convertView.findViewById(R.id.item_textview_1);
        TextView textView2=convertView.findViewById(R.id.item_textview_2);
        TextView textView3=convertView.findViewById(R.id.item_textview_3);
        TextView textView4=convertView.findViewById(R.id.item_textview_4);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("EEEE");
        SimpleDateFormat simpleDateFormat3=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=new Date();
        textView1.setText(context.getResources().getString(R.string.fragment_2_item1,
                simpleDateFormat.format(date),simpleDateFormat2.format(date)));
        textView2.setText(context.getResources().getString(R.string.fragment_2_item2,
                data.getName(),data.getChePai()));
        textView3.setText(context.getResources().getString(R.string.fragment_2_item3,
                data.getChongZhi(),data.getYuE()));
        textView4.setText(context.getResources().getString(R.string.fragment_2_item4,simpleDateFormat3.format(date)));
        return convertView;
    }
}
