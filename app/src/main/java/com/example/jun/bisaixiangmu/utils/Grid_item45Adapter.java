package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean45;

import java.util.List;

public class Grid_item45Adapter extends BaseAdapter {
    private Context context;
    private List<Bean45> bean45Listb;

    public Grid_item45Adapter(Context context, List<Bean45> bean45Listb) {
        this.context = context;
        this.bean45Listb = bean45Listb;
    }

    @Override
    public int getCount() {
        return bean45Listb.size();
    }

    @Override
    public Object getItem(int position) {
        return bean45Listb.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.tiku_45_item,parent,false);
        LinearLayout linearLayout=view.findViewById(R.id.tiku_45_item_background);
        TextView carName=view.findViewById(R.id.tiku_45_item_carid);
        TextView carText=view.findViewById(R.id.tiku_45_item_cartext);
        TextView jinE=view.findViewById(R.id.tiku_45_item_text);

        Bean45 bean45 = bean45Listb.get(position);
        linearLayout.setBackgroundColor(bean45.getBackground());
        carName.setText(bean45.getCarName());
        carText.setText(bean45.getCarText());
        jinE.setText(bean45.getYuE()+"");
        return view;
    }
}
