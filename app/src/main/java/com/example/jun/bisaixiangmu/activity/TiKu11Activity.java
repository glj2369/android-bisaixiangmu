package com.example.jun.bisaixiangmu.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean11;
import com.example.jun.bisaixiangmu.http.NetUtil1;
import com.example.jun.bisaixiangmu.utils.List_item11Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TiKu11Activity extends BaseActivity implements View.OnClickListener {
    private Button tiku_11_piliang;
    private Button tiku_11_chaxun;
    private Spinner tiku_11_spinner;
    private String[] spinnerStrs;
    private ListView ls_show_deng;
    private ProgressDialog progressDialog;
    private List_item11Adapter adapter;
    private List<Bean11> bean11List;
    private int spinnerId = 0;

    @Override
    protected String getLayoutTitle() {
        return "红绿灯管理";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku11;
    }

    @Override
    protected void initData() {
        bean11List = new ArrayList<>();
        SharedPreferences zhouqi = getSharedPreferences("zhouqi", MODE_PRIVATE);
//        String lukouid = zhouqi.getString("lukouid", "1");
//        String[] ids = lukouid.split(":");
        int hong = Integer.parseInt(zhouqi.getString("hong", "30"));
        int lv = Integer.parseInt(zhouqi.getString("lv", "30"));
        int huang = Integer.parseInt(zhouqi.getString("huang", "5"));
        Random random = null;
        for (int i = 1; i <= 5; i++) {
            random = new Random();
            Bean11 bean11 = new Bean11(i,
                    random.nextInt(hong),
                    random.nextInt(lv),
                    random.nextInt(huang),
                    false);
            bean11List.add(bean11);
        }
        adapter = new List_item11Adapter(bean11List, this);
        ls_show_deng.setAdapter(adapter);
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        progressDialog = new ProgressDialog(TiKu11Activity.this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("数据加载中...");
        progressDialog.setCancelable(false);

        spinnerStrs = getResources().getStringArray(R.array.deng);
        tiku_11_piliang = findViewById(R.id.tiku_11_piliang);
        tiku_11_chaxun = findViewById(R.id.tiku_11_chaxun);
        tiku_11_spinner = findViewById(R.id.tiku_11_spinner);
        ls_show_deng = findViewById(R.id.ls_show_deng);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, spinnerStrs);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        tiku_11_spinner.setAdapter(adapter);
        tiku_11_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerId = position + 1;
                Log.e("spinnerId", "" + spinnerId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tiku_11_piliang.setOnClickListener(this);
        tiku_11_chaxun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_11_chaxun:
                postHttp();
                break;
            case R.id.tiku_11_piliang:
                break;
            default:
                break;
        }
    }


    private void shengxu(final int i) {
        Collections.sort(bean11List, new Comparator<Bean11>() {
            @Override
            public int compare(Bean11 o1, Bean11 o2) {
                switch (i){
                    case 1:
                        return o1.getId()-o2.getId();
                    case 2:
                        return o1.getHong()-o2.getHong();
                    case 3:
                        return o1.getLv()-o2.getLv();
                    case 4:
                        return o1.getHuang()-o2.getHuang();
                }
                return 0;
            }
        });

    }
    private void jiangxu(final int i) {
        Collections.sort(bean11List, new Comparator<Bean11>() {
            @Override
            public int compare(Bean11 o1, Bean11 o2) {
                switch (i){
                    case 1:
                        return o2.getId()-o1.getId();
                    case 2:
                        return o2.getHong()-o1.getHong();
                    case 3:
                        return o2.getLv()-o1.getLv();
                    case 4:
                        return o2.getHuang()-o1.getHuang();
                }
                return 0;
            }
        });

    }
    private void updateView() {
        //bean11List = new ArrayList<>();
        switch (spinnerId) {
            case 1:
                shengxu(1);
                break;
            case 2:
                jiangxu(1);
                break;
            case 3:
                shengxu(2);
                break;
            case 4:
                jiangxu(2);
                break;
            case 5:
                shengxu(3);
                break;
            case 6:
                jiangxu(3);
                break;
            case 7:
                shengxu(4);
                break;
            case 8:
                jiangxu(4);
                break;
            default:
                break;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });

    }


    private void postHttp() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", "1");
            jsonObject.put("hong", "hong");
            jsonObject.put("lv", "lv");
            jsonObject.put("huang", "huang");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {

                String json = NetUtil1.postHttp("url", jsonObject.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
                Log.e("postHttp", "" + json);
                try {
                    bean11List.clear();
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        bean11List.add(new Bean11(i + 1,
                                jsonObject1.getInt("hong"),
                                jsonObject1.getInt("lv"),
                                jsonObject1.getInt("huang"), false));
                    }

                    updateView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
