package com.idealcn.hxchat.tools;

import com.idealcn.hxchat.ui.activity.ChatBaseActivity;

import java.util.Stack;

/**
 * author:idealcn
 * date:16-8-7 下午6:08
 */
public class ActivityStack {

    private  Stack<ChatBaseActivity> activities = new Stack<>();

    private static ActivityStack instance;
    private ActivityStack(){}

    public synchronized static ActivityStack getInstance(){
        if (instance==null)
            instance = new ActivityStack();
        return instance;
    }

    public  void push(ChatBaseActivity activity){
        if (activity!=null)
        activities.push(activity);
    }

    public void pop(ChatBaseActivity activity){

    }


}
