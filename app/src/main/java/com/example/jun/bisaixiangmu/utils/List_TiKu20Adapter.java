package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean20;

import java.util.List;

public class List_TiKu20Adapter extends BaseAdapter {
    private List<Bean20> bean20List;
    private Context context;

    public List_TiKu20Adapter(List<Bean20> bean20List, Context context) {
        this.bean20List = bean20List;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean20List.size();
    }

    @Override
    public Object getItem(int position) {
        return bean20List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.list_item_tiku20,parent,false);
        ImageView imageView=convertView.findViewById(R.id.image_item);
        TextView textViewChePai=convertView.findViewById(R.id.chepai_item);
        TextView textViewYuE=convertView.findViewById(R.id.yue_item);
        Bean20 bean20 = bean20List.get(position);
        imageView.setImageResource(bean20.getImageId());
        textViewChePai.setText(""+bean20.getChePai());
        textViewYuE.setText("余额："+bean20.getYuE());
        return convertView;
    }
}
