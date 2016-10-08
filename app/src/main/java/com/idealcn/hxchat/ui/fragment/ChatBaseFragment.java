package com.idealcn.hxchat.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idealcn.hxchat.ui.activity.ChatBaseActivity;

import org.xutils.x;

/**
 * author:idealcn
 * date:16-8-7 下午10:11
 */
public class ChatBaseFragment extends Fragment {

    private boolean isInjected = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return x.view().inject(this,inflater,container);
    }

    protected <T extends ChatBaseActivity> void openActivity(Activity activity, Class<T> clazz){
        startActivity(new Intent(activity,clazz));
    }
}
