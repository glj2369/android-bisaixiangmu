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
import com.example.jun.bisaixiangmu.bean.Bean22;
import com.example.jun.bisaixiangmu.dialog.Dialog22;
import com.example.jun.bisaixiangmu.dialog.Dialog9;

import java.util.List;

public class List_item22Adapter extends BaseAdapter {
    private List<Bean22> bean22List;
    private Context context;

    public List_item22Adapter(List<Bean22> bean22List, Context context) {
        this.bean22List = bean22List;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean22List.size();
    }

    @Override
    public Object getItem(int position) {
        return bean22List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Bean22 bean22 = bean22List.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.list_item22,parent,false);
        TextView textViewId=convertView.findViewById(R.id.list_item22_id);
        TextView textViewYuE=convertView.findViewById(R.id.list_item22_yue);
        CheckBox checkBox=convertView.findViewById(R.id.list_item22_cb);
        Button button=convertView.findViewById(R.id.list_item22_bt);
        textViewId.setText(""+bean22.getId());
        textViewYuE.setText(""+bean22.getYuE());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog22 dialog22=new Dialog22(context,bean22List,"车辆编号：");
                dialog22.setCancelable(false);
                dialog22.show();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean22.setCb(isChecked);
            }
        });
        return convertView;
    }
}
