package com.example.jun.bisaixiangmu.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.GridViewBean17;
import com.example.jun.bisaixiangmu.utils.GridViewAdapter;
import com.example.jun.bisaixiangmu.utils.GridViewAdapter17;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TiKu17Activity extends BaseActivity {

    private List<GridViewBean17> list;
    private GridViewAdapter17 adapter;
    private GridView gridview17;
    private Timer timer;
    @Override
    protected String getLayoutTitle() {
        return "生活指数";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku17;
    }

    @Override
    protected void initData() {
        getData();
        adapter=new GridViewAdapter17(list,this);
        gridview17.setAdapter(adapter);
    }

    private void getData() {
        list.clear();
        Random random=new Random();
        int random1 = random.nextInt(80) * 100;
        if (random1<1000){
            //弱(i)
            list.add(new GridViewBean17("弱("+random1+")", "辐射较弱，涂擦SPF12~15、PA+护肤品",
                    0xFFDAEDFF, R.mipmap.ziwaixian,"紫外线指数"));
        }else if (random1>=1000&&random1<=3000){
            //中等(i)
            list.add(new GridViewBean17("中等("+random1+")","涂擦SPF大于15、PA+防晒护肤品",
                    0xFFDAEDFF,R.mipmap.ziwaixian,"紫外线指数"));
        }else {
            //强(i)
            list.add(new GridViewBean17("强("+random1+")","尽量减少外出，需要涂抹高倍数防晒霜",
                    0xFFFF0000,R.mipmap.ziwaixian,"紫外线指数"));
        }
        int random2 = random.nextInt(16);
        if (random2<8){
            //较易发(i)
            list.add(new GridViewBean17("较易发("+random2+")","温度低，风较大，较易发生感冒，注意防护",
                    0xFFFF0000,R.mipmap.ganmao,"感冒指数"));
        }else {
            //少发(i)
            list.add(new GridViewBean17("少发("+random2+")","无明显降温，感冒机率较低",
                    0xFFDAEDFF,R.mipmap.ganmao,"感冒指数"));
        }
        int random3 = random.nextInt(34);
        if (random3<12){
            //冷(i)
            list.add(new GridViewBean17("冷("+random3+")","建议穿长袖衬衫、单裤等服装",
                    0xFFDAEDFF,R.mipmap.chuanyi,"穿衣指数"));
        }else if (random3>=12&&random3<=21){
            //舒适(i)
            list.add(new GridViewBean17("舒适("+random3+")","建议穿短袖衬衫、单裤等服装",
                    0xFFDAEDFF,R.mipmap.chuanyi,"穿衣指数"));
        }else {
            //热(i)
            list.add(new GridViewBean17("热("+random3+")","适合穿T恤、短薄外套等夏季服装",
                    0xFFDAEDFF,R.mipmap.chuanyi,"穿衣指数"));
        }

        int random4 = random.nextInt(30)*100;
        if (random4<3000){
            //适宜(i)
            list.add(new GridViewBean17("适宜("+random4+")","气候适宜，推荐您进行户外运动",
                    0xFFDAEDFF,R.mipmap.yundong,"运动指数"));
        }else if (random4>=3000&&random4<=6000){
            //中(i)
            list.add(new GridViewBean17("中("+random4+")","易感人群应适当减少室外活动",
                    0xFFDAEDFF,R.mipmap.yundong,"运动指数"));
        }else {
            //较不宜(i)
            list.add(new GridViewBean17("较不宜("+random4+")","空气氧气含量低，请在室内进行休闲运动",
                    0xFFFF0000,R.mipmap.yundong,"运动指数"));
        }

        int random5 = random.nextInt(130);
        if (random5<30){
            //优(i)
            list.add(new GridViewBean17("优("+random5+")","空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气",
                    0xFFDAEDFF,R.mipmap.wurandu,"空气污染指数"));
        }else if (random5>=30&&random5<=100){
            //良(i)
            list.add(new GridViewBean17("良("+random5+")","易感人群应适当减少室外活动",
                    0xFFDAEDFF,R.mipmap.wurandu,"空气污染指数"));
        }else {
            //污染(i)
            list.add(new GridViewBean17("污染("+random5+")","空气质量差，不适合户外活动",
                    0xFFFF0000,R.mipmap.wurandu,"空气污染指数"));
        }
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        gridview17=findViewById(R.id.gridview17);
        list=new ArrayList<>();
        timer=new Timer();
    }
    class  TimerTaskTest3 extends TimerTask {

        @Override
        public void run() {
            getData();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer!=null){
            timer.purge();
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer!=null){
            timer.schedule(new TimerTaskTest3(),3000,3000);
        }
    }
}
