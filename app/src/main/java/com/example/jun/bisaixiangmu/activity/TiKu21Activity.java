package com.example.jun.bisaixiangmu.activity;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.DengBean;
import com.example.jun.bisaixiangmu.utils.ListAdapterDeng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TiKu21Activity extends BaseActivity {
    private Spinner mSpDeng;
    private String[] strings;
    private DengBean dengBean;
    private List<DengBean> list = new ArrayList<>();
    private ListAdapterDeng adapter;
    private ListView mLsShowDeng;
    private ImageView mIvShow;
    private Timer timer = new Timer();
    private int flag = 0;
    private int[] imageIds = {R.drawable.hong_heng, R.drawable.huang_heng, R.drawable.lv_heng};
    @Override
    protected String getLayoutTitle() {
        return "红绿灯管理";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku21;
    }

    @Override
    protected void initData() {
        Random s = null;
        for (int i = 1; i <= 3; i++) {
            s = new Random();
            dengBean = new DengBean(i, s.nextInt(10), s.nextInt(10), s.nextInt(5));
            list.add(dengBean);
        }
        adapter = new ListAdapterDeng(this, list);
        mLsShowDeng.setAdapter(adapter);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, strings);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item);
        mSpDeng.setAdapter(spinnerAdapter);
        mSpDeng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position + 1) {
                    case 1:
                        sheng(1);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        jiang(1);
                        adapter.notifyDataSetChanged();
                        break;
                    case 3:
                        sheng(2);
                        adapter.notifyDataSetChanged();
                        break;
                    case 4:
                        jiang(2);
                        adapter.notifyDataSetChanged();
                        break;
                    case 5:
                        sheng(3);
                        adapter.notifyDataSetChanged();
                        break;
                    case 6:
                        jiang(3);
                        adapter.notifyDataSetChanged();
                        break;
                    case 7:
                        sheng(4);
                        adapter.notifyDataSetChanged();
                        break;
                    case 8:
                        jiang(4);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void sheng(final int i) {
        Collections.sort(list, new Comparator<DengBean>() {
            @Override
            public int compare(DengBean o1, DengBean o2) {
                switch (i) {
                    case 1:
                        return o1.getId() - o2.getId();
                    case 2:
                        return o1.getHong() - o2.getHong();
                    case 3:
                        return o1.getLv() - o2.getLv();
                    case 4:
                        return o1.getHuang() - o2.getHuang();
                }
                return 0;
            }
        });
    }

    private void jiang(final int i) {
        Collections.sort(list, new Comparator<DengBean>() {
            @Override
            public int compare(DengBean o1, DengBean o2) {
                switch (i) {
                    case 1:
                        return o2.getId() - o1.getId();
                    case 2:
                        return o2.getHong() - o1.getHong();
                    case 3:
                        return o2.getLv() - o1.getLv();
                    case 4:
                        return o2.getHuang() - o1.getHuang();
                }
                return 0;
            }
        });
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        mSpDeng = findViewById(R.id.spinner_sort_deng);
        strings = getResources().getStringArray(R.array.deng);
        mLsShowDeng = findViewById(R.id.ls_show_deng);
        mIvShow = findViewById(R.id.iv_show_deng);
        //timer.schedule(new MyTimer(), 1000, 2000);
        AnimationDrawable drawable= (AnimationDrawable) mIvShow.getBackground();
        drawable.start();

    }
    protected void onPause() {
        super.onPause();
        if (timer!=null){
            timer.purge();
            timer.cancel();
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
