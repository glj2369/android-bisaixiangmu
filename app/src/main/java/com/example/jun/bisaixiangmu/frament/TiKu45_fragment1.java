package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.dialog.Dialog45;
import com.example.jun.bisaixiangmu.http.HttpUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_ADDRESS;
import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_HTTP;

public class TiKu45_fragment1 extends Fragment implements View.OnClickListener {
    private Context context;
    private LinearLayout tiku_45_fragment_id1;
    private LinearLayout tiku_45_fragment_id2;
    private LinearLayout tiku_45_fragment_id3;
    private LinearLayout tiku_45_fragment_id4;
    private int[] yues;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListener();
        String ip = context.getSharedPreferences("ipset", 0).getString("ip", "192.168.1.106");
        final String address = TEST_HTTP + ip + TEST_ADDRESS;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    yues=new int[3];
                    for (int i = 0; i < 3; i++) {
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("CarId", i + 1);
                        String result2 = HttpUrl.posthttp(address + "GetCarAccountBalance.do", jsonObject2.toString());
                        result2 = new JSONObject(result2).getString("serverinfo");
                        int balance = new JSONObject(result2).getInt("Balance");
                        yues[i] = balance;
                        Log.e("RunnableRunnable",i+":"+ yues[i]);


                        //这里开始
                    }
                    updateView();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void updateView() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, ""+yues, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListener() {
        tiku_45_fragment_id1.setOnClickListener(this);
        tiku_45_fragment_id2.setOnClickListener(this);
        tiku_45_fragment_id3.setOnClickListener(this);
        tiku_45_fragment_id4.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.tiku45_fragment1, null);
        tiku_45_fragment_id1 = view.findViewById(R.id.tiku_45_fragment_id1);
        tiku_45_fragment_id2 = view.findViewById(R.id.tiku_45_fragment_id2);
        tiku_45_fragment_id3 = view.findViewById(R.id.tiku_45_fragment_id3);
        tiku_45_fragment_id4 = view.findViewById(R.id.tiku_45_fragment_id4);
        return view;
    }

    @Override
    public void onClick(View v) {
        Dialog45 dialog45 = new Dialog45(context);
        dialog45.show();
    }
}
