package com.example.jun.bisaixiangmu.activity;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;


public class YuZhi7Activity extends BaseActivity implements View.OnClickListener {
    private TextView mTvSwitchYuzhi;
    private Switch mSwitchYuzhi;
    private String[] switchStrs = {"关", "开"};
    private String switchStr = "";
    private EditText mEtWendu;
    private EditText mEtGuangzhao;
    private EditText mEtShidu;
    private EditText mEtCo2;
    private EditText mEtPm25;
    private EditText mEtZhuangtai;
    private Button mBtYuzhi;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private LinearLayout layout_switch;

    @Override
    protected String getLayoutTitle() {
        return "阈值设置";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_yu_zhi7;
    }

    @Override
    protected void initData() {
        if (mSwitchYuzhi.isChecked()) {
            setEnableds();
        }
    }

    private void setEnableds() {
        mEtWendu.setEnabled(false);
        mEtGuangzhao.setEnabled(false);
        mEtShidu.setEnabled(false);
        mEtCo2.setEnabled(false);
        mEtPm25.setEnabled(false);
        mEtZhuangtai.setEnabled(false);
        mBtYuzhi.setClickable(false);
    }

    private void setEnabledt() {
        mEtWendu.setEnabled(true);
        mEtGuangzhao.setEnabled(true);
        mEtShidu.setEnabled(true);
        mEtCo2.setEnabled(true);
        mEtPm25.setEnabled(true);
        mEtZhuangtai.setEnabled(true);
        mBtYuzhi.setClickable(true);
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        sp = getSharedPreferences("yuzhi", MODE_PRIVATE);
        edit = sp.edit();
        mTvSwitchYuzhi = findViewById(R.id.tv_switch_yuzhi);
        layout_switch = findViewById(R.id.layout_switch);
        mSwitchYuzhi = findViewById(R.id.switch_yuzhi);
        mEtWendu = findViewById(R.id.et_wendu);
        mEtShidu = findViewById(R.id.et_shidu);
        mEtGuangzhao = findViewById(R.id.et_guangzhao);
        mEtPm25 = findViewById(R.id.et_pm2_5);
        mEtZhuangtai = findViewById(R.id.et_zhuangtai);
        mEtCo2 = findViewById(R.id.et_co2);
        mBtYuzhi = findViewById(R.id.bt_save_yuzhi);
        setListener();

    }

    private void setListener() {
        mBtYuzhi.setOnClickListener(this);

        mSwitchYuzhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(YuZhi7Activity.this, "当前按钮开关" + isChecked, Toast.LENGTH_SHORT).show();

                if (isChecked) {
                    mTvSwitchYuzhi.setText(switchStrs[1]);
                    //使布局不可用
                    setEnableds();
                } else {
                    mTvSwitchYuzhi.setText(switchStrs[0]);
                    //使布局可用
                    setEnabledt();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_save_yuzhi:

                String wendu = mEtWendu.getText().toString().trim();
                String shidu = mEtShidu.getText().toString().trim();
                String guangzhao = mEtGuangzhao.getText().toString().trim();
                String co2 = mEtCo2.getText().toString().trim();
                String pm25 = mEtPm25.getText().toString().trim();
                String zhuangtai = mEtZhuangtai.getText().toString().trim();
                if (TextUtils.isEmpty(wendu) || TextUtils.isEmpty(shidu) || TextUtils.isEmpty(guangzhao) ||
                        TextUtils.isEmpty(co2) || TextUtils.isEmpty(pm25) || TextUtils.isEmpty(zhuangtai)
                        ) {
                    Toast.makeText(this, "不能有空值", Toast.LENGTH_SHORT).show();
                } else {
                    edit.putString("wendu", wendu);
                    edit.putString("shidu", shidu);
                    edit.putString("guangzhao", guangzhao);
                    edit.putString("co2", co2);
                    edit.putString("pm25", pm25);
                    edit.putString("zhuangtai", zhuangtai);
                    edit.apply();
                    mSwitchYuzhi.setChecked(true);
                    }
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
