package com.idealcn.hxchat.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.idealcn.hxchat.R;
import com.idealcn.hxchat.tools.PreferenceUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * author:idealgn
 * date:16-10-10 下午4:38
 */
@ContentView(R.layout.activity_userprofile)
public class UserProfileActivity extends ChatBaseActivity{
    @ViewInject(R.id.profile_name)
    private TextView mProfileName;
    @ViewInject(R.id.profile_nick)
    private TextView mProfileNick;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar.setTitle(getResources().getString(R.string.title_user_profile));
        mProfileName.setText(PreferenceUtils.getInstance().getCurrentUserName());
    }
}
