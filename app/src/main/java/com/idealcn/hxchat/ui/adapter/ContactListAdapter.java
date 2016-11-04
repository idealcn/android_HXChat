package com.idealcn.hxchat.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hyphenate.chat.EMContact;
import com.idealcn.hxchat.R;
import com.idealcn.hxchat.ui.ViewHolder;

import java.util.List;

/**
 * author:idealgn
 * date:16-10-8 下午4:58
 */
public class ContactListAdapter extends ArrayAdapter<EMContact>{

    private LayoutInflater inflater;
    private  List<EMContact> userList;
    private Context context;
    public ContactListAdapter(Context context, int resource, List<EMContact> userList) {
        super(context, resource,userList);
        this.userList = userList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView = inflater.inflate(R.layout.adapter_contact,null);
        }
        EMContact contact = userList.get(position);
        TextView tvName = ViewHolder.getView(convertView, R.id.item_username);
        tvName.setText(contact.getUsername());
        return convertView;
    }
}
