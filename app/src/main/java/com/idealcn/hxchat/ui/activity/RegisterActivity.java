package com.idealcn.hxchat.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Matrix2f;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.idealcn.hxchat.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * author:idealcn
 * date:16-8-7 下午7:32
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends ChatBaseActivity {
    @ViewInject(R.id.username)
    private EditText mRegisterName;
    @ViewInject(R.id.password)
    private EditText mRegisterPassword;

    @ViewInject(R.id.register_toast)
    private FrameLayout mToastLayout;
    @ViewInject(R.id.btn_toast)
    private Button mBtnToast;

    private ProgressDialog dialog;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dialog.dismiss();
            mToastLayout.setVisibility(View.VISIBLE);
            mBtnToast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivity(RegisterActivity.this, LoginActivity.class);
                    finish();
                }
            });

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Event(value = R.id.confirm)
    private void register(View view) {
        dialog = ProgressDialog.show(this, "提示", "正在注册...", true, false);
        dialog.show();
        final String username = mRegisterName.getText().toString().trim();
        final String password = mRegisterPassword.getText().toString().trim();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    EMClient.getInstance().createAccount(username, password);
                    mHandler.sendEmptyMessage(0);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }


}
