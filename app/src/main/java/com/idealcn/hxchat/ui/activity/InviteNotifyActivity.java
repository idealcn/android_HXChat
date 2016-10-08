package com.idealcn.hxchat.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.idealcn.hxchat.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar.setTitle("申请与通知");
    }
}
