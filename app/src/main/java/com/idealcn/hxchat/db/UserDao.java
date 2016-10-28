package com.idealcn.hxchat.db;

import com.idealcn.hxchat.bean.User;

import java.util.List;

/**
 * author:idealgn
 * date:16-10-8 下午3:02
 */
public class UserDao {
    public static final String TABLE_NAME = "user";

    public static final String KEY_NAME = "username";
    public static final String KEY_NICK = "nick";
    public static final String KEY_AVATAR = "avatar";

    public static final String TABLE_CREATOR = "create table "+ TABLE_NAME
            +" ( _ID INTEGER  PRIMARY KEY AUTOINCREMENT,"
            +KEY_NICK+" text,"
            +KEY_NAME+" text,"
            +KEY_AVATAR+" text"
            +");";

    private  ChatDBManager manager;
    public UserDao(){

        manager = ChatDBManager.getManager();
    }

    public void saveUser(User user){
        manager.saveUser(user);
    }


    public List<User> getContact() {
        return manager.getContact();
    }
}
