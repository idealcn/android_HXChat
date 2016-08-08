package com.idealcn.hxchat;

import android.app.Application;
import android.util.Log;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
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


        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {
                Log.d("chat","onConnected");
            }

            @Override
            public void onDisconnected(int i) {

            }
        });
        EMClient.getInstance().contactManager().setContactListener(new ChatContactListener());
    }

    private class ChatContactListener implements EMContactListener{

        @Override
        public void onContactAdded(String s) {

        }

        @Override
        public void onContactDeleted(String s) {

        }

        @Override
        public void onContactInvited(String s, String s1) {

        }

        @Override
        public void onContactAgreed(String s) {

        }

        @Override
        public void onContactRefused(String s) {

        }
    }
}
