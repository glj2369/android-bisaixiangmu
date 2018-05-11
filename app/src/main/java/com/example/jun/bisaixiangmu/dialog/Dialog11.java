package com.example.jun.bisaixiangmu.dialog;

import android.app.Dialog;

import android.content.Context;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.annotation.NonNull;

import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean11;
import com.example.jun.bisaixiangmu.bean.Bean22;
import com.example.jun.bisaixiangmu.db.ChongzhiHistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dialog11 extends Dialog implements View.OnClickListener {
    public interface DialogReturn{
        void sure_dialog(List<Map<String ,Integer>> map);
    }

    private Context context;
    private List<Bean11> bean11List;
    private EditText et_dialog_hong;
    private EditText et_dialog_lv;
    private EditText et_dialog_huang;
    private Button dialog_sure;
    private Button dialog_cancel;
    private DialogReturn lister;
    private Map<String ,Integer> map;
    private List<Map<String ,Integer>> mapList=new ArrayList<>();

    public Dialog11(@NonNull Context context, List<Bean11> bean11List) {
        super(context);
        this.context = context;
        this.bean11List=bean11List;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tiku11_dialog);
        et_dialog_hong=findViewById(R.id.et_dialog_hong);
        et_dialog_lv=findViewById(R.id.et_dialog_lv);
        et_dialog_huang=findViewById(R.id.et_dialog_huang);
        dialog_sure=findViewById(R.id.dialog_sure);
        dialog_cancel=findViewById(R.id.dialog_cancel);
        dialog_sure.setOnClickListener(this);
        dialog_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.dialog_sure:
                String hong = et_dialog_hong.getText().toString().trim();
                String lv = et_dialog_lv.getText().toString().trim();
                String huang = et_dialog_huang.getText().toString().trim();
                StringBuilder stringBuilder=new StringBuilder();
                for (Bean11 bean11:bean11List){
                    if (bean11.isChecked()){
                        int id = bean11.getId();
                        stringBuilder.append(id+":");
                    }
                }
                if ("".equals(hong)|| TextUtils.isEmpty(hong)
                        ||"".equals(lv)|| TextUtils.isEmpty(lv)
                        ||"".equals(huang)|| TextUtils.isEmpty(huang)){
                    Toast.makeText(context, "周期不能有空值", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor edit = context.getSharedPreferences("zhouqi",
                            Context.MODE_PRIVATE).edit();
                    edit.putString("lukouid",stringBuilder.toString());
                    edit.putString("hong",hong);
                    edit.putString("lv",lv);
                    edit.putString("huang",huang);
                    edit.apply();
                    //应该网络请求设置周期的，这里保存到本地
                    Toast.makeText(context, "周期设置成功", Toast.LENGTH_SHORT).show();
                    dismiss();
                }

                break;
            case R.id.dialog_cancel:
                dismiss();
                break;
        }
    }


}
