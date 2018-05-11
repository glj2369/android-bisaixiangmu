package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean11;
import com.example.jun.bisaixiangmu.dialog.Dialog11;

import java.util.List;
import java.util.Map;

public class List_item11Adapter extends BaseAdapter {
    private List<Bean11> bean11List;
    private Context context;

    public List_item11Adapter(List<Bean11> bean11List, Context context) {
        this.bean11List = bean11List;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean11List.size();
    }

    @Override
    public Object getItem(int position) {
        return bean11List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Bean11 bean11 = bean11List.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item11, parent, false);
        TextView textView1 = convertView.findViewById(R.id.tiku_11_item_1);
        TextView textView2 = convertView.findViewById(R.id.tiku_11_item_2);
        TextView textView3 = convertView.findViewById(R.id.tiku_11_item_3);
        TextView textView4 = convertView.findViewById(R.id.tiku_11_item_4);
        CheckBox checkBox = convertView.findViewById(R.id.tiku_11_item_5);
        Button button = convertView.findViewById(R.id.tiku_11_item_6);
        textView1.setText(bean11.getId()+"");
        textView2.setText(bean11.getHong()+"");
        textView3.setText(bean11.getLv()+"");
        textView4.setText(bean11.getHuang()+"");
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean11.setChecked(isChecked);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击设置这里弹出一个对话框
                Dialog11 dialog11=new Dialog11(context, bean11List);
                dialog11.setCancelable(true);
                dialog11.show();

            }
        });
        return convertView;
    }
}
