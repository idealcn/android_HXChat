package com.idealcn.hxchat.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContact;
import com.idealcn.hxchat.R;
import com.idealcn.hxchat.db.UserDao;
import com.idealcn.hxchat.tools.LogUtils;
import com.idealcn.hxchat.tools.PreferenceUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * author:idealcn
 * date:16-8-7 下午6:55
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends ChatBaseActivity{
    @ViewInject(R.id.username)
    private EditText mLoginName;
    @ViewInject(R.id.password)
    private EditText mLoginPassword;
    @ViewInject(R.id.signin)
    private Button mBtnIn;
    @ViewInject(R.id.signup)
    private Button mBtnUp;

    private ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String currentUserName = PreferenceUtils.getInstance().getCurrentUserName();
        if(!TextUtils.isEmpty(currentUserName))
            mLoginName.setText(currentUserName);
    }

    private Handler handler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           final  UserDao userDao = new UserDao();
            dialog.setMessage("获取好友列表");
            EMClient.getInstance().contactManager().aysncGetAllContactsFromServer(new EMValueCallBack<List<String>>() {
                @Override
                public void onSuccess(List<String> strings) {
                    LogUtils.d("onSuccess: "+strings.size());
                    for (String s : strings){
                        if (!userDao.hasUser(s)) {
                            EMContact user = new EMContact(s);
                            userDao.saveUser(user);
                        }
                    }
                    dialog.dismiss();
                    openActivity(LoginActivity.this,MainActivity.class);
                    finish();
                }

                @Override
                public void onError(int i, String s) {
                    LogUtils.d("onError: "+s+i);
                }
            });




        }
    };

    @Event(value = R.id.signup)
    private void login(View view){

        dialog = ProgressDialog.show(this, "提示", "正在登陆...", true, false);
        dialog.show();
        final String username = mLoginName.getText().toString().trim();
        final String password = mLoginPassword.getText().toString().trim();
        PreferenceUtils instance = PreferenceUtils.getInstance();
        instance.setCurrentPassword(password);
        instance.setCurrentUserName(username);
        new Thread(){
            @Override
            public void run() {
                super.run();
                EMClient.getInstance().login(username, password, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().chatManager().loadAllConversations();
                        EMClient.getInstance().groupManager().loadAllGroups();

                        handler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onError(int i, final String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.setMessage("error---"+s);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.dismiss();
                                    }
                                },1000);
                            }
                        });
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        }.start();
    }


    @Event(value = R.id.signin)
    private void register(View view){
        openActivity(this,RegisterActivity.class);
    }
}
