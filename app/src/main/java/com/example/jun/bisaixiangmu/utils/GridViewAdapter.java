package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.GridViewBean;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private List<GridViewBean> list;
    private Context context;

    public GridViewAdapter(List<GridViewBean> list, Context context) {
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
        GridViewBean viewBean = list.get(position);
        ViewHodler viewHodler;
        if (convertView==null){
            viewHodler=new ViewHodler();
            convertView= LayoutInflater.from(context).inflate(R.layout.gridview_item,parent,false);
            viewHodler.linearLayout_color=convertView.findViewById(R.id.item_bg);
            viewHodler.textView_title=convertView.findViewById(R.id.text);
            viewHodler.textView_zhi=convertView.findViewById(R.id.zhi);
            convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.linearLayout_color.setBackgroundResource(viewBean.getResColor());
        viewHodler.textView_title.setText(viewBean.getTitle());
        viewHodler.textView_zhi.setText(viewBean.getZhi()+"");
        return convertView;
    }
    class ViewHodler {
        LinearLayout linearLayout_color;
        TextView textView_title;
        TextView textView_zhi;
    }
}
