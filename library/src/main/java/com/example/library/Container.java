package com.example.library;

import com.example.library.holder.SugarHolder;

/**
 * Created by 1013369768 on 2018/10/4.
 */

public class Container{
    private SugarHolder.CreatedHolderCallback mCallback;
    private Class<? extends SugarHolder> mHolderClass;
    private int mLayoutRes;

    public Container(int layoutRes, Class<? extends SugarHolder> holderClass, SugarHolder.CreatedHolderCallback callback) {
        this.mLayoutRes = layoutRes;
        this.mHolderClass = holderClass;
        this.mCallback = callback;
    }

    public int getLayoutRes() {
        return this.mLayoutRes;
    }

    public Class<? extends SugarHolder> getHolderClass() {
        return this.mHolderClass;
    }

    public SugarHolder.CreatedHolderCallback getCallback() {
        return this.mCallback;
    }
}
