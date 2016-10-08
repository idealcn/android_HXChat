package com.idealcn.hxchat.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.hyphenate.chat.EMContact;

/**
 * author:idealgn
 * date:16-10-8 下午2:53
 */
public class User extends EMContact{

    private String avatar;
    private String initialLetter;

    public String getInitialLetter() {
        return initialLetter;
    }

    public void setInitialLetter(String initialLetter) {
        this.initialLetter = initialLetter;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
