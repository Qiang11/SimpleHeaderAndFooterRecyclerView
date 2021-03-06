package com.example.sampleheaderandfooterrecyclerview.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.library.BaseRecyclerView;
import com.example.library.ContainerDelegateImpl;
import com.example.library.adapter.SugarAdapter;
import com.example.library.holder.SugarHolder;
import com.example.sampleheaderandfooterrecyclerview.R;
import com.example.sampleheaderandfooterrecyclerview.bean.BaseMessage;
import com.example.sampleheaderandfooterrecyclerview.bean.OtherTextMessage;
import com.example.sampleheaderandfooterrecyclerview.bean.UserTextMessage;
import com.example.sampleheaderandfooterrecyclerview.holder.HeaderHolder;
import com.example.sampleheaderandfooterrecyclerview.holder.OtherTextMessageHolder;
import com.example.sampleheaderandfooterrecyclerview.holder.UserTextMessageHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderActivity extends AppCompatActivity implements
        UserTextMessageHolder.UserTextMessageDelegate,
        OtherTextMessageHolder.OtherTextMessageDelegate,BaseRecyclerView.HeaderViewHolder{

    private List<BaseMessage> list = new ArrayList<>();
    private BaseRecyclerView mRv;
    private Map<Class<? extends SugarHolder>, Class> mDataClassMap = new HashMap<>();
    private Map<Class<? extends SugarHolder>, Integer> mLayoutResMap = new HashMap<>();
    private SugarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        mRv = findViewById(R.id.rv);
        mRv.setHeaderViewHolder(this);
        setData();
        setImpl();
        setRv();
    }

    private void setData(){
        for (int i = 0;i<20;i++){
            UserTextMessage userTextMessage = new UserTextMessage();
            userTextMessage.setUserTextMessage("Hello"+i);
            OtherTextMessage otherTextMessage = new OtherTextMessage();
            otherTextMessage.setOtherMessage("world"+i);
            list.add(userTextMessage);
            list.add(otherTextMessage);
        }
    }


    private void setImpl(){
        mDataClassMap.put(UserTextMessageHolder.class,UserTextMessage.class);
        mLayoutResMap.put(UserTextMessageHolder.class,R.layout.user_text_messge);

        mDataClassMap.put(OtherTextMessageHolder.class,OtherTextMessage.class);
        mLayoutResMap.put(OtherTextMessageHolder.class,R.layout.other_text_message);
    }


    private void setRv(){
        adapter = SugarAdapter
                .Builder
                .with(list)
                .loadImpl(new ContainerDelegateImpl(mDataClassMap,mLayoutResMap))
                .add(UserTextMessageHolder.class, new SugarHolder.CreatedHolderCallback<UserTextMessageHolder>() {
                    @Override
                    public void onHolderCallback(UserTextMessageHolder holder) {
                        holder.setUserTextMessageDelegate(HeaderActivity.this);
                    }
                })
                .add(OtherTextMessageHolder.class, new SugarHolder.CreatedHolderCallback<OtherTextMessageHolder>() {
                    @Override
                    public void onHolderCallback(OtherTextMessageHolder holder) {
                        holder.setOtherTextMessageDelegate(HeaderActivity.this);
                    }
                })
                .builder();
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(adapter);
    }

    @Override
    public void UserTextMessage(UserTextMessage userTextMessage,int position) {
        Toast.makeText(HeaderActivity.this,userTextMessage.getUserTextMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void OtherTextMessage(OtherTextMessage otherTextMessage,int position){
        Toast.makeText(HeaderActivity.this,otherTextMessage.getOtherMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public RecyclerView.ViewHolder createHeaderViewHolder(View view){
        HeaderHolder headerHolder = new HeaderHolder(view);
        headerHolder.setData("头部布局");
        return headerHolder;
    }
}
