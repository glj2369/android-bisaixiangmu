
package com.example.jun.bisaixiangmu.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.http.HttpUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class IpSetActivity extends BaseActivity implements View.OnClickListener {
    public static final String TEST_HTTP = "http://";
    public static final String TEST_ADDRESS = ":8080/transportservice/type/jason/action/";
    private Button mSureBtn;
    private EditText mIP1ET;
    private EditText mIP2ET;
    private EditText mIP3ET;
    private EditText mIP4ET;
    private SharedPreferences mSetIPPreferences;
    private String[] ip;

    private ProgressDialog progressDialog;

    private Button mTestBt;
    private TextView mTestTv;

    @Override
    protected String getLayoutTitle() {
        return "IP设置";
    }

    @Override
    protected int getLayouId() {
        return R.layout.ip;
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initData() {
        ip = mSetIPPreferences.getString("ip", "192.168.1.131").split("\\.");
        if (ip.length >= 4) {
            mIP1ET.setText(ip[0] + "");
            mIP2ET.setText(ip[1] + "");
            mIP3ET.setText(ip[2] + "");
            mIP4ET.setText(ip[3] + "");
        }
    }

    @Override
    protected void initView() {
        mTestBt = findViewById(R.id.ip_test);
        mTestTv = findViewById(R.id.test_textview);

        mSetIPPreferences = getSharedPreferences("ipset", 0);
        mIP1ET = (EditText) findViewById(R.id.ip_et_ip0);
        mIP2ET = (EditText) findViewById(R.id.ip_et_ip1);
        mIP3ET = (EditText) findViewById(R.id.ip_et_ip2);
        mIP4ET = (EditText) findViewById(R.id.ip_et_ip3);
        mSureBtn = (Button) findViewById(R.id.ip_btn_sure);
        mSureBtn.setOnClickListener(this);
        mTestBt.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("获取数据中...");
        progressDialog.setCancelable(false);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ip_btn_sure:
                handlString(mIP1ET);
                handlString(mIP2ET);
                handlString(mIP3ET);
                handlString(mIP4ET);
                //改
                if (selectId(mIP1ET.getText().toString())) {
                    Toast.makeText(this, "ip请输入0-255", Toast.LENGTH_SHORT).show();
                    return;
                }
                //改
                if (selectId(mIP2ET.getText().toString())) {
                    Toast.makeText(this, "ip请输入0-255", Toast.LENGTH_SHORT).show();
                    return;
                }
                //改
                if (selectId(mIP3ET.getText().toString())) {

                    return;
                }
                //改
                if (selectId(mIP4ET.getText().toString())) {
                    Toast.makeText(this, "ip请输入0-255", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (v.getId() == R.id.ip_btn_sure) {
                    String string = handlString(mIP1ET) + "." + handlString(mIP2ET) + "."
                            + handlString(mIP3ET) + "." + handlString(mIP4ET);

                    mSetIPPreferences.edit().putString("ip", string).apply();
                    //finish();
                    Toast.makeText(this, mSetIPPreferences.getString("ip", "192.168.1.131"), Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.ip_test:

                String ip = mSetIPPreferences.getString("ip", "192.168.1.106");
                final String address = TEST_HTTP + ip + TEST_ADDRESS;
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            JSONObject jsonObject1 = new JSONObject();
                            jsonObject1.put("CarId", 1);
                            jsonObject1.put("CarAction", "Stop");
                            String result1 = HttpUrl.posthttp(address + "SetCarMove.do", jsonObject1.toString());
                            stringBuilder.append("我传了SetCarMove.do+" + jsonObject1.toString() + "\n我收到了" + result1 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject2 = new JSONObject();
                            jsonObject2.put("CarId", 1);
                            String result2 = HttpUrl.posthttp(address + "GetCarAccountBalance.do", jsonObject2.toString());
                            stringBuilder.append("我传了GetCarAccountBalance.do+" + jsonObject2.toString() + "\n我收到了" + result2 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject3 = new JSONObject();
                            jsonObject3.put("CarId", 1);
                            jsonObject3.put("Money", 2);
                            String result3 = HttpUrl.posthttp(address + "SetCarAccountRecharge.do", jsonObject3.toString());
                            stringBuilder.append("我传了SetCarAccountRecharge.do+" + jsonObject3.toString() + "\n我收到了" + result3 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject4 = new JSONObject();
                            jsonObject4.put("CarId", 1);
                            String result4 = HttpUrl.posthttp(address + "GetCarSpeed.do", jsonObject4.toString());
                            stringBuilder.append("我传了GetCarSpeed.do+" + jsonObject4.toString() + "\n我收到了" + result4 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject5 = new JSONObject();
                            jsonObject5.put("TrafficLightId", "1-1");
                            jsonObject5.put("Status", "Green");
                            jsonObject5.put("Time", 55);
                            String result5 = HttpUrl.posthttp(address + "SetTrafficLightNowStatus.do", jsonObject5.toString());
                            stringBuilder.append("我传了SetTrafficLightNowStatus.do+" + jsonObject5.toString() + "\n我收到了" + result5 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject6 = new JSONObject();
                            jsonObject6.put("TrafficLightId", 2);
                            String result6 = HttpUrl.posthttp(address + "GetTrafficLightConfigAction.do", jsonObject6.toString());
                            stringBuilder.append("我传了GetTrafficLightConfigAction.do+" + jsonObject6.toString() + "\n我收到了" + result6 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject7 = new JSONObject();
                            String result7 = HttpUrl.posthttp(address + "GetAllSense.do", jsonObject7.toString());
                            stringBuilder.append("我传了GetAllSense.do+" + jsonObject7.toString() + "\n我收到了" + result7 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject8 = new JSONObject();
                            jsonObject8.put("RoadId", 2);
                            String result8 = HttpUrl.posthttp(address + "GetRoadStatus.do", jsonObject8.toString());
                            stringBuilder.append("我传了GetRoadStatus.do+" + jsonObject8.toString() + "\n我收到了" + result8 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject9 = new JSONObject();
                            jsonObject9.put("RateType", "Count");
                            jsonObject9.put("Money", 10);
                            String result9 = HttpUrl.posthttp(address + "SetParkRate.do", jsonObject9.toString());
                            stringBuilder.append("我传了SetParkRate.do+" + jsonObject9.toString() + "\n我收到了" + result9 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject10 = new JSONObject();
                            jsonObject10.put("RateType", "Count");
                            String result10 = HttpUrl.posthttp(address + "GetParkRate.do", jsonObject10.toString());
                            stringBuilder.append("我传了GetParkRate-----.do+" + jsonObject10.toString() + "\n我收到了" + result10 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject11 = new JSONObject();
                            String result11 = HttpUrl.posthttp(address + "GetParkFree.do", jsonObject11.toString());
                            stringBuilder.append("GetParkFree.do+" + jsonObject11.toString() + "\n我收到了" + result11 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject12 = new JSONObject();
                            String result12 = HttpUrl.posthttp(address + "GetLightSenseValve.do", jsonObject12.toString());
                            stringBuilder.append("GetLightSenseValve.do+" + jsonObject12.toString() + "\n我收到了" + result12 + "\n");

                            Thread.sleep(300);
                            JSONObject jsonObject13 = new JSONObject();
                            jsonObject13.put("BusStationId", 1);
                            String result13 = HttpUrl.posthttp(address + "GetBusStationInfo.do", jsonObject13.toString());
                            stringBuilder.append("GetBusStationInfo.do+" + jsonObject13.toString() + "\n我收到了" + result13 + "\n");


                            updateView(stringBuilder.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            errorView();
                        } catch (IOException e) {
                            e.printStackTrace();
                            errorView();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            errorView();
                        }

                    }
                }).start();
                break;
        }

    }

    private void errorView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(IpSetActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
            }
        });

    }

    private void updateView(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(IpSetActivity.this, ""+ string, Toast.LENGTH_SHORT).show();
                mTestTv.setText(string);
                progressDialog.cancel();
            }
        });
    }

    //改
    public Boolean selectId(String str) {
        int i = Integer.parseInt(str);
        if (i > 255) {
            return true;
        } else {
            return false;
        }
    }

    private String handlString(EditText editText) {
        String string = editText.getText().toString().trim();
        if (string.equals("")) {
            Toast.makeText(this, "不能有空值", Toast.LENGTH_SHORT).show();
            return "";
        }
        return string;
    }
}
