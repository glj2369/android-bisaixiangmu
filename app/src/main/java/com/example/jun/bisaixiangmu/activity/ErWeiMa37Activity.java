package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;

public class ErWeiMa37Activity extends BaseActivity implements View.OnClickListener {
    private Spinner spinner;
    private EditText editTextYuan;
    private EditText editTextMiao;
    private Button button;

    private int carId=0;
    private String[] carIds=null;

    @Override
    protected String getLayoutTitle() {
        return "二维码支付";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_er_wei_ma37;
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
        spinner = findViewById(R.id.car_id_spinner);
        editTextMiao = findViewById(R.id.et_zhouqi_miao);
        editTextYuan = findViewById(R.id.et_jine);
        button = findViewById(R.id.bt_shengcheng);
        button.setOnClickListener(this);
        carIds=getResources().getStringArray(R.array.carid);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_item,carIds);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carId=position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_shengcheng:
//                Intent intent=new Intent(this,ZXingTestActivity.class);
//                intent.putExtra("carId",carId);
//                intent.putExtra("jine",editTextYuan.getText().toString().trim());
//                intent.putExtra("zhouqi",editTextMiao.getText().toString().trim());
//                startActivity(intent);
                if (TextUtils.isEmpty(editTextYuan.getText().toString().trim())
                        ||TextUtils.isEmpty(editTextMiao.getText().toString().trim())){
                    Toast.makeText(this, "不能含有空值", Toast.LENGTH_SHORT).show();
                }else {
                    ZXingTestActivity.startSelfActivity(this, carId,
                            editTextYuan.getText().toString().trim(),
                            editTextMiao.getText().toString().trim());
                }
                break;
            default:
                break;
        }
    }
}
