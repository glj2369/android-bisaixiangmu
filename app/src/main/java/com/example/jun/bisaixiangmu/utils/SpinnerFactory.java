package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.jun.bisaixiangmu.R;

public class SpinnerFactory {
    public interface OnItemSelected {
        void onSelected(int position);
    }

    public static void getSpinner(Context context, Spinner spinner, String[] datas,
                                  final OnItemSelected selected) {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context, R.layout.spinner_item,datas);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (selected!=null){
                    selected.onSelected(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
