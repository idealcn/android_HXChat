package com.idealcn.hxchat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.idealcn.hxchat.domain.ChatConfig;
import com.idealcn.hxchat.ui.activity.VideoCallActivity;
import com.idealcn.hxchat.ui.activity.VoiceCallActivity;

/**
 * 作者:idealgn
 * 日期:16-11-4 上午9:50
 * 类描述:语音或者视频通话的广播
 */
public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 拨打方username
        String from = intent.getStringExtra(ChatConfig.KEY_FROM);
        // call type
        String type = intent.getStringExtra(ChatConfig.KEY_TYPE);

        if (ChatConfig.TYPE_VIDEO.equals(type)){
            context.startActivity(new Intent(context,VideoCallActivity.class).putExtra(ChatConfig.KEY_FROM,from).putExtra(ChatConfig.KEY_IS_COMING,true)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }else {
            context.startActivity(new Intent(context,VoiceCallActivity.class).putExtra(ChatConfig.KEY_FROM,from).putExtra(ChatConfig.KEY_TYPE,true)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}
