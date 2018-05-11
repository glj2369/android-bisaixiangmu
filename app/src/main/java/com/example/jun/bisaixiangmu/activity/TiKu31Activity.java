package com.example.jun.bisaixiangmu.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.db.YiJian31DB;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TiKu31Activity extends BaseActivity implements View.OnClickListener {

    private TextView tiku_31_yijian;
    private Button tiku_31_bt_save;
    private EditText tiku_31_shouji;
    private EditText tiku_31_content;
    private EditText tiku_31_title;
    private YiJian31DB yiJian31DB;

    @Override
    protected String getLayoutTitle() {
        return "意见反馈";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku31;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onAfter() {
        finish();
    }

    @Override
    protected void initView() {
        yiJian31DB= new YiJian31DB(this);
        tiku_31_yijian = findViewById(R.id.tiku_31_yijian);
        tiku_31_bt_save = findViewById(R.id.tiku_31_bt_save);
        tiku_31_shouji = findViewById(R.id.tiku_31_shouji);
        tiku_31_title = findViewById(R.id.tiku_31_title);
        tiku_31_content = findViewById(R.id.tiku_31_content);
        tiku_31_bt_save.setOnClickListener(this);
        tiku_31_yijian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiku_31_yijian:
                startActivity(new Intent(TiKu31Activity.this,YiJian31Activity.class));
                break;
            case R.id.tiku_31_bt_save:
                String title = tiku_31_title.getText().toString().trim();
                String content = tiku_31_content.getText().toString().trim();
                String shouji = tiku_31_shouji.getText().toString().trim();
                if ("".equals(title)||"".equals(content)||"".equals(shouji)
                        || TextUtils.isEmpty(title)||TextUtils.isEmpty(content)||TextUtils.isEmpty(shouji)){
                    Toast.makeText(this, "不能有空值", Toast.LENGTH_SHORT).show();
                }else {
                    SQLiteDatabase db = yiJian31DB.getWritableDatabase();
                    ContentValues values=new ContentValues();
                    values.put("title",title);
                    values.put("phone",shouji);
                    values.put("content",content);
                    String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                    values.put("time",time);
                    long insert = db.insert("myYiJian", null, values);
                    db.close();
                    Log.e("YiJian31DB","意见提交，添加到第"+insert+"条数据");
                    //清空
                    tiku_31_title.setText("");
                    tiku_31_content.setText("");
                    tiku_31_shouji.setText("");
                    Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
