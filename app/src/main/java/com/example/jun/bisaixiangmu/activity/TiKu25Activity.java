package com.example.jun.bisaixiangmu.activity;

import android.app.Application;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean25;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TiKu25Activity extends BaseActivity {
    @ColorInt
    public static final int COLOR_1 = 0xFF0ebd12;
    @ColorInt
    public static final int COLOR_2 = 0xFF98ed1f;
    @ColorInt
    public static final int COLOR_3 = 0xFFffff01;
    @ColorInt
    public static final int COLOR_4 = 0xFFff0103;
    @ColorInt
    public static final int COLOR_5 = 0xFF4c060e;
    private TextView textViewPm25;
    private TextView textViewWenDu;
    private TextView textViewShiDu;

    private TextView daoLu_1;
    private TextView daoLu_2;
    private TextView daoLu_3;

    private ImageView zhuangTai_1;
    private ImageView zhuangTai_2;
    private ImageView zhuangTai_3;

    private Timer timer;

    @Override
    protected String getLayoutTitle() {
        return "路况查询";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku25;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            timer.schedule(new TimerTask25_3(), 2000, 3000);
        }
    }

    @Override
    protected void initData() {
        netWork();
    }
    //随机值
    private void RandomZhi() {
        Random random = new Random();
        int pm25 = random.nextInt(50) + 80;
        int wendu = random.nextInt(10) + 20;
        int shidu = random.nextInt(30) + 40;
        int zhuantai_1 = random.nextInt(5);
        int zhuantai_2 = random.nextInt(5);
        int zhuantai_3 = random.nextInt(5);
        Map<String, Object> map = new HashMap<>();
        map.put("pm25", "" + pm25);
        map.put("wendu", "" + wendu);
        map.put("shidu", "" + shidu);
        map.put("zhuantai_1", "" + zhuantai_1);
        map.put("zhuantai_2", "" + zhuantai_2);
        map.put("zhuantai_3", "" + zhuantai_3);
        showText(map);
    }
    //网络值
    private void netWork() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = new HashMap<>();
                OkHttpClient client = new OkHttpClient();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
                Request request = new Request.Builder()
                        .url("http://www.baidu.com")
                        .post(requestBody)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    map.put("pm25", "100");
                    map.put("wendu", "30");
                    map.put("shidu", "56");
                    map.put("zhuantai_1", "5");
                    map.put("zhuantai_2", "3");
                    map.put("zhuantai_3", "1");
                    showText(map);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }

    private void showText(final Map<String, Object> map) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("TiKu25", "这里执行了" + map.size());
                String pm25 = (String) map.get("pm25");
                textViewPm25.setText(pm25 + "");

                String wendu = (String) map.get("wendu");
                textViewWenDu.setText(wendu + "℃");

                String shidu = (String) map.get("shidu");
                textViewShiDu.setText(shidu + "%");

                String zhuantai_1 = (String) map.get("zhuantai_1");

                Bean25 bean25_1 = checkedZhuanTai(Integer.parseInt(zhuantai_1));
                zhuangTai_1.setBackgroundColor(bean25_1.getColorInt());
                daoLu_1.setText("一号道路"+bean25_1.getColorName());

                String zhuantai_2 = (String) map.get("zhuantai_2");
                Bean25 bean25_2 = checkedZhuanTai(Integer.parseInt(zhuantai_2));
                zhuangTai_2.setBackgroundColor(bean25_2.getColorInt());
                daoLu_2.setText("二号道路"+bean25_2.getColorName());

                String zhuantai_3 = (String) map.get("zhuantai_3");
                Bean25 bean25_3 = checkedZhuanTai(Integer.parseInt(zhuantai_3));
                zhuangTai_3.setBackgroundColor(bean25_3.getColorInt());
                daoLu_3.setText("三号道路"+bean25_3.getColorName());

            }
        });
    }

    private Bean25 checkedZhuanTai(int i) {
        switch (i) {
            case 1:
                return new Bean25(COLOR_1, "通畅");
            case 2:
                return new Bean25(COLOR_2, "较通畅");
            case 3:
                return new Bean25(COLOR_3, "拥挤");
            case 4:
                return new Bean25(COLOR_4, "堵塞");
            case 5:
                return new Bean25(COLOR_5, "爆表");
            default:
                return new Bean25(COLOR_1, "通畅");
        }
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        textViewPm25 = findViewById(R.id.tiku_25pm2_5);
        textViewWenDu = findViewById(R.id.tiku_25wendu);
        textViewShiDu = findViewById(R.id.tiku_25shidu);
        daoLu_1 = findViewById(R.id.tiku_25tv_1);
        daoLu_2 = findViewById(R.id.tiku_25tv_2);
        daoLu_3 = findViewById(R.id.tiku_25tv_3);
        zhuangTai_1 = findViewById(R.id.tiku_25iv_1);
        zhuangTai_2 = findViewById(R.id.tiku_25iv_2);
        zhuangTai_3 = findViewById(R.id.tiku_25iv_3);
        timer = new Timer();
    }

    class TimerTask25_3 extends TimerTask {

        @Override
        public void run() {
            RandomZhi();
        }
    }
}
