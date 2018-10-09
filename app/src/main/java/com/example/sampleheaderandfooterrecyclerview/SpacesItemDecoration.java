package com.example.sampleheaderandfooterrecyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 1013369768 on 2018/10/9.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration{

    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private boolean isHasHeader;

    public SpacesItemDecoration(int spanCount, int spacing, boolean includeEdge,boolean isHasHeader) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.isHasHeader = isHasHeader;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = 0;
        if (isHasHeader){
            position = parent.getChildAdapterPosition(view)-1; // item position
            if (position < 0) return;
        }else {
            position = parent.getChildAdapterPosition(view); // item position
        }
        int column = position % spanCount; // item column
        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
        }
        if (position >= spanCount) { // top edge
            outRect.top = spacing;
        }
    }
}
