package com.example.sampleheaderandfooterrecyclerview.holder;

import android.view.View;
import android.widget.ImageView;

import com.example.library.holder.InjectDelegate;
import com.example.library.holder.SugarHolder;
import com.example.sampleheaderandfooterrecyclerview.R;
import com.example.sampleheaderandfooterrecyclerview.SquareImage;
import com.example.sampleheaderandfooterrecyclerview.bean.ImageData;

/**
 * Created by 1013369768 on 2018/10/8.
 */

public class ImageHolder extends SugarHolder<ImageData>{

    private SquareImage imageView;
    private ImageDelegate imageDelegate;

    public ImageHolder(View view) {
        super(view);
    }


    public interface ImageDelegate{
        void image(ImageData imageData, int position);
    }

    public static class InjectDelegateImpl implements InjectDelegate {

        public InjectDelegateImpl(){}

        @Override
        public <sh extends SugarHolder> void inJectView(sh holder, View view) {
            if (holder instanceof ImageHolder){
                ImageHolder imageHolder = (ImageHolder) holder;
                imageHolder.imageView = view.findViewById(R.id.image);
            }
        }
    }


    public void setImageDelegate(ImageDelegate imageDelegate){
        this.imageDelegate = imageDelegate;
    }


    @Override
    public void onBindData(final ImageData imageData, final int position) {
        imageView.setImageResource(imageData.getImageUrl());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDelegate.image(imageData,position);
            }
        });
    }
}
