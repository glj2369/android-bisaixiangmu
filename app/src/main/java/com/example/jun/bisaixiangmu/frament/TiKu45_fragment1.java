package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.activity.TiKu45Activity;
import com.example.jun.bisaixiangmu.bean.Bean45;
import com.example.jun.bisaixiangmu.dialog.Dialog45;
import com.example.jun.bisaixiangmu.http.HttpUrl;
import com.example.jun.bisaixiangmu.utils.Grid_item45Adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;
import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_ADDRESS;
import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_HTTP;

public class TiKu45_fragment1 extends Fragment implements View.OnClickListener {
    private Context context;
    private GridView tiku_45_gridview;
    private int[] yues;
    private List<Bean45> bean45Listb;
    private Grid_item45Adapter adapter;
    private int yuzhiJine = 100;
    private Timer timer;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sp = context.getSharedPreferences("yuzhi", MODE_PRIVATE);
        String zhang_hu_gao_jing = sp.getString("zhang_hu_gao_jing", "50");
        yuzhiJine = Integer.parseInt(zhang_hu_gao_jing);
        for (int i = 1; i <= 3; i++) {
            //初始化值
            bean45Listb.add(new Bean45(0xFF00FF00, i + "号小车", "警告", 100));
        }
        adapter.notifyDataSetChanged();
        getDate();
    }

    private void getDate() {
        String ip = context.getSharedPreferences("ipset", 0).getString("ip", "192.168.1.106");
        final String address = TEST_HTTP + ip + TEST_ADDRESS;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    yues = new int[3];
                    for (int i = 0; i < 3; i++) {
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("CarId", i + 1);
                        String result2 = HttpUrl.posthttp(address + "GetCarAccountBalance.do", jsonObject2.toString());
                        result2 = new JSONObject(result2).getString("serverinfo");
                        int balance = new JSONObject(result2).getInt("Balance");
                        yues[i] = balance;
                        Log.e("RunnableRunnable", i + ":" + yues[i]);
                        //这里开始
                    }
                    Thread.sleep(100);
                    bean45Listb.clear();
                    for (int i = 0; i < yues.length; i++) {
                        if (yues[i] < yuzhiJine) {
                            bean45Listb.add(new Bean45(0xFFFF0000, (i + 1) + "号小车", "警告", yues[i]));
                        } else {
                            bean45Listb.add(new Bean45(0xFF00FF00, (i + 1) + "号小车", "警告", yues[i]));
                        }
                    }
                    updateView();
                } catch (JSONException e) {
                    e.printStackTrace();
                    updateErrorView();
                } catch (IOException e) {
                    e.printStackTrace();
                    updateErrorView();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    updateErrorView();
                }

            }
        }).start();
    }

    private void updateView() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "获取数据成功", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void updateErrorView() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bean45Listb.clear();
                for (int i = 1; i <= 3; i++) {
                    //初始化值
                    bean45Listb.add(new Bean45(0xFF00FF00, i + "号小车", "警告", 100));
                }
                Toast.makeText(context, "获取数据失败，请检查网络", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.tiku45_fragment1, null);
        tiku_45_gridview = view.findViewById(R.id.tiku_45_gridview);
        bean45Listb = new ArrayList<>();
        adapter = new Grid_item45Adapter(context, bean45Listb);
        tiku_45_gridview.setAdapter(adapter);
        tiku_45_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Dialog45 dialog45 = new Dialog45(context, position + 1, getActivity());
                dialog45.show();
                getDate();
            }
        });
        timer = new Timer();
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (timer != null) {
            timer = new Timer();
            timer.schedule(new TimerTask5(), 5000, 5000);
        }
    }

    class TimerTask5 extends TimerTask {

        @Override
        public void run() {
            getDate();
        }
    }
}
