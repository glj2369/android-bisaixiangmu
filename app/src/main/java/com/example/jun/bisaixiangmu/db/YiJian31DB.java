package com.example.jun.bisaixiangmu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class YiJian31DB extends SQLiteOpenHelper {
    private final static String CREATE_TABLE = "create table myYiJian(" +
            "id integer primary key autoincrement," +
            "title text," +
            "phone text," +
            "content text," +
            "time text)";

    private final static String CREATE_TABLE_2 = "create table testTime(" +
            "id integer primary key autoincrement," +
            "time datetime)";
    private Context context;

    public YiJian31DB(Context context) {
        super(context, "YiJian.db", null, 3);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if  exists myYiJian ");
        onCreate(db);
    }
}
