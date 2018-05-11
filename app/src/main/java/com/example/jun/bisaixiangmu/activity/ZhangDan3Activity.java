package com.example.jun.bisaixiangmu.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean3;
import com.example.jun.bisaixiangmu.db.ChongzhiHistory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZhangDan3Activity extends BaseActivity {

    private List<Bean3> bean3List ;
    private Spinner spinner;
    private int spinnerId=0;
    private Button button;
    private ListView listView;
    private String[] spinnerStrs;
    private TextView error;
    private  ChongzhiHistory chongzhiHistory;
    private boolean one=true;

    @Override
    protected String getLayoutTitle() {
        return "账单管理";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_zhang_dan3;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        chongzhiHistory=new ChongzhiHistory(this);
        spinnerStrs=getResources().getStringArray(R.array.zhangdan);
        spinner=findViewById(R.id.spinner_sort_time);
        listView=findViewById(R.id.list_zhangdan);
        error=findViewById(R.id.tv_error);
        button=findViewById(R.id.bt_chaxun);
        final ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.spinner_item,spinnerStrs);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerId=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        select(spinnerId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询功能
                select(spinnerId);

            }
        });
    }

    private void select(int spinnerId) {
        bean3List= new ArrayList<>();
        SQLiteDatabase db = chongzhiHistory.getReadableDatabase();
        Cursor query = db.query("history", null, null, null, null, null, null);
        if (query.moveToFirst()){
            do {

                int carId = query.getInt(query.getColumnIndex("carId"));
                int chongNum = query.getInt(query.getColumnIndex("chongNum"));
                String person = query.getString(query.getColumnIndex("personName"));
                String historyTime = query.getString(query.getColumnIndex("historyTime"));
                Bean3 bean3=new Bean3(carId,chongNum,person,historyTime);
                bean3List.add(bean3);
                Log.e("数据库的查询",""+historyTime);
            }while (query.moveToNext());
        }

        if (spinnerId==1){
            //降序
            Collections.reverse(bean3List);
            Log.e("reversevreverse","--------------");
        }else {
            if (one){
                //第一次降序
                Collections.reverse(bean3List);
                Log.e("reversevreverse","--------------");
                one=false;
            }
        }
        if (bean3List==null){
            bean3List= new ArrayList<>();
            error.setVisibility(View.VISIBLE);
        }else {
            error.setVisibility(View.GONE);
            TimeAdapter adapter=new TimeAdapter();
            listView.setAdapter(adapter);
        }
    }

    class TimeAdapter extends BaseAdapter {

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
            Bean3 bean3 = bean3List.get(position);
            convertView = LayoutInflater.from(ZhangDan3Activity.this).inflate(R.layout.list_item_time, parent, false);
            TextView xuhao = convertView.findViewById(R.id.tv_xuhao);
            TextView chehao = convertView.findViewById(R.id.tv_chehao);
            TextView jine=convertView.findViewById(R.id.tv_jine);
            TextView person=convertView.findViewById(R.id.tv_person);
            TextView time=convertView.findViewById(R.id.tv_time);
            xuhao.setText((position+1)+"");
            chehao.setText(bean3.getChehao()+"");
            jine.setText(bean3.getJine()+"");
            person.setText(bean3.getPerson()+"");
            time.setText(bean3.getTime()+"");
            return convertView;
        }
    }
}
