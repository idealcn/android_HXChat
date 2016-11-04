package com.idealcn.hxchat.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.hyphenate.chat.EMContact;
import com.idealcn.hxchat.R;
import com.idealcn.hxchat.db.UserDao;
import com.idealcn.hxchat.tools.PreferenceUtils;
import com.idealcn.hxchat.ui.activity.ChatRoomActivity;
import com.idealcn.hxchat.ui.activity.GroupActivity;
import com.idealcn.hxchat.ui.activity.InviteNotifyActivity;
import com.idealcn.hxchat.ui.adapter.ContactListAdapter;
import com.idealcn.hxchat.widget.ContactItemView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * author:idealcn
 * date:16-8-7 下午10:43
 * 类描述:联系人界面的fragment
 */
@ContentView(R.layout.fragment_contact_list)
public class ContactListFragment extends ChatBaseFragment implements View.OnClickListener {
    @ViewInject(R.id.contact_list)
    private ListView mContactList;

    private  View headerView;

    private ContactListAdapter adapter;

    private List<EMContact> userList = new ArrayList<>();

    private boolean flag;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initHeaderView();
    }



    private void initHeaderView() {

        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_contact_header, null);
        headerView.findViewById(R.id.item_invite).setOnClickListener(this);
        headerView.findViewById(R.id.item_group).setOnClickListener(this);
        headerView.findViewById(R.id.item_chatroom).setOnClickListener(this);
        mContactList.addHeaderView(headerView);

        ContactItemView contactItemView = (ContactItemView) headerView.findViewById(R.id.item_invite);
        contactItemView.setNotifyVisible(flag);


        adapter = new ContactListAdapter(getActivity(), android.R.layout.simple_list_item_1, userList);
        mContactList.setAdapter(adapter);


    }

    @Override
    public void onResume() {
        super.onResume();

        flag = PreferenceUtils.getInstance().getAcceptInvite();
        ContactItemView contactItemView = (ContactItemView) headerView.findViewById(R.id.item_invite);
        contactItemView.setNotifyVisible(flag);


        userList.clear();
        UserDao userDao = new UserDao();
        userList.addAll( userDao.getContactList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_invite:
                PreferenceUtils.getInstance().setAcceptInvite(false);
//                ((ContactItemView) headerView.findViewById(R.id.item_invite)).setNotifyVisible(false);
               startActivity(new Intent(getActivity(), InviteNotifyActivity.class));
                break;
            case R.id.item_group:
                startActivity(new Intent(getActivity(), GroupActivity.class));
                break;
            case R.id.item_chatroom:
                startActivity(new Intent(getActivity(), ChatRoomActivity.class));
                break;
            default:
                break;
        }
    }
}
