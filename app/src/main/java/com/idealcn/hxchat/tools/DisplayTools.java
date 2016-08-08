package com.idealcn.hxchat.tools;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * author:idealcn
 * date:16-8-8 上午10:41
 */
public class DisplayTools {

    private static DisplayMetrics getMetrics(Context context){
        return context.getResources().getDisplayMetrics();
    }

    public static int getScreenWidth(Context context){
        return  getMetrics(context).widthPixels;
    }

    public static int getScreenHeight(Context context){
        return getMetrics(context).heightPixels;
    }
}
