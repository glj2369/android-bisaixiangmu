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
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;
import com.example.jun.bisaixiangmu.bean.Bean16_fragment2;
import com.example.jun.bisaixiangmu.utils.List16_adapter_frag2;

import java.util.ArrayList;
import java.util.List;

public class TiKu16Fragment2 extends Fragment {
    private Context context;
    private ListView tiku_16_layout2_list;
    private TextView item_textview_1;
    private TextView item_textview_2;
    private TextView item_textview_3;
    private TextView item_textview_4;
    private List<Bean16_fragment2> list;
    private List16_adapter_frag2 adapter;

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
        list.add(new Bean16_fragment2("李四","鲁B10001",100,120));
        list.add(new Bean16_fragment2("李四","鲁B10003",300,315));
        list.add(new Bean16_fragment2("李四","鲁B10004",200,200));
        adapter=new List16_adapter_frag2(list,context);
        tiku_16_layout2_list.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tiku_16_layout2, null);
        tiku_16_layout2_list=view.findViewById(R.id.tiku_16_layout2_list);
        item_textview_1=view.findViewById(R.id.item_textview_1);
        item_textview_2=view.findViewById(R.id.item_textview_2);
        item_textview_3=view.findViewById(R.id.item_textview_3);
        item_textview_4=view.findViewById(R.id.item_textview_4);
        list=new ArrayList<>();
        return view;
    }
}
