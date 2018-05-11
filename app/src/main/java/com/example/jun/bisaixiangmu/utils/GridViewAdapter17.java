package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.GridViewBean;
import com.example.jun.bisaixiangmu.bean.GridViewBean17;

import java.util.List;

public class GridViewAdapter17 extends BaseAdapter {
    private List<GridViewBean17> list;
    private Context context;

    public GridViewAdapter17(List<GridViewBean17> list, Context context) {
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
        GridViewBean17 viewBean = list.get(position);
        ViewHodler viewHodler;
        if (convertView == null) {
            viewHodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_17_item, parent, false);
            viewHodler.relativeLayout = convertView.findViewById(R.id.ralyout);
            viewHodler.dengji = convertView.findViewById(R.id.dengji1);
            viewHodler.dengji_message = convertView.findViewById(R.id.dengji_message1);
            viewHodler.image_ziwaixian1 = convertView.findViewById(R.id.image_ziwaixian1);
            viewHodler.image_miaoshu = convertView.findViewById(R.id.image_miaoshu);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        viewHodler.relativeLayout.setBackgroundColor(viewBean.getResColor());
        viewHodler.dengji.setText(viewBean.getZhi());
        viewHodler.dengji_message.setText(viewBean.getTitle());
        viewHodler.image_ziwaixian1.setImageResource(viewBean.getImageId());
        viewHodler.image_miaoshu.setText(viewBean.getImageMiaoshu());
        return convertView;
    }

    class ViewHodler {
        RelativeLayout relativeLayout;
        TextView dengji;
        TextView dengji_message;
        ImageView image_ziwaixian1;
        TextView image_miaoshu;

    }
}