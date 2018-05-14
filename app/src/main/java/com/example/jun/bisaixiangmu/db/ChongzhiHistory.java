package com.example.jun.bisaixiangmu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
//要求APP使用SQLite数据库保存充值的历史记录，记录
// 车辆编号、充值金额、充值后余额、充值人、充值时间（日期+时间）
// 。任何用户都有权限查询所有人的充值记录
public class ChongzhiHistory extends SQLiteOpenHelper {
    private Context context;
    private static final String CREATE_TABLE="create table history ("
            + "id integer primary key autoincrement, "
            + "carId integer, "
            + "chongNum integer, "
            + "personName text, "
            + "historyTime datetime)";
    private static final String CREATE_TABLE2="create table tiku45db ("
            + "id integer primary key autoincrement, "
            + "carId integer, "
            + "chongNum integer, "
            + "historyTime datetime)";
    public ChongzhiHistory(Context context) {
        super(context, "Account.db", null, 4);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table  if exists history");
        db.execSQL("drop table  if exists tiku45db");
        onCreate(db);
    }
}
