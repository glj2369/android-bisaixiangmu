package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.DengBean;
import com.example.jun.bisaixiangmu.bean.MenuList;

import java.util.List;

public class ListAdapterMenu extends BaseAdapter {
    private Context context;
    private List<MenuList> list;

    public ListAdapterMenu(Context context, List<MenuList> list) {
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
        MenuList menuList=list.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.list_menu_item,parent,false);
        ImageView imageView=convertView.findViewById(R.id.menu_iv_name);
        TextView textView=convertView.findViewById(R.id.menu_tv_name);
        imageView.setImageResource(menuList.getImageId());
        textView.setText(menuList.getTitle());
        return convertView;
    }
}
