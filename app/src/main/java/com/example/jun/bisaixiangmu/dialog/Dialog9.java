package com.example.jun.bisaixiangmu.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean9;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Dialog9 extends Dialog implements View.OnClickListener {
    private Button buttonChongzhi;
    private Button buttonCancel;
    private EditText editTextJinE;
    private TextView chePai;
    private TextView dialog_content;
    private String stringChepai;
    private String string_content;
    private List<Bean9> bean9List;
    private Context context;
    private boolean flag = true;
    private ProgressDialog progressDialog;
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

    public  Dialog9(@NonNull Context context, String stringChepai, List<Bean9> bean9List,String string_content) {
        super(context);
        this.stringChepai = stringChepai;
        this.context = context;
        this.bean9List = bean9List;
        this.string_content = string_content;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_dialog);
        chePai = findViewById(R.id.dialog_chepai);
        StringBuilder stringBuilder = new StringBuilder();
        for (Bean9 bean9 : bean9List) {
            if (bean9.getaBoolean()) {
                String chePai = bean9.getChePai();
                stringBuilder.append(chePai + "  ");
            }
        }


        editTextJinE = findViewById(R.id.et_jine);
        buttonChongzhi = findViewById(R.id.dialog_chonghzi);
        buttonCancel = findViewById(R.id.dialog_cancel);
        dialog_content = findViewById(R.id.dialog_content);
        buttonCancel.setOnClickListener(this);
        buttonChongzhi.setOnClickListener(this);
        dialog_content.setText(""+string_content);
        chePai.setText("" + stringBuilder.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_cancel:
                dismiss();
                break;
            case R.id.dialog_chonghzi:
                String string = editTextJinE.getText().toString().trim();
                if ("".equals(string) || TextUtils.isEmpty(string)) {
                    Toast.makeText(context, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    int i = Integer.parseInt(string);
                    if (i >= 1 && i <= 999) {
                        //进行网络请求
                        //进行充值
                        if (flag) {
                            progressDialog = new ProgressDialog(context);
                            progressDialog.setTitle("提示");
                            progressDialog.setMessage("数据加载中...");
                            progressDialog.setCancelable(false);
                            progressDialog.show();
                            //这里开启一个子线程，，休眠三秒
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
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



}
