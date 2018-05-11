package com.example.jun.bisaixiangmu.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;

public class TiKu23Activity extends BaseActivity {
    private EditText mEtYuZhi;
    private TextView mTvYuZhi;
    private Button mBtSabe;

    @Override
    protected String getLayoutTitle() {
        return "账户设置";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku23;
    }

    @Override
    protected void initData() {
        SharedPreferences sp=getSharedPreferences("yuzhi",MODE_PRIVATE);
        String zhang_hu_gao_jing = sp.getString("zhang_hu_gao_jing", "50");

        mTvYuZhi.setText(getResources().getString(R.string.gaoJing_yuzhi,zhang_hu_gao_jing));
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        mEtYuZhi=findViewById(R.id.et_yuzhi);
        mTvYuZhi=findViewById(R.id.tv_show_yuzhi);
        mBtSabe=findViewById(R.id.bt_save_yuzhi);

        mBtSabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = mEtYuZhi.getText().toString().trim();
                if ("".equals(string)|| TextUtils.isEmpty(string)){
                    Toast.makeText(TiKu23Activity.this, "设置阈值不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences sp=getSharedPreferences("yuzhi",MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("zhang_hu_gao_jing",""+string);
                    boolean commit = edit.commit();
                    Log.e("commitcommit",""+commit);
                    mTvYuZhi.setText(getResources().getString(R.string.gaoJing_yuzhi,string));
                    Toast.makeText(TiKu23Activity.this, "设置成功", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
