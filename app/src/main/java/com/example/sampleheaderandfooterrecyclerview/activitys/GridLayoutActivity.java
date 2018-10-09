package com.example.sampleheaderandfooterrecyclerview.activitys;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.library.BaseRecyclerView;
import com.example.library.ContainerDelegateImpl;
import com.example.library.adapter.SugarAdapter;
import com.example.library.holder.SugarHolder;
import com.example.sampleheaderandfooterrecyclerview.R;
import com.example.sampleheaderandfooterrecyclerview.SpacesItemDecoration;
import com.example.sampleheaderandfooterrecyclerview.bean.ImageData;
import com.example.sampleheaderandfooterrecyclerview.holder.HeaderHolder;
import com.example.sampleheaderandfooterrecyclerview.holder.ImageHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridLayoutActivity extends AppCompatActivity implements BaseRecyclerView.HeaderViewHolder,
        BaseRecyclerView.LoadMoreListener,ImageHolder.ImageDelegate{

    private List<ImageData> list = new ArrayList<>();
    private BaseRecyclerView mRv;
    private Map<Class<? extends SugarHolder>, Class> mDataClassMap = new HashMap<>();
    private Map<Class<? extends SugarHolder>, Integer> mLayoutResMap = new HashMap<>();
    private RecyclerView.Adapter adapter;
    private Handler handler = new Handler();
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        mRv = findViewById(R.id.rv);
        mRv.setLoadMoreListener(this);
        mRv.setHeaderViewHolder(this);
        setData();
        setImpl();
        setRv();
    }



    private List<ImageData> setData(){
        count++;
        List<ImageData> list = new ArrayList<>();
        for (int i = 0;i<20;i++){
            ImageData imageData = new ImageData();
            imageData.setImageUrl(R.drawable.timg);
            list.add(imageData);
        }
        return list;
    }


    private void setImpl(){
        mDataClassMap.put(ImageHolder.class,ImageData.class);
        mLayoutResMap.put(ImageHolder.class,R.layout.image_grid_item);
    }


    private void setRv(){
        list.addAll(setData());

        //设置item改变时动画时长为0，防止产生动画
        mRv.getItemAnimator().setRemoveDuration(0);
        mRv.getItemAnimator().setAddDuration(0);
        mRv.getItemAnimator().setChangeDuration(0);
        mRv.getItemAnimator().setMoveDuration(0);

        adapter = SugarAdapter
                .Builder
                .with(list)
                .loadImpl(new ContainerDelegateImpl(mDataClassMap,mLayoutResMap))
                .add(ImageHolder.class, new SugarHolder.CreatedHolderCallback<ImageHolder>() {
                    @Override
                    public void onHolderCallback(ImageHolder holder) {
                        holder.setImageDelegate(GridLayoutActivity.this);
                    }
                })
                .builder();
        mRv.setLayoutManager(new GridLayoutManager(GridLayoutActivity.this,2));
        mRv.addItemDecoration(new SpacesItemDecoration(2,20,true,true));
        mRv.setAdapter(adapter);
        adapter = mRv.getAdapter();
    }


    @Override
    public void image(ImageData imageData, int position) {
        Toast.makeText(GridLayoutActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(count == 2){
                    mRv.loadMoreComplete(true);
                }else {
                    mRv.loadMoreComplete(false);
                }

                List<ImageData> mList = setData();
                int index = list.size();
                list.addAll(mList);
                adapter.notifyItemRangeInserted(index,mList.size());
            }
        },1000);
    }

    @Override
    public RecyclerView.ViewHolder createHeaderViewHolder(View view) {
        HeaderHolder headerHolder = new HeaderHolder(view);
        headerHolder.setData("头部和底部布局");
        return headerHolder;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
