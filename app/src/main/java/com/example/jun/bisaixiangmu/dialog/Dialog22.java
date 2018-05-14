package com.example.jun.bisaixiangmu.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean22;
import com.example.jun.bisaixiangmu.bean.Bean9;
import com.example.jun.bisaixiangmu.db.ChongzhiHistory;
import com.example.jun.bisaixiangmu.http.HttpUrl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Dialog22 extends Dialog implements View.OnClickListener {
    private Button buttonChongzhi;
    private Button buttonCancel;
    private EditText editTextJinE;
    private TextView chePaiId;
    private TextView dialog_content;
    private int id=1;
    private String string_content="车辆编号：";
    private List<Bean22> bean22List;
    private Context context;
    private boolean flag = true;
    private ProgressDialog progressDialog;
    private ChongzhiHistory chongzhiHistory;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    progressDialog.cancel();
                    dismiss();
                    Toast.makeText(context, "充值结束", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public Dialog22(@NonNull Context context, List<Bean22> bean22List, String string_content) {
        super(context);
        this.context = context;
        this.bean22List = bean22List;
        this.string_content = string_content;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_dialog);
        chePaiId = findViewById(R.id.dialog_chepai);
        StringBuilder stringBuilder = new StringBuilder();
        for (Bean22 bean22 : bean22List) {
            if (bean22.isCb()) {
                int id = bean22.getId();
                stringBuilder.append(id + "  ");
            }
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("数据加载中...");
        progressDialog.setCancelable(false);

        editTextJinE = findViewById(R.id.et_jine);
        buttonChongzhi = findViewById(R.id.dialog_chonghzi);
        buttonCancel = findViewById(R.id.dialog_cancel);
        dialog_content = findViewById(R.id.dialog_content);
        buttonCancel.setOnClickListener(this);
        buttonChongzhi.setOnClickListener(this);
        dialog_content.setText(""+string_content);
        chePaiId.setText("" + stringBuilder.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_cancel:
                dismiss();
                break;
            case R.id.dialog_chonghzi:
                chongzhiHistory=new ChongzhiHistory(context);

                final String string = editTextJinE.getText().toString().trim();
                if ("".equals(string) || TextUtils.isEmpty(string)) {
                    Toast.makeText(context, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    int i = Integer.parseInt(string);
                    if (i >= 1 && i <= 999) {
                        //进行网络请求
                        //进行充值
                        if (flag) {

                            progressDialog.show();
                            final SQLiteDatabase db = chongzhiHistory.getWritableDatabase();
                            //这里开启一个子线程，，休眠三秒
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        for (Bean22 bean22:bean22List){
                                            if (bean22.isCb()){
                                                saveDatabase(bean22, string, db);
                                            }
                                        }
                                        if (db!=null){
                                            db.close();
                                        }
                                        Thread.sleep(3000);
                                        Message message=new Message();
                                        message.what=1;
                                        handler.sendMessage(message);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                            flag = false;
                        }

                    } else {
                        Toast.makeText(context, "输入的值只能在1-999之间", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void saveDatabase(Bean22 bean22, String string, SQLiteDatabase db) {
        ContentValues valuse=new ContentValues();
        valuse.put("carId",bean22.getId()+"");
        valuse.put("chongNum",string+"");
        valuse.put("personName","admin");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        valuse.put("historyTime",simpleDateFormat.format(date));
        long history = db.insert("history", null, valuse);
        Log.i("DiaLog22","当前数据库添加到"+history+"条");
    }


}
