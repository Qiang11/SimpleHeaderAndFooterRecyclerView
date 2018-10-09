package com.example.sampleheaderandfooterrecyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 1013369768 on 2018/10/9.
 */

@SuppressLint("AppCompatCustomView")
public class SquareImage extends ImageView {
    public SquareImage(Context context) {
        this(context,null);
    }

    public SquareImage(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SquareImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }
}
