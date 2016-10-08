package com.idealcn.hxchat.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.idealcn.hxchat.bean.InviteMessage;
import com.idealcn.hxchat.bean.User;

import java.util.List;

/**
 * author:idealgn
 * date:16-10-8 下午3:05
 */
public class ChatDBManager {
    private ChatDBOpenHelper helper;

    private ChatDBManager() {
        helper = ChatDBOpenHelper.getHelper();
    }

    private static ChatDBManager manager;

    public synchronized static ChatDBManager getManager() {
        if (manager == null)
            manager = new ChatDBManager();
        return manager;
    }

    public void saveUser(User user) {
        SQLiteDatabase database = helper.getWritableDatabase();
        if (database.isOpen()) {
            ContentValues values = new ContentValues();
            String avatar = user.getAvatar();
            if (avatar != null)
                values.put(UserDao.KEY_AVATAR, avatar);
            String username = user.getUsername();
            if (username != null)
                values.put(UserDao.KEY_NAME, username);
            String nick = user.getNick();
            if (nick != null)
                values.put(UserDao.KEY_NICK, nick);
            database.replace(UserDao.TABLE_NAME, null, values);
        }
    }


    public List<InviteMessage> getInviteMessageList() {
        SQLiteDatabase database = helper.getReadableDatabase();
        InviteMessage message = new InviteMessage();
        Cursor cursor = database.query(InviteMessageDao.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String from = cursor.getString(cursor.getColumnIndex(InviteMessageDao.COLUMN_NAME_FROM));
            message.setFrom(from);

        }
        return null;
    }
}
