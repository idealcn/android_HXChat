package com.idealcn.hxchat.ui;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;

/**
 * 作者:idealcn
 * 日期:2016/10/28 23:10
 * 类描述:
 */
public class ViewHolder {


    public static <T extends View> T getView(View convertView,int id){

        SparseArray<T> holder = (SparseArray) convertView.getTag();

        if (holder == null) {
            holder = new SparseArray();
            convertView.setTag(holder);
        }
        T childView = holder.get(id);
        if (childView == null) {
            childView = (T) convertView.findViewById(id);
            holder.put(id, childView);
        }
        return childView;
    }
}
