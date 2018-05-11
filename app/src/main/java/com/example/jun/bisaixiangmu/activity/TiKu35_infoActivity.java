package com.example.jun.bisaixiangmu.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;

public class TiKu35_infoActivity extends BaseActivity {
    private LinearLayout tiku_35_info_call;

    @Override
    protected String getLayoutTitle() {
        return "详细信息";
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_ti_ku35_info;
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
        tiku_35_info_call = findViewById(R.id.tiku_35_info_call);
        tiku_35_info_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //01088888888

                if (ActivityCompat.checkSelfPermission(TiKu35_infoActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                   ActivityCompat.requestPermissions(TiKu35_infoActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else {
                    call();
                }

//                Uri uri = Uri.parse("tel:01088888888");
//                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//                startActivity(intent);

            }
        });
    }

    private void call() {
        Uri uri1 = Uri.parse("tel:01088888888");
        Intent it = new Intent(Intent.ACTION_DIAL, uri1);
        startActivity(it);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            if (grantResults.length!=0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                call();
            }else {
                Toast.makeText(this, "你需要打电话的权限", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}
