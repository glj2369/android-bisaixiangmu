package com.example.jun.bisaixiangmu.activity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;

public class AnimTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_test);
        ImageView back = (ImageView) findViewById(R.id.back);
        TextView textView = (TextView) findViewById(R.id.title);
        textView.setText("测试动画");
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView imageView=findViewById(R.id.test_image_anim);
        AnimationDrawable drawable = (AnimationDrawable) imageView.getBackground();
        drawable.start();
    }
}
