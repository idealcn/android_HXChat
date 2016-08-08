package com.idealcn.hxchat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.idealcn.hxchat.R;

/**
 * author:idealcn
 * date:16-8-8 上午10:59
 */
public class DeleteInputView  extends RelativeLayout {

    public DeleteInputView(Context context) {
        super(context);
        init(context);
    }

    public DeleteInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DeleteInputView);
        String hint = ta.getString(R.styleable.DeleteInputView_delete_hint);
        BitmapDrawable drawable = (BitmapDrawable) ta.getDrawable(R.styleable.DeleteInputView_background_delete_button);
       String text_delete_button = ta.getString(R.styleable.DeleteInputView_text_delete_button);
        mDeleteInput.setHint(hint);
        if (drawable!=null)
        mButtonDelete.setBackgroundDrawable(drawable);
        if (!TextUtils.isEmpty(text_delete_button)) {
            mButtonDelete.setBackgroundColor(Color.parseColor("#FF2D43BC"));
            mButtonDelete.setText(text_delete_button);
        }
        ta.recycle();
    }

    public DeleteInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private EditText mDeleteInput;
    private Button mButtonDelete;


    private void init(Context context){
        View content = LayoutInflater.from(context).inflate(R.layout.layout_delete,null);
        mDeleteInput = (EditText) content.findViewById(R.id.delete_input);
        mButtonDelete = (Button) content.findViewById(R.id.delete_button);


        mDeleteInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)){
                    mButtonDelete.setVisibility(View.VISIBLE);
                    if (listener!=null)listener.change(s);
                    mButtonDelete.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (btnListener!=null) {
                                Log.d("chat","--------------");
                                btnListener.onDeleteButtonClick(v);
                            }
                        }
                    });
                }else {
                    mButtonDelete.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(content,params);
    }



    public String getInputText(){
        return mDeleteInput.getText().toString().trim();
    }



    private OnInputTextChangeListener listener;
    public interface OnInputTextChangeListener{
        void change(CharSequence s);
    }

    private OnDeleteButtonClickListener btnListener;
    public interface OnDeleteButtonClickListener{
        void onDeleteButtonClick(View view);
    }

    public void setOnDeleteButtonClickListener(OnDeleteButtonClickListener l){
        this.btnListener = l;
    }
}
