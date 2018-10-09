package com.example.sampleheaderandfooterrecyclerview.activitys;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.widget.Toast;

import com.example.library.BaseRecyclerView;
import com.example.library.ContainerDelegateImpl;
import com.example.library.adapter.SugarAdapter;
import com.example.library.holder.SugarHolder;
import com.example.sampleheaderandfooterrecyclerview.R;
import com.example.sampleheaderandfooterrecyclerview.bean.BaseMessage;
import com.example.sampleheaderandfooterrecyclerview.bean.OtherTextMessage;
import com.example.sampleheaderandfooterrecyclerview.bean.UserTextMessage;
import com.example.sampleheaderandfooterrecyclerview.holder.OtherTextMessageHolder;
import com.example.sampleheaderandfooterrecyclerview.holder.UserTextMessageHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadMoreActivity extends AppCompatActivity implements
        UserTextMessageHolder.UserTextMessageDelegate,OtherTextMessageHolder.OtherTextMessageDelegate,
        BaseRecyclerView.LoadMoreListener{


    private List<BaseMessage> list = new ArrayList<>();
    private BaseRecyclerView mRv;
    private Map<Class<? extends SugarHolder>, Class> mDataClassMap = new HashMap<>();
    private Map<Class<? extends SugarHolder>, Integer> mLayoutResMap = new HashMap<>();
    private RecyclerView.Adapter adapter;
    private Handler handler = new Handler();
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);
        mRv = findViewById(R.id.rv);
        mRv.setLoadMoreListener(this);
        setData();
        setImpl();
        setRv();
    }

    private List<BaseMessage> setData(){
        count++;
        List<BaseMessage> list = new ArrayList<>();
        for (int i = 0;i<20;i++){
            UserTextMessage userTextMessage = new UserTextMessage();
            userTextMessage.setUserTextMessage("Hello"+i);
            OtherTextMessage otherTextMessage = new OtherTextMessage();
            otherTextMessage.setOtherMessage("world"+i);
            list.add(userTextMessage);
            list.add(otherTextMessage);
        }
        return list;
    }


    private void setImpl(){
        mDataClassMap.put(UserTextMessageHolder.class,UserTextMessage.class);
        mLayoutResMap.put(UserTextMessageHolder.class,R.layout.user_text_messge);

        mDataClassMap.put(OtherTextMessageHolder.class,OtherTextMessage.class);
        mLayoutResMap.put(OtherTextMessageHolder.class,R.layout.other_text_message);
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
                .add(UserTextMessageHolder.class, new SugarHolder.CreatedHolderCallback<UserTextMessageHolder>() {
                    @Override
                    public void onHolderCallback(UserTextMessageHolder holder) {
                        holder.setUserTextMessageDelegate(LoadMoreActivity.this);
                    }
                })
                .add(OtherTextMessageHolder.class, new SugarHolder.CreatedHolderCallback<OtherTextMessageHolder>() {
                    @Override
                    public void onHolderCallback(OtherTextMessageHolder holder) {
                        holder.setOtherTextMessageDelegate(LoadMoreActivity.this);
                    }
                })
                .builder();
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(adapter);
        adapter = mRv.getAdapter();
    }

    @Override
    public void UserTextMessage(UserTextMessage userTextMessage,int position) {
        Toast.makeText(LoadMoreActivity.this,userTextMessage.getUserTextMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void OtherTextMessage(OtherTextMessage otherTextMessage,int position){
        Toast.makeText(LoadMoreActivity.this,otherTextMessage.getOtherMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(count == 3){
                    mRv.loadMoreComplete(true);
                }else {
                    mRv.loadMoreComplete(false);
                }

                List<BaseMessage> mList = setData();
                int index = list.size();
                list.addAll(mList);
                adapter.notifyItemRangeInserted(index,mList.size());
            }
        },1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
