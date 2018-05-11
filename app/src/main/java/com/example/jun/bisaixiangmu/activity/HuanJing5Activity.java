package com.example.jun.bisaixiangmu.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.GridViewBean;
import com.example.jun.bisaixiangmu.db.HuanJing4DB;
import com.example.jun.bisaixiangmu.utils.GridViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HuanJing5Activity extends BaseActivity {
    private GridView mGridView;

    private SharedPreferences sp;
    private Timer timer;

    //假设获取的网络数据
    //温度  湿度  光照  co2 pm2.5  道路状态1正常 2超出{18,26,55,80,79,2}
    private int[] data;
    private HuanJing4DB huanJing4DB;
    private SQLiteDatabase db;
    private List<GridViewBean> list;
    private GridViewAdapter adapter;

    @Override
    protected String getLayoutTitle() {
        return "环境指标";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_huan_jing5;
    }

    @Override
    protected void initData() {
        sp = getSharedPreferences("yuzhi", MODE_PRIVATE);
        initViewBack();
    }

    private void initViewBack() {
        if (data == null) {
            data = new int[]{18, 26, 55, 200, 300, 2};
        }
        list.clear();
        int wendu = Integer.parseInt(sp.getString("wendu", "20"));
        addList("温度", wendu, data[0]);
        int shidu = Integer.parseInt(sp.getString("shidu", "30"));
        addList("湿度", shidu, data[1]);
        int guangzhao = Integer.parseInt(sp.getString("guangzhao", "50"));
        addList("光照", guangzhao, data[2]);
        int co2 = Integer.parseInt(sp.getString("co2", "100"));
        addList("CO2", co2, data[3]);
        int pm25 = Integer.parseInt(sp.getString("pm25", "80"));
        addList("PM2.5", pm25, data[4]);
        int zhuangtai = Integer.parseInt(sp.getString("zhuangtai", "2"));
        addList("道路状态", zhuangtai, data[5]);
        Log.e("initData", "wendu" + wendu + "shidu"
                + shidu + "guangzhao" + guangzhao + "co2" + co2 + "pm25" + pm25 + "zhuangtai" + zhuangtai);
        adapter.notifyDataSetChanged();
    }

    private void addList(String title, int data1, int data2) {
        if (data2 > data1) {
            list.add(new GridViewBean(title, data2, R.drawable.zhibiao_1));
        } else {
            list.add(new GridViewBean(title, data2, R.drawable.zhibiao));
        }
    }


    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        mGridView = findViewById(R.id.gridview1);
        list = new ArrayList<>();
        adapter = new GridViewAdapter(list, this);
        mGridView.setAdapter(adapter);
        setLisenter();
        huanJing4DB = new HuanJing4DB(this);
        timer = new Timer();
         //timer.schedule(new TimerTaskTest3(), 500, 3000);
        // timer.schedule(new TimerTaskTest60(), 500, 3000);
    }

    private void setLisenter() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String title = list.get(position).getTitle();
                Log.e("HuanJing5Activity","startActivity XianShi6Activity"+position+title);
                Intent intent=new Intent(HuanJing5Activity.this,XianShi6Activity.class);
                intent.putExtra("title",title);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }


    private class TimerTaskTest60 extends TimerTask {
        @Override
        public void run() {
            Log.e("TimerTaskTest60", "------------------------");
            db = huanJing4DB.getWritableDatabase();
            Cursor cursor = db.query("zhibiao", null, null, null, null, null, null);
            if (cursor.getCount() >= 20) {
                int id = 0;
                if (cursor.moveToFirst()) {
                    id = cursor.getInt(cursor.getColumnIndex("id"));
                }
                if (id >= 40000) {
                    db.delete("zhibiao", null, null);
                } else {
                    db.delete("zhibiao", "id=?", new String[]{String.valueOf(id)});
                }
                cursor.close();
                db.close();
            } else {
                cursor.close();
                db.close();
            }
        }
    }

    private class TimerTaskTest3 extends TimerTask {
        @Override
        public void run() {

            Random random = new Random();
            data[0] = random.nextInt(10) + 25;
            data[1] = random.nextInt(15) + 70;
            data[2] = random.nextInt(200) + 500;
            data[3] = random.nextInt(150) + 200;
            data[4] = random.nextInt(50) + 80;
            data[5] = random.nextInt(5) + 1;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initViewBack();
                }
            });
            ContentValues valuse = new ContentValues();
            valuse.put("weidu", "" + data[0]);
            valuse.put("shidu", "" + data[1]);
            valuse.put("guangzhao", "" + data[2]);
            valuse.put("co2", "" + data[3]);
            valuse.put("pm25", "" + data[4]);
            valuse.put("zhuangtai", "" + data[5]);
            db = huanJing4DB.getWritableDatabase();
            db.insert("zhibiao", null, valuse);
            db.close();
            Log.e("TimerTaskTest3", "+++++++++++++++++++++++++++");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause","======================");
        if (timer != null) {
            timer.purge();
            timer.cancel();
            Log.e("timer","======================");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume","======================");
        if (timer != null) {
            timer=new Timer();
            timer.schedule(new TimerTaskTest3(), 500, 3000);
            timer.schedule(new TimerTaskTest60(), 500, 3000);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart","======================");
    }
}
