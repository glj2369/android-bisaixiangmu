package com.example.jun.bisaixiangmu.activity;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jun.bisaixiangmu.R;

public class ImageTouchTestActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_touch_test);

    }



    /**
     * 返回两个点之间的距离
     */
    private float spacing(MotionEvent event) {
        if (event.getPointerCount() >= 2) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        } else return 0;
    }

    /**
     * onCreate中
     *
     *
     imageView =findViewById(R.id.image);  //找到图片id
     LinearLayout layout=findViewById(R.id.test); //找到可以支持图片缩放的大布局
     imageView.setImageResource(R.mipmap.ic_launcher_round); //给image设置图片
     imageView.setScaleType(ImageView.ScaleType.MATRIX); //设置图片缩放属性为MATRIX
     imageView.setOnTouchListener(this);//设置触摸监听
     layout.setOnTouchListener(this);
     */
}
