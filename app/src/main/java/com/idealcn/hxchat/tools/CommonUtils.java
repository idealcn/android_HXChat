package com.idealcn.hxchat.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

/**
 * author:idealgn
 * date:16-10-8 下午5:47
 */
public class CommonUtils {

    public static void registeReceiver(Context context, BroadcastReceiver receivers,String[]actions){
        IntentFilter filter = new IntentFilter();
        for (String action : actions){
            filter.addAction(action);
        }
        context.registerReceiver(receivers,filter);
    }
}
