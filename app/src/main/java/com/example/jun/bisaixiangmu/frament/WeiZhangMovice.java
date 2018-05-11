package com.example.jun.bisaixiangmu.frament;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.jun.bisaixiangmu.R;

public class WeiZhangMovice extends Fragment implements View.OnClickListener {
    private LinearLayout layout1_1;
    private LinearLayout layout1_2;
    private LinearLayout layout1_3;
    private LinearLayout layout1_4;
    private LinearLayout layout2_1;
    private LinearLayout layout0_1;
    private LinearLayout layout0_2;
    private VideoView videoView;
    private MediaController controller;
    private Context context;
    private Button mBtClose;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layout1_1.setOnClickListener(this);
        layout1_2.setOnClickListener(this);
        layout1_3.setOnClickListener(this);
        layout1_4.setOnClickListener(this);
        layout2_1.setOnClickListener(this);

        layout0_2.setOnClickListener(this);
        mBtClose.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, null);
        layout1_1 = view.findViewById(R.id.layout1_1);
        layout1_2 = view.findViewById(R.id.layout1_2);
        layout1_3 = view.findViewById(R.id.layout1_3);
        layout1_4 = view.findViewById(R.id.layout1_4);
        layout2_1 = view.findViewById(R.id.layout2_1);

        layout0_1 = view.findViewById(R.id.layout0_1);
        layout0_2 = view.findViewById(R.id.layout0_2);

        videoView = view.findViewById(R.id.videro_view);
        controller = new MediaController(context);
        videoView.setMediaController(controller);

        mBtClose=view.findViewById(R.id.bt_close);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_close:
                if (videoView!=null){
                    videoView.stopPlayback();
                    layout0_2.setVisibility(View.GONE);
                    layout0_1.setClickable(true);
                }
                break;
            case R.id.layout1_1:
                play();
                break;
            case R.id.layout1_2:
                play();
                break;
            case R.id.layout1_3:
                play();
                break;
            case R.id.layout1_4:
                play();
                break;
            case R.id.layout2_1:
                play();
                break;
        }
    }

    private void play() {
        layout0_2.setVisibility(View.VISIBLE);
        layout0_1.setClickable(false);
        if (videoView!=null){
            //videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.movice));
            //videoView.start();
        }
    }
}
