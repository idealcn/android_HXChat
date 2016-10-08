package com.idealcn.hxchat.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.idealcn.hxchat.R;
import com.idealcn.hxchat.widget.DeleteInputView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * author:idealcn
 * date:16-8-8 上午10:57
 */
@ContentView(R.layout.activity_add_contact)
public class AddContactActivity extends ChatBaseActivity implements DeleteInputView.OnDeleteButtonClickListener {
    @ViewInject(R.id.delete_view)
    private DeleteInputView mDeleteView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDeleteView.setOnDeleteButtonClickListener(this);
    }



    @Override
    public void onDeleteButtonClick(View view) {
        final String username = mDeleteView.getInputText();
        if (TextUtils.isEmpty(username))
            return;

        //TODO

        final ProgressDialog dialog = ProgressDialog.show(this, "提示", "正在添加...", false, true);
        dialog.show();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    EMClient.getInstance().contactManager().addContact(username,"");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.setMessage("申请成功");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                }
                            },1000);
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.setMessage("添加失败");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                }
                            },1000);
                        }
                    });
                }
            }
        }.start();
    }
}
