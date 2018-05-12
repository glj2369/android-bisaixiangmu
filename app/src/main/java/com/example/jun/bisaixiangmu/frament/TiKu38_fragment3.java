package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.http.HttpUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_ADDRESS;
import static com.example.jun.bisaixiangmu.activity.IpSetActivity.TEST_HTTP;

public class TiKu38_fragment3 extends Fragment {
    private Context context;
    private List<String> datas;
    private ListView tiku_39_list;
    private String address;
    private ArrayAdapter<String> adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        datas.add("中超-对攻大战国安2-2富力 傅欢因辱骂裁判被足协停赛6场");
        datas.add("NBA-汤神正和勇士讨论降薪提前续约 东部第一主帅正式下课");
        datas.add("英超-桑切斯哑火曼联客平铁锤帮 穆里尼奥:绝对不会批评球员");
        datas.add("天津女排否认邀请朱婷加盟 丁俊晖入选斯诺克名人堂");
        datas.add("CBA-诺维茨基恩师登陆执教四川 拜纳姆被香港次级联赛球队拒绝");
        adapter=new ArrayAdapter<String>(context,R.layout.tiku32_item,datas);
        tiku_39_list.setAdapter(adapter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tiku38_fragment3,null);
        tiku_39_list= view.findViewById(R.id.tiku_39_list);
        datas=new ArrayList<>();
        return view;
    }
}
