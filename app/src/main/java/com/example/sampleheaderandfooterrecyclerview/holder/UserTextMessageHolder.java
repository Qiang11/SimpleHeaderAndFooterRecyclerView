package com.example.sampleheaderandfooterrecyclerview.holder;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.example.library.holder.InjectDelegate;
import com.example.library.holder.SugarHolder;
import com.example.sampleheaderandfooterrecyclerview.R;
import com.example.sampleheaderandfooterrecyclerview.bean.BaseMessage;
import com.example.sampleheaderandfooterrecyclerview.bean.UserTextMessage;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 1013369768 on 2018/10/6.
 */

public class UserTextMessageHolder extends SugarHolder<UserTextMessage>{

    private  CircleImageView headImageImageView;
    private TextView textView;
    private UserTextMessageDelegate userTextMessageDelegate;

    public UserTextMessageHolder(View view){
        super(view);
    }

    public interface UserTextMessageDelegate{
        void UserTextMessage(UserTextMessage userTextMessage,int position);
    }

    public static class InjectDelegateImpl implements InjectDelegate{

        public InjectDelegateImpl(){}

        @Override
        public <sh extends SugarHolder> void inJectView(sh holder, View view) {
            if (holder instanceof UserTextMessageHolder){
                UserTextMessageHolder userTextMessageHolder = (UserTextMessageHolder) holder;
                userTextMessageHolder.headImageImageView = view.findViewById(R.id.user_head);
                userTextMessageHolder.textView = view.findViewById(R.id.user_message);
            }
        }
    }

    public void setUserTextMessageDelegate(UserTextMessageDelegate userTextMessageDelegate){
        this.userTextMessageDelegate = userTextMessageDelegate;
    }


    @Override
    public void onBindData(final UserTextMessage userTextMessage, final int position) {
        textView.setText(userTextMessage.getUserTextMessage());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTextMessageDelegate.UserTextMessage(userTextMessage,position);
            }
        });

    }

}
