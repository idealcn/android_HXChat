package com.idealcn.hxchat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idealcn.hxchat.R;

/**
 * author:idealgn
 * date:16-10-8 下午4:36
 */
public class ContactItemView extends RelativeLayout{

    private      ImageView mItemVisible;
    public ContactItemView(Context context) {
        this(context,null);
    }

    public ContactItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View content = LayoutInflater.from(context).inflate(R.layout.layout_contact_item,null);
        addView(content);
        TextView mItemText = (TextView) findViewById(R.id.item_text);
        ImageView mItemView = (ImageView) findViewById(R.id.item_view);

        mItemVisible = (ImageView) findViewById(R.id.item_visible);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ContactItemView);
        Drawable view = typedArray.getDrawable(R.styleable.ContactItemView_item_view);
        mItemView.setImageDrawable(view);
        String text = typedArray.getString(R.styleable.ContactItemView_item_text);
        if (!TextUtils.isEmpty(text))
            mItemText.setText(text);
        boolean visible = typedArray.getBoolean(R.styleable.MenuItem_android_visible, false);
        mItemVisible.setVisibility(visible? View.VISIBLE:View.GONE);
        typedArray.recycle();
    }

    public ContactItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public void setNotifyVisible(boolean visible){
        mItemVisible.setVisibility(visible? View.VISIBLE:View.GONE);
    }

}
