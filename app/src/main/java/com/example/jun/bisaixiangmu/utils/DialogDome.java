package com.example.jun.bisaixiangmu.utils;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/23.
 * 这是一个网络提示对话框类
 */

public class DialogDome extends BroadcastReceiver {
    AlertDialog alertDialog1;
    AlertDialog alertDialog2;
    public DialogDome(final Context context) {
        alertDialog1 = new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("网络未连接")
                .setNegativeButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "设置网络", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(Settings.ACTION_SETTINGS));
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog1.dismiss();
                    }
                }).create();
        alertDialog2 = new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("网络已连接")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog2.dismiss();
                    }
                }).create();
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager conn = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=conn.getActiveNetworkInfo();
        if (networkInfo!=null&&networkInfo.isAvailable()) {
            if (alertDialog1.isShowing() && alertDialog1 != null) {
                alertDialog1.dismiss();
            }
            alertDialog2.show();
        } else {
            if (alertDialog2.isShowing() && alertDialog2 != null) {
                alertDialog2.dismiss();
            }
            alertDialog1.show();
        }
    }
}
