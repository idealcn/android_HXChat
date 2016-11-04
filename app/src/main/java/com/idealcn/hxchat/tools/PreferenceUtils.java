package com.idealcn.hxchat.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by idealgn on 16-10-10.
 */
public class PreferenceUtils {

    private static final String SP_NAME = "sp_user";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ACCEPT_INVITE = "accept";
    private static PreferenceUtils instance;
    private SharedPreferences sp;


    public synchronized static PreferenceUtils getInstance() {
        if (instance == null)
            instance = new PreferenceUtils();
        return instance;
    }

    private PreferenceUtils() {
    }

    /**
     * 在Application中进行初始化
     * @param context
     */
    public void init(Context context){
        sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
    }


    public String getCurrentUserName(){
        return sp.getString(KEY_USERNAME,"");
    }

    public void setCurrentUserName(String username){
        sp.edit().putString(KEY_USERNAME,username).commit();
    }


    public String getCurrentPassword(){
        return sp.getString(KEY_PASSWORD,"");
    }

    public void setCurrentPassword(String password){
        sp.edit().putString(KEY_PASSWORD,password).commit();
    }

    public void setAcceptInvite(boolean accept) {
        sp.edit().putBoolean(KEY_ACCEPT_INVITE,accept).commit();
    }

    public boolean getAcceptInvite(){
        return sp.getBoolean(KEY_ACCEPT_INVITE,false);
    }

    /**
     * 清除本地数据
     */
    public void clearAll() {
        sp.edit().clear().commit();
    }
}
