package com.idealcn.hxchat.ui.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.idealcn.hxchat.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends ChatBaseActivity {
    @ViewInject(R.id.btn_message)
    private ImageButton mBtnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
