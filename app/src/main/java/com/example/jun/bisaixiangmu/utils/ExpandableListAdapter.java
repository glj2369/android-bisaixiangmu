package com.example.jun.bisaixiangmu.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jun.bisaixiangmu.R;

import java.util.List;
import java.util.Map;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private Map<String,List<String>> listMap;
    private String[] strings;


    public ExpandableListAdapter(Context context, Map<String, List<String>> listMap,String[] mapKey) {
        this.context = context;
        this.listMap = listMap;
        this.strings = mapKey;
        if (strings==null){
            strings=new String[]{"一号站台", "二号站台"};
        }
    }

    @Override
    public int getGroupCount() {
        return listMap.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listMap.get(strings[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listMap.get(strings[groupPosition]);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listMap.get(strings[groupPosition]).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHoderTest viewHoder;
        if (convertView==null){
            viewHoder=new ViewHoderTest();
            convertView= LayoutInflater.from(context).inflate(R.layout.list_view_item,parent,false);
            viewHoder.textView=convertView.findViewById(R.id.base_textview);
            convertView.setTag(viewHoder);
        }else {
            viewHoder= (ViewHoderTest) convertView.getTag();
        }
        viewHoder.textView.setText(strings[groupPosition]);


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHoderTest viewHoder;
        if (convertView==null){
            viewHoder=new ViewHoderTest();
            convertView= LayoutInflater.from(context).inflate(R.layout.list_view_item,parent,false);
            viewHoder.textView=convertView.findViewById(R.id.base_textview);
            convertView.setTag(viewHoder);
        }else {
            viewHoder= (ViewHoderTest) convertView.getTag();
        }
        viewHoder.textView.setText(listMap.get(strings[groupPosition]).get(childPosition));
        return convertView;
    }
    class ViewHoderTest{
        TextView textView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
