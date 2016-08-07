package com.idealcn.hxchat;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import org.xutils.x;

/**
 * author:idealcn
 * date:16-8-7 下午6:49
 */
public class ChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        EMClient.getInstance().init(this,options);

        //xutils
        x.Ext.init(this);
    }
}
