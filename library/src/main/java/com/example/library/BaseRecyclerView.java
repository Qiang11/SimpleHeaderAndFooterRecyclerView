package com.example.library;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.example.library.adapter.HeaderAndFooterAdapter;
import com.example.library.adapter.LoadHeaderAdapter;
import com.example.library.adapter.LoadFooterAdapter;
import com.example.library.adapter.OnCreateViewHolder;

/**
 * Created by 1013369768 on 2018/9/29.
 */

public class BaseRecyclerView extends RecyclerView implements RecyclerDataObserver.DataUpdate,OnCreateViewHolder {

    private boolean isNeedHeader,isNeedLoadMore;
    private int footResId,headerResId,endFootResId;
    public FootView footView;
    // 是否正在加载数据
    private boolean isLoadingData = false;
    //判断加载更多时是否还有数据
    private boolean dataDone;
    private Adapter adapter;
    //加载更过监听
    private LoadMoreListener loadMoreListener;
    //添加头部ViewHolder
    private HeaderViewHolder headerViewHolder;

    public BaseRecyclerView(Context context) {
        this(context,null);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.BaseRecyclerView);
        try {
            isNeedHeader = a.getBoolean(R.styleable.BaseRecyclerView_isNeedHeader,false);
            isNeedLoadMore = a.getBoolean(R.styleable.BaseRecyclerView_isNeedLoadMore,false);
            footResId = a.getResourceId(R.styleable.BaseRecyclerView_footResId,R.layout.footer_defvalue_layout);
            headerResId = a.getResourceId(R.styleable.BaseRecyclerView_headerResId,0);
            endFootResId = a.getResourceId(R.styleable.BaseRecyclerView_endFootResId,0);
        }finally {
            a.recycle();
        }
    }



    @Override
    public void setAdapter(Adapter adapter) {
        String typeAdapter = String.valueOf(isNeedHeader)+"_"+String.valueOf(isNeedLoadMore);
        switch (typeAdapter){
            case "true_true":
                if (headerResId == 0){
                    throw new RuntimeException("Can't find layoutRes for headerLayout");
                }
                footView = new FootView(getContext());
                footView.setFoot(footResId);
                adapter = new HeaderAndFooterAdapter(adapter,footView,headerResId,this);
                adapter.registerAdapterDataObserver(new RecyclerDataObserver(this));
                break;
            case "true_false":
                if (headerResId == 0){
                    throw new RuntimeException("Can't find layoutRes for headerLayout");
                }
                adapter = new LoadHeaderAdapter(adapter,headerResId,this);
                break;
            case "false_true":
                footView = new FootView(getContext());
                footView.setFoot(footResId);
                adapter = new LoadFooterAdapter(footView,adapter);
                adapter.registerAdapterDataObserver(new RecyclerDataObserver(this));
                break;
            case "false_false":
                break;
        }
        this.adapter = adapter;
        super.setAdapter(this.adapter);
    }


    /**
     *  加载更多判断
     */
    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE && loadMoreListener != null && !isLoadingData && isNeedLoadMore) {
            int lastVisibleItemPosition;
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                lastVisibleItemPosition = last(into);
            } else {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }

            if (layoutManager.getChildCount() > 0
                    && lastVisibleItemPosition >= layoutManager.getItemCount() - 1) {
                if (footView!= null) {
                    footView.setViewVisibility();
                }
                isLoadingData = true;
                loadMoreListener.onLoadMore();
            }
        }
    }


    //取到最后的一个节点
    private int last(int[] lastPositions) {
        int last = lastPositions[0];
        for (int value : lastPositions) {
            if (value > last) {
                last = value;
            }
        }
        return last;
    }


    public void setLoadMoreListener(LoadMoreListener loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }

    /**
     * 设置添加头部
     * @param headerViewHolder
     */
    public void setHeaderViewHolder(HeaderViewHolder headerViewHolder){
        this.headerViewHolder = headerViewHolder;
    }

    /**
     * 获取adapter
     * @return
     */
    public Adapter getAdapter(){
        return adapter;
    }

    /**
     * 加载更多，加载完成
     */
    public void loadMoreComplete(boolean dataDone){
        this.dataDone = dataDone;
        if (footView!=null){
            footView.setViewGone();
        }
    }

    /**
     *加载更多时，如果还有数据则显示足部，没有则隐藏足部
     */
    @Override
    public void loadingMore(){
        if (!dataDone){
            footView.setViewVisibility();
            isLoadingData = false;
        }else {
            if (endFootResId != 0){
                footView.setViewVisibility();
                footView.setEnd(endFootResId);
            }
        }
    }


    @Override
    public ViewHolder viewHolder(View view) {
        return headerViewHolder.createHeaderViewHolder(view);
    }


    public interface LoadMoreListener {
        void onLoadMore();

    }

    public interface HeaderViewHolder{
        ViewHolder createHeaderViewHolder(View view);
    }

}
