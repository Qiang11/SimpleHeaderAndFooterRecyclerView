package com.example.library;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 1013369768 on 2018/10/8.
 */

public class RecyclerDataObserver extends RecyclerView.AdapterDataObserver{

    private DataUpdate dataUpdate;

    public RecyclerDataObserver(DataUpdate dataUpdate){
        this.dataUpdate = dataUpdate;
    }

    @Override
    public void onChanged() {
        super.onChanged();
        update();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
        super.onItemRangeChanged(positionStart, itemCount);
        update();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        super.onItemRangeChanged(positionStart, itemCount, payload);
        update();
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        super.onItemRangeInserted(positionStart, itemCount);
        update();
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        super.onItemRangeRemoved(positionStart, itemCount);
        update();
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
        update();
    }


    private void update() {
        dataUpdate.loadingMore();
    }

    public interface DataUpdate{
        void loadingMore();
    }
}
