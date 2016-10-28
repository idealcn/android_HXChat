package com.idealcn.hxchat.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.idealcn.hxchat.R;

import org.xutils.view.annotation.ContentView;

/**
 * author:idealcn
 * date:2016/10/8 21:40
 */
@ContentView(R.layout.activity_group)
public class GroupActivity extends ChatBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar.setTitle("群聊");
    }
}
