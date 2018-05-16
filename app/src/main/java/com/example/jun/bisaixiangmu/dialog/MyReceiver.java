package com.example.jun.bisaixiangmu.dialog;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private AlertDialog alertDialog1;
    private AlertDialog alertDialog2;

    public MyReceiver(final Context context) {
        alertDialog1=new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("网络已连接")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog1.dismiss();
                    }
                })
                .create();
        alertDialog2=new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("网络未连接")
                .setNegativeButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(Settings.ACTION_SETTINGS));
                        Toast.makeText(context, "设置网络", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog1.dismiss();
                    }
                })
                .create();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn= (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();
        if (networkInfo!=null&&networkInfo.isAvailable()){
            if (alertDialog2.isShowing()&&alertDialog2!=null){
                alertDialog2.dismiss();
            }
            alertDialog1.show();
        }else {
            if (alertDialog1.isShowing()&&alertDialog1!=null){
                alertDialog1.dismiss();
            }
            alertDialog2.show();
        }
    }
}
