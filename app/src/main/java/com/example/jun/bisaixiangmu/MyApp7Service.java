package com.example.jun.bisaixiangmu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.jun.bisaixiangmu.activity.HuanJing5Activity;
import com.example.jun.bisaixiangmu.db.HuanJing4DB;

import java.util.Timer;
import java.util.TimerTask;

public class MyApp7Service extends Service {
    private HuanJing4DB huanJing4DB;
    private NotificationManager notificationManager;
    private Notification notification;
    private Timer timer;
    private MyBinder myBinder;
    private String title = "阈值提醒";
    private String content = "";
    private String oldContent = "";


    @Override
    public IBinder onBind(Intent intent) {
        myBinder = new MyBinder();

        return myBinder;
    }

    class MyBinder extends Binder {

    }

    public void tongzhijiance() {
        SharedPreferences sp = getSharedPreferences("yuzhi", MODE_PRIVATE);
        int wendu1 = Integer.parseInt(sp.getString("wendu", "20"));
        int shidu1 = Integer.parseInt(sp.getString("shidu", "30"));
        int guangzhao1 = Integer.parseInt(sp.getString("guangzhao", "50"));
        int co21 = Integer.parseInt(sp.getString("co2", "100"));
        int pm251 = Integer.parseInt(sp.getString("pm25", "80"));
        int zhuangtai1 = Integer.parseInt(sp.getString("zhuangtai", "2"));
        SQLiteDatabase db = huanJing4DB.getReadableDatabase();
        int weidu2 = 20;
        int shidu2 = 70;
        int guangzhao2 = 550;
        int co22 = 250;
        int pm252 = 90;
        int zhuangtai2 = 3;
        Cursor cursor = db.query("zhibiao", null, null, null, null, null, null);
        if (cursor.moveToLast()) {
            weidu2 = cursor.getInt(cursor.getColumnIndex("weidu"));
            shidu2 = cursor.getInt(cursor.getColumnIndex("shidu"));
            guangzhao2 = cursor.getInt(cursor.getColumnIndex("guangzhao"));
            co22 = cursor.getInt(cursor.getColumnIndex("co2"));
            pm252 = cursor.getInt(cursor.getColumnIndex("pm25"));
            zhuangtai2 = cursor.getInt(cursor.getColumnIndex("zhuangtai"));
            cursor.close();
            db.close();
        } else {
            cursor.close();
            db.close();
        }
        StringBuilder stringBuilder = new StringBuilder();
        checked(wendu1, weidu2, "温度", stringBuilder);
        checked(shidu1, shidu2, "湿度", stringBuilder);
        checked(guangzhao1, guangzhao2, "光照", stringBuilder);
        checked(co21, co22, "CO2", stringBuilder);
        checked(pm251, pm252, "PM25", stringBuilder);
        checked(zhuangtai1, zhuangtai2, "PM25", stringBuilder);
        content = stringBuilder.toString();
        Log.e("content", "通知内容" + content);

    }

    public void checked(int wendu1, int weidu2, String titleStr, StringBuilder stringBuilder) {
        if (wendu1 < weidu2) {
            String string = getResources().getString(R.string.notifi, titleStr, wendu1, weidu2);
            stringBuilder.append(string + ",");
        }
    }

    public void kaiqitongzhi() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, HuanJing5Activity.class), 0);
        notification = new NotificationCompat.Builder(this)
                .setContentText("" + title)
                .setContentText("" + content)
                .setContentIntent(pi)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .build();
        notificationManager.notify(1, notification);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(new TimerChecked(), 500, 10 * 1000);
        huanJing4DB = new HuanJing4DB(this);
    }

    class TimerChecked extends TimerTask {

        @Override
        public void run() {
            tongzhijiance();
            if (oldContent.equals(content)){
                tongzhijiance();
            }else {
                kaiqitongzhi();
            }
            oldContent=content;
            Log.e("TimerChecked", "-----------");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
    }

}
