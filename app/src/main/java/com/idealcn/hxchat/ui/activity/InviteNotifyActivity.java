package com.idealcn.hxchat.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.idealcn.hxchat.R;
import com.idealcn.hxchat.bean.InviteMessage;
import com.idealcn.hxchat.db.InviteMessageDao;
import com.idealcn.hxchat.tools.LogUtils;
import com.idealcn.hxchat.ui.adapter.InviteMsgAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * author:idealgn
 * date:16-10-8 下午4:02
 */
@ContentView(R.layout.activity_invite_notify)
public class InviteNotifyActivity extends ChatBaseActivity {
    @ViewInject(android.R.id.empty)
    private TextView mEmptyView;
    @ViewInject(android.R.id.list)
    private ListView mListView;

    private InviteMsgAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar.setTitle("申请与通知");
        InviteMessageDao dao = new InviteMessageDao();
        List<InviteMessage> messageList = dao.getMessageList();
        LogUtils.d( "InviteNotify: "+messageList.size());
        if (messageList.size()>0){
            adapter = new InviteMsgAdapter(this,messageList);
            mListView.setAdapter(adapter);
        }
    }
}
