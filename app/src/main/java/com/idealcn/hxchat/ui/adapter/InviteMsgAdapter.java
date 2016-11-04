package com.idealcn.hxchat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContact;
import com.hyphenate.exceptions.HyphenateException;
import com.idealcn.hxchat.R;
import com.idealcn.hxchat.bean.InviteMessage;
import com.idealcn.hxchat.db.UserDao;
import com.idealcn.hxchat.ui.ViewHolder;

import java.util.List;

/**
 * author:idealcn
 * date:2016/10/28 23:01
 */
public class InviteMsgAdapter extends BaseAdapter  {

    private Context context;
    private List<InviteMessage> messageList;
    private LayoutInflater inflater;

    public InviteMsgAdapter(Context context, List<InviteMessage> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public InviteMessage getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_invite_msg, null);
        }

        TextView tvFrom = ViewHolder.getView(convertView, R.id.from);
        TextView tvReason = ViewHolder.getView(convertView, R.id.reason);
        Button btnAccept = ViewHolder.getView(convertView,R.id.accept);
        Button btnRefuse = ViewHolder.getView(convertView,R.id.refuse);

        final InviteMessage message = messageList.get(position);

        tvFrom.setText(message.getFrom());
        tvReason.setText(message.getReason());

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept(message.getFrom());
            }
        });
        btnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refuse(message.getFrom());
            }
        });

        return convertView;
    }



    private void refuse(String from) {
        try {
            EMClient.getInstance().contactManager().declineInvitation(from);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    private void accept(String from) {
        try {
            EMClient.getInstance().contactManager().acceptInvitation(from);
            //保存好友到本地数据库
            UserDao userDao = new UserDao();
            EMContact user = new EMContact(from);
            userDao.saveUser(user);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }
}
