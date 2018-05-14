package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean3;
import com.example.jun.bisaixiangmu.db.ChongzhiHistory;
import com.example.jun.bisaixiangmu.utils.List_item45fragment3Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TiKu45_fragment3 extends Fragment {
    private Context context;
    private ListView tiku_45_frament3_list;
    private TextView tiku_45_error;
    private List<Bean3> bean3List;
    private List_item45fragment3Adapter adapter;
    private ChongzhiHistory chongzhiHistory;
    private Timer timer;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
        if (bean3List.size()==0){
            tiku_45_error.setVisibility(View.VISIBLE);
        }else {
            adapter.notifyDataSetChanged();
            tiku_45_error.setVisibility(View.GONE);
        }
    }

    private void getData() {
        SQLiteDatabase db = chongzhiHistory.getReadableDatabase();
        Cursor cursor = db.query("tiku45db", null, null, null, null, null, null);

        if (cursor.moveToFirst()){
            bean3List.clear();
            do {
                int carId = cursor.getInt(cursor.getColumnIndex("carId"));
                int chongNum = cursor.getInt(cursor.getColumnIndex("chongNum"));
                String historyTime = cursor.getString(cursor.getColumnIndex("historyTime"));
                bean3List.add(new Bean3(carId,chongNum,"admin",historyTime));
            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        if (db!=null){
            db.close();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.tiku45_fragment3, null);
        tiku_45_frament3_list=view.findViewById(R.id.tiku_45_frament3_list);
        tiku_45_error=view.findViewById(R.id.tiku_45_error);
        bean3List=new ArrayList<>();
        timer = new Timer();
        adapter= new List_item45fragment3Adapter(context, bean3List);
        tiku_45_frament3_list.setAdapter(adapter);
        chongzhiHistory=new ChongzhiHistory(context);
        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        if (timer != null) {
            timer.purge();
            timer.cancel();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (timer != null) {
            timer = new Timer();
            timer.schedule(new TimerTask5(), 5000, 5000);
        }
    }

    class TimerTask5 extends TimerTask {

        @Override
        public void run() {
            getData();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (bean3List.size()==0){
                        tiku_45_error.setVisibility(View.VISIBLE);
                    }else {
                        adapter.notifyDataSetChanged();
                        tiku_45_error.setVisibility(View.GONE);
                    }
                }
            });

        }
    }
}
