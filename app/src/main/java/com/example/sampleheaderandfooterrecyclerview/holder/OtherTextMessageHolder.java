package com.example.sampleheaderandfooterrecyclerview.holder;

import android.view.View;
import android.widget.TextView;

import com.example.library.holder.InjectDelegate;
import com.example.library.holder.SugarHolder;
import com.example.sampleheaderandfooterrecyclerview.R;
import com.example.sampleheaderandfooterrecyclerview.bean.OtherTextMessage;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 1013369768 on 2018/10/7.
 */

public class OtherTextMessageHolder extends SugarHolder<OtherTextMessage>{

    private TextView otherMesage;
    private CircleImageView otherHead;
    private OtherTextMessageDelegate otherTextMessageDelegate;


    public OtherTextMessageHolder(View view) {
        super(view);
    }

    public interface OtherTextMessageDelegate{
        void OtherTextMessage(OtherTextMessage otherTextMessage,int position);
    }

    public static class InjectDelegateImpl implements InjectDelegate {

        public InjectDelegateImpl(){}

        @Override
        public <sh extends SugarHolder> void inJectView(sh holder, View view) {
            if (holder instanceof OtherTextMessageHolder){
                OtherTextMessageHolder userTextMessageHolder = (OtherTextMessageHolder) holder;
                userTextMessageHolder.otherHead = view.findViewById(R.id.other_head);
                userTextMessageHolder.otherMesage = view.findViewById(R.id.other_message);
            }
        }
    }


    public void setOtherTextMessageDelegate(OtherTextMessageDelegate otherTextMessageDelegate){
        this.otherTextMessageDelegate = otherTextMessageDelegate;
    }


    @Override
    public void onBindData(final OtherTextMessage otherTextMessage, final int position) {
        otherMesage.setText(otherTextMessage.getOtherMessage());
        otherMesage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherTextMessageDelegate.OtherTextMessage(otherTextMessage,position);
            }
        });
    }
}
