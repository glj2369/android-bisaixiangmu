package com.example.jun.bisaixiangmu.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;

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

public class TiKu27Activity extends BaseActivity {
    private TextView textViewPm25;
    private TextView textViewWenDu;
    private TextView textViewShiDu;
    private TextView tiku_27_info_title1;
    private TextView tiku_27_info_content1;
    private TextView tiku_27_info_title2;
    private TextView tiku_27_info_content2;
    private Timer timer;
    private String guangzhao="600";

    @Override
    protected String getLayoutTitle() {
        return "生活助手";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku27;
    }

    @Override
    protected void initData() {
        netWork();
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        textViewPm25 = findViewById(R.id.tiku_27pm25);
        textViewWenDu = findViewById(R.id.tiku_27wendu);
        textViewShiDu = findViewById(R.id.tiku_27shidu);
        tiku_27_info_title1=findViewById(R.id.tiku_27_info_title1);
        tiku_27_info_content1=findViewById(R.id.tiku_27_info_content1);
        tiku_27_info_title2=findViewById(R.id.tiku_27_info_title2);
        tiku_27_info_content2=findViewById(R.id.tiku_27_info_content2);
        timer = new Timer();
        SharedPreferences sp=getSharedPreferences("yuzhi", MODE_PRIVATE);
        guangzhao = sp.getString("guangzhao", "600");
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
            timer.schedule(new TimerTask27_3(), 2000, 3000);
        }
    }
    class TimerTask27_3 extends TimerTask {

        @Override
        public void run() {
            RandomZhi();
        }
    }
    //随机值
    private void RandomZhi() {
        Random random = new Random();
        int pm25 = random.nextInt(350) + 50;
        int wendu = random.nextInt(10) + 20;
        int shidu = random.nextInt(30) + 40;
        int guangzhao = random.nextInt(200) + 500;
        Map<String, Object> map = new HashMap<>();
        map.put("pm25", "" + pm25);
        map.put("wendu", "" + wendu);
        map.put("shidu", "" + shidu);
        map.put("guangzhao", guangzhao);
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
                    map.put("guangzhao", 577);

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

                Log.e("TiKu27", "这里执行了" + map.size());

                String wendu = (String) map.get("wendu");
                textViewWenDu.setText(wendu + "℃");

                String shidu = (String) map.get("shidu");
                textViewShiDu.setText(shidu + "%");



                int guangzhao2 = (int) map.get("guangzhao");
                if (guangzhao2<520){
                    tiku_27_info_title1.setTextColor(Color.GREEN);
                    tiku_27_info_title1.setText("非常弱");
                    tiku_27_info_content1.setText("您无需担心紫外线");
                }else if (guangzhao2>=520&& guangzhao2<=Integer.parseInt(guangzhao)){
                    tiku_27_info_title1.setTextColor(Color.YELLOW);
                    tiku_27_info_title1.setText("弱");
                    tiku_27_info_content1.setText("外出适当涂抹低倍数防晒霜");
                }else {
                    tiku_27_info_title1.setTextColor(Color.RED);
                    tiku_27_info_title1.setText("强");
                    tiku_27_info_content1.setText("外出需要涂抹中倍数防晒霜");
                }
                String pm25 = (String) map.get("pm25");
                int intPm25 = Integer.parseInt(pm25);
                textViewPm25.setText(pm25);
                if (intPm25>=0&&intPm25<100){
                    tiku_27_info_title2.setTextColor(Color.BLACK);
                    tiku_27_info_title2.setText("良好");
                    tiku_27_info_content2.setText("气象条件会对晨练影响不大");
                }else if (intPm25>=100&&intPm25<200){
                    tiku_27_info_title2.setTextColor(Color.BLACK);
                    tiku_27_info_title2.setText("轻度");
                    tiku_27_info_content2.setText("受天气影响，较不宜晨练");
                }else if (intPm25>=200&&intPm25<300){
                    tiku_27_info_title2.setText("重度");
                    tiku_27_info_title2.setTextColor(Color.RED);
                    tiku_27_info_content2.setText("减少外出，出行注意戴口罩");
                }else {
                    tiku_27_info_title2.setText("爆表");
                    tiku_27_info_title2.setTextColor(Color.RED);
                    tiku_27_info_content2.setText("停止一切外出活动");
                }
            }
        });

    }
}
