package com.idealcn.hxchat;

import android.content.Context;
import android.content.Intent;

import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMError;
import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.NetUtils;
import com.idealcn.hxchat.bean.InviteMessage;
import com.idealcn.hxchat.db.InviteMessageDao;
import com.idealcn.hxchat.domain.ChatConfig;
import com.idealcn.hxchat.tools.LogUtils;

import java.util.List;

/**
 * Created by idealgn on 16-10-8.
 */
public class ChatHepler {

    private static ChatHepler instance = null;
    private Context context;

    //黑名单列表
    private List<String> blackList;

    public synchronized static ChatHepler getInstance() {
        if (instance == null)
            instance = new ChatHepler();
        return instance;
    }

    private ChatHepler() {
    }

    public void init(Context context) {
        this.context = context;
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        EMClient.getInstance().init(context, options);
        //状态连接的监听
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
        //联系人的监听
        EMClient.getInstance().contactManager().setContactListener(new MyEMContactListener());
        //消息的监听
        EMClient.getInstance().chatManager().addMessageListener(new MyEMMessageListener());
        //群组的监听
        EMClient.getInstance().groupManager().addGroupChangeListener(new MyEMGroupListener());
        //聊天室的监听
        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(new MyEMChatRoomListener());

        //获取黑名单列表
        try {

            blackList = EMClient.getInstance().contactManager().getBlackListFromServer();

        } catch (HyphenateException e) {
            e.printStackTrace();
        }


    }


    /**
     * 状态链接的监听
     */
    private class MyConnectionListener implements EMConnectionListener {

        @Override
        public void onConnected() {

        }

        @Override
        public void onDisconnected(final int error) {

            switch (error) {
                case EMError.USER_REMOVED://账号被移除

                    break;

                case EMError.USER_LOGIN_ANOTHER_DEVICE://账号在另一台设备登陆

                    break;

                default:
                    if (!NetUtils.hasNetwork(context)) {
                        //连接服务器失败
                    } else {
                        //当前网络不可用检查网络设置
                    }

                    break;
            }


        }
    }

    /**
     * 联系人的监听
     */
    private class MyEMContactListener implements EMContactListener {

        @Override
        public void onContactAdded(String s) {
            LogUtils.d("onContactAdded");
        }

        @Override
        public void onContactDeleted(String s) {

        }

        @Override
        public void onContactInvited(String s, String s1) {
            LogUtils.d("onContactInvited");
            //过滤，检测是否存在于黑名单中
            if (blackList.contains(s))return;

            InviteMessageDao messageDao = new InviteMessageDao();
            List<InviteMessage> messageList = messageDao.getMessageList();


            Intent intent = new Intent();
            intent.setAction(ChatConfig.ACTION_INVITE);
            context.sendBroadcast(intent);
            //将邀请信息保存到本地数据库，发送通知到好友申请页面

//            try {
//                //接受好友添加请求
//                EMClient.getInstance().contactManager().acceptInvitation(s);
//            } catch (HyphenateException e) {
//                e.printStackTrace();
//            }
        }

        @Override
        public void onContactAgreed(String s) {
            LogUtils.d("onContactAgreed");
        }

        @Override
        public void onContactRefused(String s) {
            LogUtils.d("onContactRefused");
        }
    }

    /**
     * 消息的监听
     */
    private class MyEMMessageListener implements EMMessageListener {

        @Override
        public void onMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageReadAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    }

    /**
     * 群组的监听
     */
    private class MyEMGroupListener implements EMGroupChangeListener {
        @Override
        public void onInvitationReceived(String s, String s1, String s2, String s3) {

        }

        @Override
        public void onApplicationReceived(String s, String s1, String s2, String s3) {

        }

        @Override
        public void onApplicationAccept(String s, String s1, String s2) {

        }

        @Override
        public void onApplicationDeclined(String s, String s1, String s2, String s3) {

        }

        @Override
        public void onInvitationAccpted(String s, String s1, String s2) {

        }

        @Override
        public void onInvitationDeclined(String s, String s1, String s2) {

        }

        @Override
        public void onUserRemoved(String s, String s1) {

        }

        @Override
        public void onGroupDestroy(String s, String s1) {

        }

        @Override
        public void onAutoAcceptInvitationFromGroup(String s, String s1, String s2) {

        }
    }

    private class MyEMChatRoomListener implements EMChatRoomChangeListener {

        @Override
        public void onChatRoomDestroyed(String s, String s1) {

        }

        @Override
        public void onMemberJoined(String s, String s1) {

        }

        @Override
        public void onMemberExited(String s, String s1, String s2) {

        }

        @Override
        public void onMemberKicked(String s, String s1, String s2) {

        }
    }
}
