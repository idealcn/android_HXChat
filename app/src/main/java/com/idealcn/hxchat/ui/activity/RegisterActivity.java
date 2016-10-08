package com.idealcn.hxchat.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Event(value = R.id.confirm)
    private void register(View view) {
        final ProgressDialog dialog = ProgressDialog.show(this, "提示", "正在注册...", true, false);
        dialog.show();
        final String username = mRegisterName.getText().toString().trim();
        final String password = mRegisterPassword.getText().toString().trim();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    EMClient.getInstance().createAccount(username, password);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            openActivity(RegisterActivity.this, MainActivity.class);
                            finish();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }


}
