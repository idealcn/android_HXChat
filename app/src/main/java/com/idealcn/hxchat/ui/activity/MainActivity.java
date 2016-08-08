package com.idealcn.hxchat.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.idealcn.hxchat.R;
import com.idealcn.hxchat.ui.fragment.ChatBaseFragment;
import com.idealcn.hxchat.ui.fragment.ContactListFragment;
import com.idealcn.hxchat.ui.fragment.MessageListFragment;
import com.idealcn.hxchat.ui.fragment.StatusFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends ChatBaseActivity {
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_CONTACT = "contact";
    private static final String TAG_STATUS = "status";
    @ViewInject(R.id.btn_message)
    private ImageButton mBtnMessage;

    private ChatBaseFragment[] fragments = null;
    private MessageListFragment messageListFragment = null;
    private ContactListFragment contactListFragment = null;
    private StatusFragment statusFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageListFragment = new MessageListFragment();
        contactListFragment = new ContactListFragment();
        statusFragment = new StatusFragment();
        fragments = new ChatBaseFragment[]{messageListFragment,contactListFragment,statusFragment};

        getSupportFragmentManager().beginTransaction().add(R.id.content_container,messageListFragment,TAG_MESSAGE)
                .add(R.id.content_container,contactListFragment,TAG_CONTACT)
                .add(R.id.content_container,statusFragment,TAG_STATUS)
                .hide(contactListFragment).hide(statusFragment).show(messageListFragment).commit();
    }


    @Event(value = R.id.message_container)
    private void changeToMessage(View view){


        if (messageListFragment.isVisible())
            return;

        if (contactListFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(contactListFragment).show(messageListFragment)
                    .commit();
            return;
        }

        if (statusFragment.isVisible()){
            getSupportFragmentManager().beginTransaction().hide(statusFragment).show(messageListFragment)
                    .commit();
            return;
        }




    }

    @Event(value = R.id.status_container)
    private void changeToStatus(View view){
        if (statusFragment.isVisible()){
            return;
        }

        if (messageListFragment.isVisible()){
            getSupportFragmentManager().beginTransaction().hide(messageListFragment)
                    .show(statusFragment).commit();
            return;
        }

        if (contactListFragment.isVisible()){
            getSupportFragmentManager().beginTransaction().hide(contactListFragment)
                    .show(statusFragment).commit();
        }
    }

    @Event(value = R.id.contact_container)
    private void changeToContact(View view){
        if (contactListFragment.isVisible())
            return;

        if (messageListFragment.isVisible()){
            getSupportFragmentManager().beginTransaction().hide(messageListFragment)
                    .show(contactListFragment).commit();
            return;
        }

        if (statusFragment.isVisible()){
            getSupportFragmentManager().beginTransaction().hide(statusFragment)
                    .show(contactListFragment).commit();
        }
    }
}
