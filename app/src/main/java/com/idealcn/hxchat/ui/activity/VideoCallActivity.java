package com.idealcn.hxchat.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.idealcn.hxchat.R;
import com.idealcn.hxchat.tools.LogUtils;

import org.xutils.view.annotation.ContentView;

/**
 * 作者:idealgn
 * 日期:16-11-4 上午10:18
 * 类描述:视频通话界面
 */
@ContentView(R.layout.activity_video_call)
public class VideoCallActivity extends ChatBaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //添加通话状态的监听
        addCallStateListener();
    }

    private void addCallStateListener() {
        EMClient.getInstance().callManager().addCallStateChangeListener(new EMCallStateChangeListener() {
            @Override
            public void onCallStateChanged(CallState callState, CallError error) {
                switch (callState) {
                    case CONNECTING: // 正在连接对方
                        LogUtils.d("CONNECTING");
                        break;
                    case CONNECTED: // 双方已经建立连接
                        LogUtils.d("CONNECTED");
                        break;

                    case ACCEPTED: // 电话接通成功
                        LogUtils.d("ACCEPTED");
                        break;
                    case DISCONNNECTED: // 电话断了
                        LogUtils.d("DISCONNNECTED");
                        break;
                    case NETWORK_UNSTABLE: //网络不稳定
                        LogUtils.d("NETWORK_UNSTABLE");
                        if(error == CallError.ERROR_NO_DATA){
                            //无通话数据
                        }else{
                        }
                        break;
                    case NETWORK_NORMAL: //网络恢复正常
                        LogUtils.d("NETWORK_NORMAL");
                        break;
                    default:
                        break;
                }

            }
        });
    }
}
