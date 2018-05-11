package com.example.jun.bisaixiangmu.activity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.DengBean;
import com.example.jun.bisaixiangmu.http.HttpUrl;
import com.example.jun.bisaixiangmu.utils.ListAdapterDeng;
import com.example.jun.bisaixiangmu.utils.SpinnerFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class HongDengActivity extends BaseActivity implements View.OnClickListener {
    public static final String HONGLVDENG = "http://192.168.1.106:8080" +
            "/transportservice/type/jason/action/GetTrafficLightConfigAction.do";
    //暂时使用红绿灯本地资源  DengBean

    private DengBean dengBean;
    private List<DengBean> list;
    private ListAdapterDeng adapter;
    private TextView error;
    private ListView mLsShowDeng;
    private Spinner mSpDeng;
    private Button mBtSelect;
    private int spinnerId = 0;
    private ProgressDialog progressDialog;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
        }

        ;
    };

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
//        Random s = null;
//        for (int i = 1; i <= 3; i++) {
//            s = new Random();
//            dengBean = new DengBean(i, s.nextInt(10), s.nextInt(10), s.nextInt(5));
//            list.add(dengBean);
//        }

        SpinnerFactory.getSpinner(this, mSpDeng, getResources().getStringArray(R.array.deng), new SpinnerFactory.OnItemSelected() {
            @Override
            public void onSelected(int position) {
                spinnerId = position + 1;
            }
        });
        netWork();

    }

    private void netWork() {
        list.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i <= 3; i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("TrafficLightId", i);
                        final String result = HttpUrl.posthttp(HONGLVDENG, jsonObject.toString());

                        final String serverinfo = new JSONObject(result).optString("serverinfo");
                        JSONObject jsonObject1 = new JSONObject(serverinfo);
                        final int redTime = jsonObject1.getInt("RedTime");
                        int greenTime = jsonObject1.getInt("GreenTime");
                        int yellowTime = jsonObject1.getInt("YellowTime");
                        list.add(new DengBean(i, redTime, greenTime, yellowTime));
                    }
                    updateView();

                } catch (JSONException e) {
                    e.printStackTrace();
                    setNotConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                    setNotConnection();
                }

            }
        }).start();
    }

    private void updateView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
                error.setVisibility(View.GONE);
                switch (spinnerId) {
                    case 1:
                        sheng(1);
                        Toast.makeText(HongDengActivity.this, "路口升序", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        jiang(1);
                        Toast.makeText(HongDengActivity.this, "路口降序", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        break;
                    case 3:
                        sheng(2);
                        Toast.makeText(HongDengActivity.this, "红灯升序", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        break;
                    case 4:
                        jiang(2);
                        Toast.makeText(HongDengActivity.this, "红灯降序", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        break;
                    case 5:
                        sheng(3);
                        Toast.makeText(HongDengActivity.this, "绿灯升序", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        break;
                    case 6:
                        jiang(3);
                        Toast.makeText(HongDengActivity.this, "绿灯降序", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        break;
                    case 7:
                        sheng(4);
                        Toast.makeText(HongDengActivity.this, "黄灯升序", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        break;
                    case 8:
                        jiang(4);
                        Toast.makeText(HongDengActivity.this, "绿灯降序", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        mBtSelect = findViewById(R.id.bt_select_deng);
        mLsShowDeng = findViewById(R.id.ls_show_deng);
        mSpDeng = findViewById(R.id.spinner_sort_deng);
        error=findViewById(R.id.error);
        setlistener();
        adapter = new ListAdapterDeng(this, list);
        mLsShowDeng.setAdapter(adapter);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("获取网络数据中...");
        progressDialog.setCancelable(false);
    }

    private void setlistener() {
        mBtSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressDialog.show();
        netWork();
    }
    //改
    public void setNotConnection(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                error.setVisibility(View.VISIBLE);
                if (progressDialog!=null&&progressDialog.isShowing()){
                    progressDialog.cancel();
                }
            }
        });

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
