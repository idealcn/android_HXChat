package com.idealcn.hxchat.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.idealcn.hxchat.tools.ActivityStack;

import org.xutils.x;

/**
 * Created by idealcn on 16-8-7.
 */
public class ChatBaseActivity extends ActionBarActivity {


    protected  ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        ActivityStack.getInstance().push(this);



        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setShowHideAnimationEnabled(true);
        actionBar.show();
    }


    protected  <T extends ChatBaseActivity> void openActivity(T activity,Class<? extends ChatBaseActivity> clazz){
        Intent intent = new Intent(activity,clazz);
        startActivity(intent);
    }
}
