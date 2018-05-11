package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.ActivityCollector;
import com.example.jun.bisaixiangmu.R;

import java.util.ArrayList;

public class TiKu38_4Activity extends BaseActivity implements View.OnClickListener {
    private Spinner tiku_38_spinner;
    private String[] strs;
    private String str="一号路口";
    private EditText name;
    private EditText phone;
    private Button tiku_38_4_bt_next;
    private ArrayList<String> timeList;


    @Override
    protected String getLayoutTitle() {
        return "定制班车";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku38_4;
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
        name = findViewById(R.id.tiku_38_name);
        phone = findViewById(R.id.tiku_38_phone);
        Intent intent = getIntent();
        timeList = intent.getStringArrayListExtra("timeList");
        Log.e("timeList", "timeList " + timeList.size());
        tiku_38_spinner = findViewById(R.id.tiku_38_spinner);
        tiku_38_4_bt_next = findViewById(R.id.tiku_38_4_bt_next);
        tiku_38_4_bt_next.setOnClickListener(this);
        strs = getResources().getStringArray(R.array.address);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, strs);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        tiku_38_spinner.setAdapter(adapter);
        tiku_38_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str = strs[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ActivityCollector.addActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_38_4_bt_next:
                String str1 = name.getText().toString().trim();
                String str2 = phone.getText().toString().trim();
                if ("".equals(str1)||"".equals(str2)
                        || TextUtils.isEmpty(str1)||TextUtils.isEmpty(str2)){
                    Toast.makeText(this, "姓名或手机不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(TiKu38_4Activity.this,TiKu38_5Activity.class);
                    intent.putExtra("name",str1);
                    intent.putExtra("phone",str2);
                    intent.putExtra("address",str);
                    intent.putStringArrayListExtra("timeList",timeList);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
