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

import java.util.ArrayList;
import java.util.List;

public class TiKu38_fragment2 extends Fragment {
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
        datas.add("媒体用吸鸦片比喻宣传高考状元   山东户口遭吐槽：高考累死");
        datas.add("全球大学毕业生就业力排名   12所招收非京籍的公办国际班分数线");
        datas.add("教育部：豫湘冀大班额占全国总数52%   北京六区小升初特长生计划");
        datas.add("牢记这8句话 2018中考必将一鸣惊人(图)");
        datas.add("2018年高考不到1个月 各科复习这样做");
        adapter=new ArrayAdapter<String>(context,R.layout.tiku32_item,datas);
        tiku_39_list.setAdapter(adapter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tiku38_fragment2,null);
        tiku_39_list= view.findViewById(R.id.tiku_39_list);
        datas=new ArrayList<>();
        return view;
    }
}
