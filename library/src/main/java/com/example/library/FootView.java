package com.example.library;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * Created by 1013369768 on 2018/9/29.
 */

public class FootView extends FrameLayout{

    private View view;
    private Context context;
    private View endView;

    public FootView(@NonNull Context context) {
        this(context,null);
    }

    public FootView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public FootView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    public void setFoot(int resId){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(resId,null);
        addView(view);
    }

    public void setEnd(int endResId){
        removeAllViews();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        endView = layoutInflater.inflate(endResId,null);
        addView(endView);
    }

    public void setViewGone(){
        setVisibility(GONE);
    }

    public void setViewVisibility(){
        setVisibility(VISIBLE);
    }

}
