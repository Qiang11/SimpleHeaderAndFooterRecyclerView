package com.example.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 1013369768 on 2018/10/8.
 */

public class LoadHeaderAdapter extends BaseLoadHeaderAndFooterAdapter {

    private static final int ITEM_HEADER = 0x9528;
    private RecyclerView.Adapter mInnerAdapter;   //不包含头部和尾部的adapter
    private int headerRes;
    private  OnCreateViewHolder createViewHolder;

    public LoadHeaderAdapter(RecyclerView.Adapter mInnerAdapter, int headerRes, OnCreateViewHolder createViewHolder){
        this.mInnerAdapter = mInnerAdapter;
        this.headerRes = headerRes;
        this.createViewHolder = createViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return ITEM_HEADER;
        return mInnerAdapter.getItemViewType(position-1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if (viewType == ITEM_HEADER){
           View view = LayoutInflater.from(parent.getContext()).inflate(headerRes,parent,false);
           return createViewHolder.viewHolder(view);
       }
       return mInnerAdapter.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) return;
        mInnerAdapter.onBindViewHolder(holder,position-1);
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount()+1;
    }

    @Override
    public boolean isFooterOrHeader(int position) {
        return position == 0;
    }

}
