package com.example.jun.bisaixiangmu.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.utils.List_item33Adapter;

public class TiKu33Activity extends BaseActivity implements View.OnClickListener {
    private ListView list_item33;
    private List_item33Adapter adapter;
    private AlertDialog dialog;
    private TextView tiku_33_textshow;

    @Override
    protected String getLayoutTitle() {
        return "高速路况";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku33;
    }

    @Override
    protected void initData() {
        list_item33.setAdapter(adapter);
    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        list_item33 = findViewById(R.id.list_item33);
        adapter = new List_item33Adapter(this);
        tiku_33_textshow = findViewById(R.id.tiku_33_textshow);
        tiku_33_textshow.setOnClickListener(this);
        dialog = new AlertDialog.Builder(this)
                .setTitle("公告详情")
                .setIcon(R.drawable.tiku33)
                .setMessage("深海高速胶州、莱西服务器封闭施工通知")
                .setPositiveButton("确定2", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_33_textshow:
                dialog.show();
                break;
            default:
                break;
        }
    }
}
