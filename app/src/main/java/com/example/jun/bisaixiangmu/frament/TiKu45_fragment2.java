package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.http.HttpUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_ADDRESS;
import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_HTTP;

public class TiKu45_fragment2 extends Fragment implements View.OnClickListener {
    private Context context;
    private Button tiku_45_frament2_start_1;
    private Button tiku_45_frament2_start_2;
    private Button tiku_45_frament2_start_3;
    private Button tiku_45_frament2_stop_1;
    private Button tiku_45_frament2_stop_2;
    private Button tiku_45_frament2_stop_3;
    private String address;
    private int[] colorBt={0xFF00FF00,0xFFFFFFFF};
    private String[] carActions={"Start","Stop"};

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.tiku45_fragment2, null);
        tiku_45_frament2_start_1 = view.findViewById(R.id.tiku_45_frament2_start_1);
        tiku_45_frament2_start_2 = view.findViewById(R.id.tiku_45_frament2_start_2);
        tiku_45_frament2_start_3 = view.findViewById(R.id.tiku_45_frament2_start_3);
        tiku_45_frament2_stop_1 = view.findViewById(R.id.tiku_45_frament2_stop_1);
        tiku_45_frament2_stop_2 = view.findViewById(R.id.tiku_45_frament2_stop_2);
        tiku_45_frament2_stop_3 = view.findViewById(R.id.tiku_45_frament2_stop_3);
        setOnClisenter();
        String ip = context.getSharedPreferences("ipset", 0).getString("ip", "192.168.1.106");
        address = TEST_HTTP + ip + TEST_ADDRESS;
        return view;
    }

    private void setOnClisenter() {
        tiku_45_frament2_start_1.setOnClickListener(this);
        tiku_45_frament2_start_2.setOnClickListener(this);
        tiku_45_frament2_start_3.setOnClickListener(this);
        tiku_45_frament2_stop_1.setOnClickListener(this);
        tiku_45_frament2_stop_2.setOnClickListener(this);
        tiku_45_frament2_stop_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_45_frament2_start_1:
                getNetword(1,carActions[0]);
                tiku_45_frament2_start_1.setBackgroundColor(colorBt[0]);
                tiku_45_frament2_stop_1.setBackgroundColor(colorBt[1]);

                break;
            case R.id.tiku_45_frament2_start_2:
                getNetword(2,carActions[0]);
                tiku_45_frament2_start_2.setBackgroundColor(colorBt[0]);
                tiku_45_frament2_stop_2.setBackgroundColor(colorBt[1]);
                break;
            case R.id.tiku_45_frament2_start_3:
                getNetword(3,carActions[0]);
                tiku_45_frament2_start_3.setBackgroundColor(colorBt[0]);
                tiku_45_frament2_stop_3.setBackgroundColor(colorBt[1]);
                break;
            case R.id.tiku_45_frament2_stop_1:
                getNetword(1,carActions[1]);
                tiku_45_frament2_stop_1.setBackgroundColor(colorBt[0]);
                tiku_45_frament2_start_1.setBackgroundColor(colorBt[1]);

                break;
            case R.id.tiku_45_frament2_stop_2:
                getNetword(2,carActions[1]);
                tiku_45_frament2_stop_2.setBackgroundColor(colorBt[0]);
                tiku_45_frament2_start_2.setBackgroundColor(colorBt[1]);
                break;
            case R.id.tiku_45_frament2_stop_3:
                getNetword(3,carActions[1]);
                tiku_45_frament2_stop_3.setBackgroundColor(colorBt[0]);
                tiku_45_frament2_start_3.setBackgroundColor(colorBt[1]);
                break;
            default:
                break;
        }
    }

    private void getNetword(final int carId, final String carAction) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("CarId", carId);
                    jsonObject.put("CarAction", carAction);
                    String result = HttpUrl.posthttp(address + "SetCarMove.do", jsonObject.toString());
                    String serverinfo = new JSONObject(result).getString("serverinfo");
                    String result1 = new JSONObject(serverinfo).getString("result");
                    if ("ok".equals(result1)){
                        updateView(carAction);
                    }else {
                        updateError(carAction);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    updateError(carAction);
                } catch (IOException e) {
                    e.printStackTrace();
                    updateError(carAction);
                }
            }
        }).start();
    }

    private void updateView(final String carAction) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, carAction+"设置成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateError(final String carAction) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, carAction+"设置失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
