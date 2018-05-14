package com.example.jun.bisaixiangmu.dialog;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.db.ChongzhiHistory;
import com.example.jun.bisaixiangmu.http.HttpUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_ADDRESS;
import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_HTTP;


public class Dialog45 extends Dialog implements View.OnClickListener {
    private Button sure;
    private Button cancel;
    private EditText jinE;
    private ImageView close;
    private Context context;
    private int carId;
    private ProgressDialog dialog;
    private String address;
    private Activity activity;
    private ChongzhiHistory chongzhiHistory;

    public Dialog45(@NonNull Context context, int carId, Activity activity) {
        super(context);
        this.context = context;
        this.carId = carId;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_45);
        chongzhiHistory = new ChongzhiHistory(context);
        close = findViewById(R.id.image_close);
        jinE = findViewById(R.id.et_jin_e);
        sure = findViewById(R.id.sure);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        close.setOnClickListener(this);
        sure.setOnClickListener(this);
        dialog = new ProgressDialog(context);
        dialog.setTitle("提示");
        dialog.setMessage("充值中...");
        dialog.setCancelable(false);
        String ip = context.getSharedPreferences("ipset", 0).getString("ip", "192.168.1.106");
        address = TEST_HTTP + ip + TEST_ADDRESS;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_close:
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
            case R.id.sure:
                String trim = jinE.getText().toString().trim();
                if ("".equals(trim) || TextUtils.isEmpty(trim)) {
                    Toast.makeText(context, "输入数额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    final int i = Integer.parseInt(trim);
                    dialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("CarId", carId);
                                jsonObject.put("Money", i);
                                String response = HttpUrl.posthttp(address +
                                        "SetCarAccountRecharge.do", jsonObject.toString());
                                if (response != null) {
                                    updateView(i);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                updateViewError();
                            } catch (IOException e) {
                                e.printStackTrace();
                                updateViewError();
                            }

                        }
                    }).start();
                }
                break;
            default:
        }
    }

    private void updateViewError() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                Toast.makeText(context, "充值失败", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    private void updateView(final int i) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                SQLiteDatabase db = chongzhiHistory.getWritableDatabase();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ContentValues values = new ContentValues();
                values.put("carId", carId);
                values.put("chongNum", i);
                values.put("historyTime", format.format(new Date()));
                long tiku45db = db.insert("tiku45db", null, values);
                Log.i("Dialog45","充值保存记录到:"+tiku45db);
                db.close();
                Toast.makeText(context, "充值成功", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

}
