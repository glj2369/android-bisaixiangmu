package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.activity.TiKu23Activity;

import static android.content.Context.MODE_PRIVATE;

public class TiKu16Fragment3 extends Fragment {
    private Context context;
    private EditText mEtYuZhi;
    private TextView mTvYuZhi;
    private Button mBtSabe;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sp=context.getSharedPreferences("yuzhi",MODE_PRIVATE);
        String zhang_hu_gao_jing = sp.getString("zhang_hu_gao_jing", "50");

        mTvYuZhi.setText(getResources().getString(R.string.gaoJing_yuzhi,zhang_hu_gao_jing));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tiku_16_layout3, null);
        mEtYuZhi=view.findViewById(R.id.et_yuzhi);
        mTvYuZhi=view.findViewById(R.id.tv_show_yuzhi);
        mBtSabe=view.findViewById(R.id.bt_save_yuzhi);

        mBtSabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = mEtYuZhi.getText().toString().trim();
                if ("".equals(string)|| TextUtils.isEmpty(string)){
                    Toast.makeText(context, "设置阈值不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences sp=context.getSharedPreferences("yuzhi",MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("zhang_hu_gao_jing",""+string);
                    boolean commit = edit.commit();
                    Log.e("commitcommit",""+commit);
                    mTvYuZhi.setText(getResources().getString(R.string.gaoJing_yuzhi,string));
                    Toast.makeText(context, "设置成功", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}
