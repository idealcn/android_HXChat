package com.idealcn.hxchat.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Environment;

import java.io.File;

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

    public static void deleteDatabase(Context context){
        File file = new File("/data/data/"+context.getPackageName()+"/databases");
        if (file.exists()) {
            for (File f : file.listFiles()){
                boolean delete = f.delete();
                LogUtils.d(f.getName()+"----delete----"+delete);
            }
        }
    }
}
