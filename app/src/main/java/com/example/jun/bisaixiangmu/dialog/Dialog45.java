package com.example.jun.bisaixiangmu.dialog;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;


public class Dialog45 extends Dialog implements View.OnClickListener {
    private Button sure;
    private Button cancel;
    private EditText jinE;
    private ImageView close;
    private Context context;

    public Dialog45(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_45);
        close = findViewById(R.id.image_close);
        jinE = findViewById(R.id.et_jin_e);
        sure = findViewById(R.id.sure);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        close.setOnClickListener(this);
        sure.setOnClickListener(this);
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
                if ("".equals(trim)|| TextUtils.isEmpty(trim)){
                    Toast.makeText(context, "输入数额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    int i = Integer.parseInt(trim);
                    //访问网络充值数据，同时需要获取当前小车id
                    Toast.makeText(context, "充值成功", Toast.LENGTH_SHORT).show();
                    dismiss();
                }

                break;
            default:
        }
    }
}
