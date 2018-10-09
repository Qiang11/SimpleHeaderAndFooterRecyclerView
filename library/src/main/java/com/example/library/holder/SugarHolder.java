package com.example.library.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by 1013369768 on 2018/10/4.
 */

public abstract class SugarHolder<T> extends RecyclerView.ViewHolder{

    private Context mContext;

    public interface CreatedHolderCallback<sh extends SugarHolder>{
        void onHolderCallback(sh holder);
    }

    public SugarHolder(View view) {
        super(view);
        mContext = view.getContext();
        InjectDelegate injectDelegate = getInjectDelegate();
        if (injectDelegate !=null){
            injectDelegate.inJectView(this,view);
        }
    }

    public abstract void onBindData(T t,int position);

    public Context getContext(){
        return mContext;
    }

    public View getRootView(){
        return this.itemView;
    }

    private InjectDelegate getInjectDelegate(){
        try {
            InjectDelegate injectDelegate = (InjectDelegate)
                    Class.forName(this.getClass().getCanonicalName()+"$InjectDelegateImpl").newInstance();
            return injectDelegate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
