package com.idealcn.hxchat.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.hyphenate.chat.EMContact;

import java.util.List;

/**
 * author:idealgn
 * date:16-10-8 下午4:58
 */
public class ContactListAdapter extends ArrayAdapter<EMContact>{

    public ContactListAdapter(Context context, int resource, List<EMContact> userList) {
        super(context, resource,userList);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return super.getView(position, convertView, parent);
    }
}
