package com.example.jun.bisaixiangmu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HuanJing4DB extends SQLiteOpenHelper {
    private static final String CREATE_TABLE = "create table zhibiao(" +
            "id Integer primary key autoincrement," +
            "weidu Integer," +
            "shidu Integer," +
            "guangzhao Integer," +
            "co2 Integer," +
            "pm25 Integer," +
            "zhuangtai Integer)";

    public HuanJing4DB(Context context) {
        super(context, "Huanjinshuju.db", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists zhibiao");
        onCreate(db);
    }
}
