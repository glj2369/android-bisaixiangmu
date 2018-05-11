package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.frament.CO2Fragment;
import com.example.jun.bisaixiangmu.frament.GuangZhaoFragment;
import com.example.jun.bisaixiangmu.frament.Pm25Fragment;
import com.example.jun.bisaixiangmu.frament.ShiduFragment;
import com.example.jun.bisaixiangmu.frament.WenDuFragment;
import com.example.jun.bisaixiangmu.frament.ZhuangTaiFragment;
import com.example.jun.bisaixiangmu.utils.MyFragmentPagerAdapter;
import com.example.jun.bisaixiangmu.utils.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class XianShi6Activity extends BaseActivity {

    private ViewPager mViewPager;
    private TextView mTitle;
    private List<Fragment> mViews;
    private List<ImageView> mDots;
    private LayoutInflater mInflater;
    private int oldPosition;//记录当前点的位置。
    private String[] titles;
    private String title;
    @Override
    protected String getLayoutTitle() {
        return "实时显示";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_xian_shi6;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        titles=getResources().getStringArray(R.array.titles);
        mTitle = findViewById(R.id.title_xianshi);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        int position = intent.getIntExtra("position", 0);
        if (title != null) {
            mTitle.setText(title + "");
            Log.e("initView",""+title);
        } else {
            mTitle.setText("温度");
        }
        mViewPager = (ViewPager) findViewById(R.id.viewpager_6);

        mInflater = getLayoutInflater();
//
        mViews = new ArrayList<>();
        // View view1 = mInflater.inflate(R.layout.wendu_viewpager, null);
        // View view2 = mInflater.inflate(R.layout.shidu_viewpager, null);
        // View view3 = mInflater.inflate(R.layout.co2_viewpager, null);

        mViews.add(new WenDuFragment());
        mViews.add(new ShiduFragment());
        mViews.add(new GuangZhaoFragment());
        mViews.add(new CO2Fragment());
        mViews.add(new Pm25Fragment());
        mViews.add(new ZhuangTaiFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mViews);
        mViewPager.setAdapter(adapter);


        //暂时先弄前三个布局
        mDots = new ArrayList<>();
        ImageView dotFirst = (ImageView) findViewById(R.id.image_1);
        ImageView dotFSecond = (ImageView) findViewById(R.id.image_2);
        ImageView dotThrid = (ImageView) findViewById(R.id.image_3);
        ImageView dotFours = (ImageView) findViewById(R.id.image_4);
        ImageView dotFives= (ImageView) findViewById(R.id.image_5);
        ImageView dotSixth= (ImageView) findViewById(R.id.image_6);
        mDots.add(dotFirst);
        mDots.add(dotFSecond);
        mDots.add(dotThrid);
        mDots.add(dotFours);
        mDots.add(dotFives);
        mDots.add(dotSixth);
        oldPosition = position;
        mViewPager.setCurrentItem(position);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mDots.get(oldPosition).setBackgroundColor(Color.GREEN);
                mDots.get(position).setBackgroundColor(Color.RED);
                oldPosition = position;
                title=titles[position];
                mTitle.setText(title);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
