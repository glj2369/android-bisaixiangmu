package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jun.bisaixiangmu.R;

public class WeiZhangImage extends Fragment implements View.OnClickListener {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView big_imageview;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, null);
        imageView1 = view.findViewById(R.id.iv_weizhang_1);
        imageView2 = view.findViewById(R.id.iv_weizhang_2);
        imageView3 = view.findViewById(R.id.iv_weizhang_3);
        imageView4 = view.findViewById(R.id.iv_weizhang_4);
        big_imageview = view.findViewById(R.id.big_imageview);
        big_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView1.setClickable(true);
                imageView2.setClickable(true);
                imageView3.setClickable(true);
                imageView4.setClickable(true);
                big_imageview.setVisibility(View.GONE);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_weizhang_1:
                setImage(R.mipmap.ic_launcher_round);
                break;
            case R.id.iv_weizhang_2:
                setImage(R.mipmap.ic_launcher_round);
                break;
            case R.id.iv_weizhang_3:
                setImage(R.mipmap.ic_launcher_round);
                break;
            case R.id.iv_weizhang_4:
                setImage(R.mipmap.ic_launcher_round);
                break;
            default:
                break;
        }
    }

    private void setImage(int srcId) {
        big_imageview.setImageResource(srcId);
        big_imageview.setVisibility(View.VISIBLE);
        imageView1.setClickable(false);
        imageView2.setClickable(false);
        imageView3.setClickable(false);
        imageView4.setClickable(false);
    }
}
