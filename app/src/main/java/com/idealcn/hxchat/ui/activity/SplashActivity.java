package com.idealcn.hxchat.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hyphenate.chat.EMClient;
import com.idealcn.hxchat.R;

import org.xutils.view.annotation.ContentView;

/**
 * author:idealcn
 * date:16-8-7 下午6:01
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends ChatBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (EMClient.getInstance().isLoggedInBefore()){
            openActivity(this,MainActivity.class);
            finish();
        }else {
            openActivity(this,LoginActivity.class);
//            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
            finish();
        }

    }


}
