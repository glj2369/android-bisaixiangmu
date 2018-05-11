package com.example.jun.bisaixiangmu.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean9;
import com.example.jun.bisaixiangmu.dialog.Dialog9;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class List_item9Adapter extends BaseAdapter {
    private Context context;
    private List<Bean9> bean9List;


    public List_item9Adapter(Context context, List<Bean9> bean9List) {
        this.context = context;
        this.bean9List = bean9List;

    }

    @Override
    public int getCount() {
        return bean9List.size();
    }

    @Override
    public Object getItem(int position) {
        return bean9List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.list_item9, parent, false);


        final Bean9 bean9 = bean9List.get(position);
        TextView tvId = convertView.findViewById(R.id.tv_id);
        CheckBox checkBox = convertView.findViewById(R.id.cb_piliang);
        Button button = convertView.findViewById(R.id.bt_jilu);
        ImageView imageView = convertView.findViewById(R.id.logo);
        RelativeLayout layout = convertView.findViewById(R.id.item);
        TextView chePai = convertView.findViewById(R.id.che_pai);
        TextView cheZhu = convertView.findViewById(R.id.che_zhu);
        TextView yuE = convertView.findViewById(R.id.yu_e);

        checkBox.setChecked(bean9.getaBoolean());
        tvId.setText((position + 1) + "");
        imageView.setImageResource(bean9.getImageId());
        chePai.setText(bean9.getChePai());
        cheZhu.setText(bean9.getCheZhu());
        yuE.setText("余额:" + bean9.getYuE() + "元");
        //后面23题是设置余额预警的
        SharedPreferences sp=context.getSharedPreferences("yuzhi",MODE_PRIVATE);
        String zhang_hu_gao_jing = sp.getString("zhang_hu_gao_jing", "50");
        if (bean9.getYuE() < Integer.parseInt(zhang_hu_gao_jing)) {//这里50以后的阈值，，是有个范围的
            layout.setBackgroundResource(R.drawable.item9_bg);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean9.setaBoolean(isChecked);
                Log.e("onCheckedChanged",""+isChecked);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog9 dialog9=new Dialog9(context,"",bean9List,"车牌号：");
                dialog9.setCancelable(false);
                dialog9.show();
            }
        });
        return convertView;
    }


}
