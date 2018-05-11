package com.example.jun.bisaixiangmu.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.MainActivity;
import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.db.ChongzhiHistory;
import com.example.jun.bisaixiangmu.http.HttpUrl;
import com.example.jun.bisaixiangmu.http.NetUtil1;
import com.example.jun.bisaixiangmu.requst.BaseRequest;
import com.example.jun.bisaixiangmu.requst.CarAccount_1;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChongZhi1Activity extends BaseActivity implements View.OnClickListener {
    public static final String getJinE_carId  = "http://192.168.1.106:8080/transportservice/type/jason/action/GetCarAccountBalance.do";
    public static final String setJinE_carId = "http://192.168.1.106:8080/transportservice/type/jason/action/SetCarAccountRecharge.do";
    private TextView mTvYuE;
    private Spinner mSpCarId;
    private EditText mEtJinE;
    private Button mBtChaXun;
    private Button mBtChongZhi;
    private String[] carIds;
    private int carId = 1;
    private ChongzhiHistory history;
    private int banlance = 100;
    private HttpUrl httpUrl=new HttpUrl();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                mTvYuE.setText(banlance + "");
            }
        }
    };

    @Override
    protected String getLayoutTitle() {
        return "我的账户";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_chong_zhi1;
    }

    @Override
    protected void initData() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        history = new ChongzhiHistory(ChongZhi1Activity.this);
        carIds = getResources().getStringArray(R.array.carid);
        mTvYuE = findViewById(R.id.tv_yu_e);
        mSpCarId = findViewById(R.id.sp_chehao);
        mEtJinE = findViewById(R.id.et_jine);
        mBtChaXun = findViewById(R.id.bt_chaxun);
        mBtChongZhi = findViewById(R.id.bt_chongzhi);
        setListener();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, carIds);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        mSpCarId.setAdapter(adapter);
        mSpCarId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carId = Integer.parseInt(carIds[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getYuE2();
        //getYuE2okhttp();
    }

    private void setListener() {
        mBtChaXun.setOnClickListener(this);
        mBtChongZhi.setOnClickListener(this);
    }
//{"CarId":1,"Money":200}
    private void setJinE(final int carJinE){
        Log.e("setJinE_carId", "" + carId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("CarId",carId);
                    jsonObject.put("Money",carJinE);
                    String result = postHttpUrl(setJinE_carId, jsonObject.toString());
                    result=new JSONObject(result).getString("serverinfo");
                    result=new JSONObject(result).getString("result");

                    final String finalResult = result;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if ("ok".equals(finalResult)){
                                //getYuE();
                                Toast.makeText(ChongZhi1Activity.this, "充值成功", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void getYuE() {
        Log.e("getYuE-carId", "" + carId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("CarId", carId);

                    String result = postHttpUrl(getJinE_carId,jsonObject.toString());
                    result = new JSONObject(result).optString("serverinfo");
                    final int a=new JSONObject(result).optInt("Balance");

                    final String finalResult = result;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvYuE.setText(a+"元");
                            Toast.makeText(ChongZhi1Activity.this, "我接收到的数据" + finalResult, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    private void getYuE2(){

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CarId", carId);
            HttpUrl.postHttpUrl(getJinE_carId, jsonObject.toString(), new HttpUrl.ResultHttpLisenter() {
                @Override
                public void response(final String result) {
                    try {
                       String result1 = new JSONObject(result).optString("serverinfo");
                        final int a=new JSONObject(result1).optInt("Balance");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvYuE.setText(a+"元");
                                Toast.makeText(ChongZhi1Activity.this, "我接收到的数据" + result, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void getYuE2okhttp(){

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("CarId", carId);
            HttpUrl.postJsonUrl(getJinE_carId, jsonObject.toString(), new HttpUrl.ResultHttpLisenter() {
                @Override
                public void response(final String result) {
                    try {
                        String result1 = new JSONObject(result).optString("serverinfo");
                        final int a=new JSONObject(result1).optInt("Balance");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvYuE.setText(a+"元");
                                Toast.makeText(ChongZhi1Activity.this, "我接收到的数据" + result, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void setYuE2(final int carJinE){

        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("CarId",carId);
            jsonObject.put("Money",carJinE);
            HttpUrl.postHttpUrl(setJinE_carId, jsonObject.toString(), new HttpUrl.ResultHttpLisenter() {
                @Override
                public void response(final String result) {
                    try {
                        String result1=new JSONObject(result).getString("serverinfo");
                        final String result2=new JSONObject(result1).getString("result");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if ("ok".equals(result2)){
                                    getYuE2();
                                    Toast.makeText(ChongZhi1Activity.this, "充值成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @NonNull
    private String postHttpUrl(String urlString,String jsonStr) throws IOException, JSONException {
        URL mUrl = new URL(urlString);
        HttpURLConnection mConnection = (HttpURLConnection) mUrl.openConnection();
        mConnection.addRequestProperty("accept", "*/*");
        mConnection.setRequestProperty("connection", "Keep-Alive");
        mConnection.addRequestProperty("Content-Type", "text/html;charset=utf-8");
        mConnection.setConnectTimeout(3000);
        mConnection.setReadTimeout(3000);
        mConnection.setDoOutput(true);
        mConnection.setDoInput(true);
        mConnection.setRequestMethod("POST");

        OutputStream os = mConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(os);
        writer.write(jsonStr);
        writer.flush();
        writer.close();
        os.close();

        InputStream in = mConnection.getInputStream();
        BufferedReader mReader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result =new StringBuilder();
        String line;
        while ((line = mReader.readLine()) != null) {
            result.append(line);
        }
        if (in != null) {
            in.close();
        }
        if (mReader != null) {
            mReader.close();
        }
        return result.toString();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_chaxun:
                getYuE2();
               // getYuE2okhttp();

                break;
            case R.id.bt_chongzhi:
                String jine = mEtJinE.getText().toString().trim();
                Log.e("bt_chongzhi", "点击了");
                //暂未实现user  对应的是当时登录的name
                SQLiteDatabase db = history.getWritableDatabase();
                Log.e("bt_chongzhi", "SQLiteDatabase");
                ContentValues values = new ContentValues();
                values.put("carId", carId);
                values.put("chongNum", jine);
                values.put("personName", "admin");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                values.put("historyTime", simpleDateFormat.format(date));
                long history = db.insert("history", null, values);
                db.close();
                setYuE2(Integer.parseInt(jine));

                Toast.makeText(this, "充值成功，记录保存到数据库" + history + "条", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
