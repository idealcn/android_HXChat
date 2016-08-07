package com.idealcn.hxchat.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.idealcn.hxchat.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Event(value = R.id.signup)
    private void login(View view){
        final ProgressDialog dialog = ProgressDialog.show(this, "提示", "正在登陆...", true, false);
        dialog.show();
        final String username = mLoginName.getText().toString().trim();
        final String password = mLoginPassword.getText().toString().trim();

        new Thread(){
            @Override
            public void run() {
                super.run();
                EMClient.getInstance().login(username, password, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                openActivity(LoginActivity.this,MainActivity.class);
                                finish();
                            }
                        });
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
