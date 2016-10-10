package com.idealcn.hxchat.ui.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.idealcn.hxchat.R;
import com.idealcn.hxchat.domain.ChatConfig;
import com.idealcn.hxchat.tools.PreferenceUtils;
import com.idealcn.hxchat.ui.activity.InviteNotifyActivity;
import com.idealcn.hxchat.ui.activity.MainActivity;
import com.idealcn.hxchat.widget.ContactItemView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * author:idealcn
 * date:16-8-7 下午10:43
 */
@ContentView(R.layout.fragment_contact_list)
public class ContactListFragment extends ChatBaseFragment implements View.OnClickListener {
    @ViewInject(R.id.contact_list)
    private ListView mContactList;

    private  View headerView;




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

        List<String> lists = new ArrayList<>();
        lists.add("item---1");
        lists.add("item---3");
        mContactList.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,lists));
    }

    @Override
    public void onResume() {
        super.onResume();

        flag = PreferenceUtils.getInstance().getAcceptInvite();
        ContactItemView contactItemView = (ContactItemView) headerView.findViewById(R.id.item_invite);
        contactItemView.setNotifyVisible(flag);
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
                break;
            case R.id.item_chatroom:

                break;
            default:
                break;
        }
    }
}
