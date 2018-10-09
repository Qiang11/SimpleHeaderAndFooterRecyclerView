package com.example.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 1013369768 on 2018/10/9.
 */

public class HeaderAndFooterAdapter extends BaseLoadHeaderAndFooterAdapter {

    private static final int ITEM_FOOTER = 0x9527;
    private static final int ITEM_HEADER = 0x9528;
    private RecyclerView.Adapter mInnerAdapter;   //不包含头部和尾部的adapter
    private View view;
    private int headerRes;
    private OnCreateViewHolder createViewHolder;


    public HeaderAndFooterAdapter(RecyclerView.Adapter mInnerAdapter,View view,int headerRes,OnCreateViewHolder createViewHolder){
        this.mInnerAdapter = mInnerAdapter;
        this.view = view;
        this.headerRes = headerRes;
        this.createViewHolder = createViewHolder;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ITEM_HEADER;
        }else if (position == mInnerAdapter.getItemCount()+1){
            return ITEM_FOOTER;
        }else {
            return mInnerAdapter.getItemViewType(position-1);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEADER){
            View view = LayoutInflater.from(parent.getContext()).inflate(headerRes,parent,false);
            return createViewHolder.viewHolder(view);
        }else if (viewType == ITEM_FOOTER){
            return new FooterHolder(view);
        }else {
            return mInnerAdapter.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0 || position == mInnerAdapter.getItemCount()+1) return;
        mInnerAdapter.onBindViewHolder(holder,position-1);
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount()+2;
    }

    @Override
    public boolean isFooterOrHeader(int position) {
        return position == 0 || position == mInnerAdapter.getItemCount()+1;
    }


    class FooterHolder extends RecyclerView.ViewHolder{

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
}
