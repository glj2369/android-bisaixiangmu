package com.example.jun.bisaixiangmu.activity;

import android.app.DatePickerDialog;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;


import java.util.Calendar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 第8题：编码实现公司交通单双号管制功能第8题：编码实现公司交通单双号管制功能
 */
public class ChuXingActivity extends BaseActivity implements View.OnClickListener {
    private String toDay = "2018年4月24日";
    private int day;
    private int offDay;
    private int offyear;
    private int offMonth;
    private boolean bday;
    private String[] switchStrs={"关","开"};
    private String[] strData = {"单号出行车辆：1、3", "双号出行车辆：2"};
    private int[] imageIds = {R.drawable.hong, R.drawable.huang, R.drawable.lv};
    private String strNumber = "";
    private DatePickerDialog dp;
    private TextView mTvShowDate;
    private TextView mTvShowNumber;
    private Switch mSwitchNumber1;
    private Switch mSwitchNumber2;
    private Switch mSwitchNumber3;
    private ImageView mIvShow;
    private Timer timer = new Timer();
    private int flag = 0;

    @Override
    protected String getLayoutTitle() {
        return "出行管理";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_chu_xing;
    }

    @Override
    protected void initData() {
        mTvShowDate.setText(toDay);
        //timer.schedule(new MyTimer(), 1000, 2000);
        AnimationDrawable drawable= (AnimationDrawable) mIvShow.getBackground();
        drawable.start();
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        mTvShowDate = findViewById(R.id.tv_data);
        mTvShowNumber = findViewById(R.id.tv_number);
        mSwitchNumber1 = findViewById(R.id.switch_1);
        mSwitchNumber2 = findViewById(R.id.switch_2);
        mSwitchNumber3 = findViewById(R.id.switch_3);
        mIvShow = findViewById(R.id.iv_show_deng);
        datainit();
        setListener();
    }

    private void setListener() {
        mTvShowDate.setOnClickListener(this);
        checkSwitch(mSwitchNumber1);
        checkSwitch(mSwitchNumber2);
        checkSwitch(mSwitchNumber3);

    }
    private void checkSwitch(final Switch s){
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(ChuXingActivity.this, "当前按钮开关"+isChecked, Toast.LENGTH_SHORT).show();
                if (isChecked){
                    s.setText(switchStrs[1]);
                }else {
                    s.setText(switchStrs[0]);
                }
            }
        });
    }
    private void datainit() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        bday = checkDay(day);
        switchCheck(bday);
        mTvShowNumber.setText(strNumber);
        toDay = year + "年" + month + "月" + day + "日";
        Toast.makeText(this, "" + year, Toast.LENGTH_SHORT).show();
        dp = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int iyear, int monthOfYear, int dayOfMonth) {
                long maxDate = datePicker.getMaxDate();//日历最大能设置的时间的毫秒值
                offyear = datePicker.getYear();//年
                offMonth = datePicker.getMonth();//月-1
                offDay = datePicker.getDayOfMonth();//日
                boolean boffday = checkDay(offDay);
                switchCheck(boffday);
                setText();
                Toast.makeText(getApplicationContext(), iyear + ":" + (monthOfYear + 1) + ":" + dayOfMonth, Toast.LENGTH_LONG).show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));//2013:初始年份，2：初始月份-1 ，1：初始日期

    }

    private void switchCheck(boolean b) {
        if (b) {
            mSwitchNumber1.setChecked(true);
            mSwitchNumber1.setText(switchStrs[1]);
            mSwitchNumber1.setClickable(true);
            mSwitchNumber2.setChecked(false);
            mSwitchNumber2.setText(switchStrs[0]);
            mSwitchNumber2.setClickable(false);
            mSwitchNumber3.setChecked(true);
            mSwitchNumber3.setText(switchStrs[1]);
            mSwitchNumber3.setClickable(true);
        } else {
            mSwitchNumber1.setChecked(false);
            mSwitchNumber1.setClickable(false);
            mSwitchNumber1.setText(switchStrs[0]);
            mSwitchNumber2.setChecked(true);
            mSwitchNumber2.setClickable(true);
            mSwitchNumber2.setText(switchStrs[1]);
            mSwitchNumber3.setChecked(false);
            mSwitchNumber3.setClickable(false);
            mSwitchNumber3.setText(switchStrs[0]);
        }
    }

    private boolean checkDay(int i) {
        if (i % 2 == 1) {
            strNumber = strData[0];
            return true;
            //Toast.makeText(this, "单", Toast.LENGTH_SHORT).show();
        } else {
            strNumber = strData[1];
            return false;
            // Toast.makeText(this, "双", Toast.LENGTH_SHORT).show();
        }
    }
    protected void onPause() {
        super.onPause();
        if (timer!=null){
            timer.purge();
            timer.cancel();
        }
    }
    private void setText() {
        mTvShowNumber.setText(strNumber);
        toDay = offyear + "年" + offMonth+1 + "月" + offDay + "日";
        mTvShowDate.setText(toDay);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_data:
                dp.show();
                break;
            default:
                break;
        }
    }

    class MyTimer extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mIvShow.setImageDrawable(getResources().getDrawable(imageIds[flag]));
                }
            });
            flag++;
            if (flag==3){
                flag=0;
            }
        }
    }
}
