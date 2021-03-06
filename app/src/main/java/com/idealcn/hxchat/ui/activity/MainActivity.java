package com.idealcn.hxchat.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContact;
import com.idealcn.hxchat.R;
import com.idealcn.hxchat.db.UserDao;
import com.idealcn.hxchat.tools.LogUtils;
import com.idealcn.hxchat.ui.fragment.ChatBaseFragment;
import com.idealcn.hxchat.ui.fragment.ContactListFragment;
import com.idealcn.hxchat.ui.fragment.LeftMenuFragment;
import com.idealcn.hxchat.ui.fragment.MessageListFragment;
import com.idealcn.hxchat.ui.fragment.StatusFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends ChatBaseActivity {

    private String TAG = "hx";


    private static final String TAG_MESSAGE = "message";
    private static final String TAG_CONTACT = "contact";
    private static final String TAG_STATUS = "status";
    private static final String TAG_LEFT = "left";


    private ChatBaseFragment[] fragments = null;
    private MessageListFragment messageListFragment = null;
    private ContactListFragment contactListFragment = null;
    private StatusFragment statusFragment = null;

    private LeftMenuFragment leftMenuFragment;

    @ViewInject(R.id.main_drawer)
    private DrawerLayout mDrawerLayout;

    @ViewInject(R.id.btn_message)
    private Button mBtnMessage;
    @ViewInject(R.id.btn_contact)
    private Button mBtnContact;
    @ViewInject(R.id.btn_status)
    private Button mBtnStatus;

    private boolean isInviteIconVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leftMenuFragment = new LeftMenuFragment();
        messageListFragment = new MessageListFragment();
        contactListFragment = new ContactListFragment();
        statusFragment = new StatusFragment();
        fragments = new ChatBaseFragment[]{messageListFragment, contactListFragment, statusFragment};

        getSupportFragmentManager().beginTransaction().add(R.id.content_container, messageListFragment, TAG_MESSAGE)
                .add(R.id.content_container, contactListFragment, TAG_CONTACT)
                .add(R.id.content_container, statusFragment, TAG_STATUS)
                .hide(contactListFragment).hide(statusFragment).show(messageListFragment).commit();

        getSupportFragmentManager().beginTransaction().add(R.id.left_container,leftMenuFragment,TAG_LEFT).commit();

        mBtnContact.setSelected(false);
        mBtnStatus.setSelected(false);
        mBtnMessage.setSelected(true);


    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.qrcode) {
//            showPopup( item.getActionView());
        }  else if (itemId == R.id.add) {
            openActivity(this, AddContactActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onTitleLeftClick() {
        showLeft();
    }

    public void showLeft() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        else
            mDrawerLayout.openDrawer(Gravity.LEFT);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mDrawerLayout.scrollTo((int) slideOffset, 0);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }


    @Event(value = R.id.message_container)
    private void changeToMessage(View view) {

        if (messageListFragment.isVisible())
            return;

        mBtnContact.setSelected(false);
        mBtnStatus.setSelected(false);
        mBtnMessage.setSelected(true);


        if (contactListFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(contactListFragment).show(messageListFragment)
                    .commit();
            return;
        }

        if (statusFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(statusFragment).show(messageListFragment)
                    .commit();
            return;
        }


    }

    @Event(value = R.id.status_container)
    private void changeToStatus(View view) {
        if (statusFragment.isVisible()) {
            return;
        }

        mBtnContact.setSelected(false);
        mBtnStatus.setSelected(true);
        mBtnMessage.setSelected(false);


        if (messageListFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(messageListFragment)
                    .show(statusFragment).commit();
            return;
        }

        if (contactListFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(contactListFragment)
                    .show(statusFragment).commit();
        }
    }

    @Event(value = R.id.contact_container)
    private void changeToContact(View view) {
        if (contactListFragment.isVisible())
            return;


        mBtnContact.setSelected(true);
        mBtnStatus.setSelected(false);
        mBtnMessage.setSelected(false);

        if (messageListFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(messageListFragment)
                    .show(contactListFragment).commit();
            return;
        }

        if (statusFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(statusFragment)
                    .show(contactListFragment).commit();
        }
    }

}
