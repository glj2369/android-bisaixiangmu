package com.example.jun.bisaixiangmu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.DengBean;
import com.example.jun.bisaixiangmu.utils.ListAdapterDeng;
import com.example.jun.bisaixiangmu.utils.SpinnerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class HongDengActivity extends BaseActivity implements View.OnClickListener {

    //暂时使用红绿灯本地资源  DengBean

    private DengBean dengBean;
    private List<DengBean> list = new ArrayList<>();
    private ListAdapterDeng adapter;
    private ListView mLsShowDeng;
    private Spinner mSpDeng;
    private Button mBtSelect;
    private int spinnerId = 0;

    @Override
    protected String getLayoutTitle() {
        return "红绿灯管理";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_hong_deng;
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
        SpinnerFactory.getSpinner(this, mSpDeng, getResources().getStringArray(R.array.deng), new SpinnerFactory.OnItemSelected() {
            @Override
            public void onSelected(int position) {
                spinnerId = position + 1;
            }
        });
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        mBtSelect = findViewById(R.id.bt_select_deng);
        mLsShowDeng = findViewById(R.id.ls_show_deng);
        mSpDeng = findViewById(R.id.spinner_sort_deng);
        setlistener();
    }

    private void setlistener() {
        mBtSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (spinnerId) {
            case 1:
                sheng(1);
                Toast.makeText(this, "路口升序", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case 2:
                jiang(1);
                Toast.makeText(this, "路口降序", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case 3:
                sheng(2);
                Toast.makeText(this, "红灯升序", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case 4:
                jiang(2);
                Toast.makeText(this, "红灯降序", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case 5:
                sheng(3);
                Toast.makeText(this, "绿灯升序", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case 6:
                jiang(3);
                Toast.makeText(this, "绿灯降序", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case 7:
                sheng(4);
                Toast.makeText(this, "黄灯升序", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case 8:
                jiang(4);
                Toast.makeText(this, "绿灯降序", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void jiang(final int i) {
        Collections.sort(list, new Comparator<DengBean>() {
            @Override
            public int compare(DengBean o1, DengBean o2) {
                switch (i) {
                    case 1:
                        int id = o2.getId() - o1.getId();
                        return id;
                    case 2:
                        int hong = o2.getHong() - o1.getHong();
                        return hong;
                    case 3:
                        int lv = o2.getLv() - o1.getLv();
                        return lv;
                    case 4:
                        int huang = o2.getHuang() - o1.getHuang();
                        return huang;
                }
                return 0;
            }
        });
    }

    private void sheng(final int i) {
        Collections.sort(list, new Comparator<DengBean>() {
            @Override
            public int compare(DengBean o1, DengBean o2) {
                switch (i) {
                    case 1:
                        int id = o1.getId() - o2.getId();
                        return id;
                    case 2:
                        int hong = o1.getHong() - o2.getHong();
                        return hong;
                    case 3:
                        int lv = o1.getLv() - o2.getLv();
                        return lv;
                    case 4:
                        int huang = o1.getHuang() - o2.getHuang();
                        return huang;
                }
                return 0;
            }
        });
    }
}
