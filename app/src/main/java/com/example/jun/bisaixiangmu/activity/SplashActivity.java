package com.example.jun.bisaixiangmu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jun.bisaixiangmu.MainActivity;
import com.example.jun.bisaixiangmu.R;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    private ArrayList<ImageView> list;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initIsFirst();

        initData();
        btn = (Button) findViewById(R.id.btn);

        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        MyAdapter adapter = new MyAdapter();
        viewpager.setAdapter(adapter);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (position == list.size() - 1) {
                    btn.setVisibility(View.VISIBLE);
                    btn.setOnClickListener(new Button.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(),
                                    MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    btn.setVisibility(Button.GONE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        list = new ArrayList<ImageView>();

        ImageView iv1 = new ImageView(getBaseContext());
        iv1.setBackgroundResource(R.mipmap.ic_launcher);
        list.add(iv1);

        ImageView iv2 = new ImageView(getBaseContext());
        iv2.setBackgroundResource(R.mipmap.ic_launcher);
        list.add(iv2);

        ImageView iv3 = new ImageView(getBaseContext());
        iv3.setBackgroundResource(R.mipmap.ic_launcher);
        list.add(iv3);
    }

    private void initIsFirst() {
        SharedPreferences.Editor edit = getSharedPreferences("config", MODE_PRIVATE).edit();
        edit.putBoolean("is_First", false);
        edit.commit();
    }
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
