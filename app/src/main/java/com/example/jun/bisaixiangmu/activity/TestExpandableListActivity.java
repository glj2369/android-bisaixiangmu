package com.example.jun.bisaixiangmu.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.MainActivity;
import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.dialog.Detail;
import com.example.jun.bisaixiangmu.http.HttpUrl;
import com.example.jun.bisaixiangmu.utils.ExpandableListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class TestExpandableListActivity extends AppCompatActivity implements View.OnClickListener {

    private ExpandableListView listView;
    private ExpandableListAdapter adapter;
    private Map<String, List<String>> listMap= new HashMap<>();
    private String[] strings= new String[]{"一号站台", "二号站台"};
    private List<String> list1= new ArrayList<>();
    private List<String> list2= new ArrayList<>();
    private Timer timer;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                //String str= (String) msg.obj;
                //Toast.makeText(TestExpandableListActivity.this, ""+str, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_expandable_list);
        listView = findViewById(R.id.test_expandablelist1);
        adapter = new ExpandableListAdapter(this, listMap, strings);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(TestExpandableListActivity.this,
                        "我点击"+strings[groupPosition]+"的"+listMap.get(strings[groupPosition]).get(childPosition), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        timer=new Timer();
        getDate();
        Button mBtDetail= findViewById(R.id.bt_detail);
        mBtDetail.setOnClickListener(this);
    }

    private void getDate() {
        String ip = getSharedPreferences("ipset", 0).getString("ip", "192.168.1.106");
        final String address = "http://" + ip + ":8080/transportservice/type/jason/action/";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1111


                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("BusStationId", 1);
                    String result1 = HttpUrl.posthttp(address + "GetBusStationInfo.do",
                            jsonObject1.toString());
                    result1 = new JSONObject(result1).getString("serverinfo");
                    JSONArray jsonArray1 = new JSONArray(result1);
                    list1.clear();
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject = jsonArray1.getJSONObject(i);
                        int distance = jsonObject.getInt("Distance");
                        int busId = jsonObject.getInt("BusId");
                        //这里给数据
                        list1.add(busId+"号公交车，距离"+distance);
                    }

                    JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("BusStationId", 2);
                    String result2 = HttpUrl.posthttp(address + "GetBusStationInfo.do",
                            jsonObject2.toString());
                    result2 = new JSONObject(result2).getString("serverinfo");
                    JSONArray jsonArray2 = new JSONArray(result2);
                    list2.clear();
                    for (int i = 0; i < jsonArray2.length(); i++) {
                        JSONObject json = jsonArray2.getJSONObject(i);
                        int distance = json.getInt("Distance");
                        int busId = json.getInt("BusId");
                        //这里给数据
                        list2.add(busId+"号公交车，距离"+distance);
                    }
                    //这里给数据
                    listMap.clear();
                    listMap.put(strings[0],list1);
                    Log.e("11111",""+list1);
                    listMap.put(strings[1],list2);
                    Log.e("22222",""+list2);

                    showView();
                } catch (JSONException e) {
                    e.printStackTrace();
                    showViewerror();
                } catch (IOException e) {
                    e.printStackTrace();
                    showViewerror();
                }

            }
        }).start();
    }

    private void showViewerror() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listMap.clear();
                list1.clear();
                list2.clear();
                list1.add("暂无公交信息");
                list2.add("暂无公交信息");
                listMap.put(strings[0],list1);
                Log.e("error1",""+list1);
                listMap.put(strings[1],list2);
                Log.e("error2",""+list2);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void showView() {
        Message message=new Message();
        message.what=1;
        //message.obj="我发送了消息";
        handler.sendMessage(message);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                adapter.notifyDataSetChanged();
//            }
//        });
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
            timer=new Timer();
            timer.schedule(new Timer3s(),3000,3000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_detail:
                Detail detail = new Detail(this);
                detail.show();
                break;
            default:
                break;
        }
    }

    class Timer3s extends TimerTask{

        @Override
        public void run() {
            getDate();
        }
    }
}
