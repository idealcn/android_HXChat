package com.idealcn.hxchat.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hyphenate.chat.EMContact;
import com.idealcn.hxchat.bean.InviteMessage;

import java.util.ArrayList;
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



    public void saveUser(EMContact user) {
        SQLiteDatabase database = helper.getWritableDatabase();
        if (database.isOpen()) {
            ContentValues values = new ContentValues();
            String username = user.getUsername();
            if (username != null)
                values.put(UserDao.KEY_NAME, username);
            database.replace(UserDao.TABLE_NAME, null, values);
        }
    }

    public boolean hasUser(String name){
        boolean flag = false;
        SQLiteDatabase database = helper.getReadableDatabase();
        if (database.isOpen()){
            String sql = "select * from "+UserDao.TABLE_NAME+" where "+UserDao.KEY_NAME+" = ?";
            Cursor cursor = database.rawQuery(sql, new String[]{name});
           if (cursor.getCount()>0){
               flag = true;
           }
        }
        return flag;
    }


    public List<InviteMessage> getInviteMessageList() {
        List<InviteMessage> messageList = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query(InviteMessageDao.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            InviteMessage message = new InviteMessage();
            String from = cursor.getString(cursor.getColumnIndex(InviteMessageDao.COLUMN_NAME_FROM));
            message.setFrom(from);
            String reason = cursor.getString(cursor.getColumnIndex(InviteMessageDao.COLUMN_NAME_REASON));
            message.setReason(reason);
            long time = cursor.getLong(cursor.getColumnIndex(InviteMessageDao.COLUMN_NAME_TIME));
            message.setTime(time);
            messageList.add(message);
        }
        return messageList;
    }

    /**
     * 保存邀请信息
     * @param msg
     */
    public void saveInviteMsg(InviteMessage msg) {
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String from = msg.getFrom();
        if (from!=null)
            values.put(InviteMessageDao.COLUMN_NAME_FROM,from);
        String reason = msg.getReason();
        if (reason!=null)
            values.put(InviteMessageDao.COLUMN_NAME_REASON,reason);
        long time = msg.getTime();
        values.put(InviteMessageDao.COLUMN_NAME_TIME,time);

        if(database.isOpen()){
            long insert = database.insert(InviteMessageDao.TABLE_NAME, null, values);
        }
    }

    public List<EMContact> getContact() {
        List<EMContact> userList = new ArrayList<>();
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "select "+UserDao.KEY_NAME+" from "+UserDao.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(UserDao.KEY_NAME));
            EMContact user = new EMContact(name);
            userList.add(user);
        }
        return userList;
    }
}
