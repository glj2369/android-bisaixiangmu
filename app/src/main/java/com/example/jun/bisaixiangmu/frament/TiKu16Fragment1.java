package com.example.jun.bisaixiangmu.frament;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean20;
import com.example.jun.bisaixiangmu.utils.List_TiKu20Adapter;

import java.util.ArrayList;
import java.util.List;

public class TiKu16Fragment1 extends Fragment {
    private Context context;
    private ListView list_view_tiku16;
    private List<Bean20> bean16List;
    private List_TiKu20Adapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bean20 bean20_1=new Bean20(R.mipmap.ic_launcher_round,"辽A  123456",100);
        Bean20 bean20_2=new Bean20(R.mipmap.ic_launcher_round,"辽A  223456",100);
        bean16List.add(bean20_1);
        bean16List.add(bean20_2);
        adapter=new List_TiKu20Adapter(bean16List,context);
        list_view_tiku16.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tiku_16_layout1, null);
        bean16List=new ArrayList<>();
        list_view_tiku16=view.findViewById(R.id.list_view_tiku16);
        return view;
    }
}
