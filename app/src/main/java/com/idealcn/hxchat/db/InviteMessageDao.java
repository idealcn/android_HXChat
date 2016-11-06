package com.idealcn.hxchat.db;

import com.idealcn.hxchat.bean.InviteMessage;

import java.util.List;

/**
 * author:idealgn
 * date:16-10-8 下午4:07
 */
public class InviteMessageDao {

    static final String TABLE_NAME = "new_friends_msgs";
    static final String COLUMN_NAME_ID = "id";
    static final String COLUMN_NAME_FROM = "username";
    static final String COLUMN_NAME_GROUP_ID = "groupid";
    static final String COLUMN_NAME_GROUP_Name = "groupname";

    static final String COLUMN_NAME_TIME = "time";
    static final String COLUMN_NAME_REASON = "reason";
    public static final String COLUMN_NAME_STATUS = "status";
    static final String COLUMN_NAME_ISINVITEFROMME = "isInviteFromMe";
    static final String COLUMN_NAME_GROUPINVITER = "groupinviter";



    public static final String TABLE_CREATOR = "create table "+ TABLE_NAME
            +" ( _ID INTEGER  PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_NAME_FROM+" text,"
            +COLUMN_NAME_REASON+" text,"
            +COLUMN_NAME_TIME+" text"
            +");";


    private ChatDBManager manager;

    public InviteMessageDao(){
        manager = ChatDBManager.getManager();
    }


    public List<InviteMessage> getMessageList(){
        return ChatDBManager.getManager().getInviteMessageList();

    }

    public void saveInviteMsg(InviteMessage msg){
        manager.saveInviteMsg(msg);
    }

    /**
     * 删除某条申请信息
     * @param from
     */
    public void deleteInviteMsg(String from) {
        manager.deleteInviteMsg(from);
    }
}
