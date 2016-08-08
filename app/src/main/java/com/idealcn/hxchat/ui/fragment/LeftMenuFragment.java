package com.idealcn.hxchat.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.idealcn.hxchat.R;
import com.idealcn.hxchat.tools.ActivityStack;
import com.idealcn.hxchat.ui.activity.LoginActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * author:idealcn
 * date:16-8-8 下午3:32
 */

@ContentView(R.layout.fragment_left_menu)
public class LeftMenuFragment extends ChatBaseFragment {

    @ViewInject(R.id.left_menu_listview)
    private ListView mLeftListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private  void initData(){
        mLeftListView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,new String[]{"个人资料","设置","退出登陆"}));


        mLeftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 2:
                        logout();
                        break;

                    default:break;
                }
            }
        });
    }

    private void logout() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                EMClient.getInstance().logout(false, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Log.d("chat","logout success");
                        ActivityStack.getInstance().pullAll();
                        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        }.start();

    }


}
