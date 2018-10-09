package com.example.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by 1013369768 on 2018/9/29.
 */

public class LoadFooterAdapter extends BaseLoadHeaderAndFooterAdapter {

    private static final int ITEM_FOOTER = 0x9527;
    private RecyclerView.Adapter mInnerAdapter;   //不包含头部和尾部的adapter
    private View view;

    public LoadFooterAdapter(View view, RecyclerView.Adapter adapter){
        mInnerAdapter = adapter;
        this.view = view;
    }

    @Override
    public int getItemViewType(int position) {
        // 如果位置和不含有头部足部的Adapter相同，则是最后一个Item
        if (position == mInnerAdapter.getItemCount()) return ITEM_FOOTER;
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_FOOTER){
            return new FooterHolder(view);
        }
        return mInnerAdapter.createViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == mInnerAdapter.getItemCount()) return;
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount()+1;
    }

    @Override
    public boolean isFooterOrHeader(int position) {
        return position == mInnerAdapter.getItemCount();
    }

    class FooterHolder extends RecyclerView.ViewHolder{

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }

}
