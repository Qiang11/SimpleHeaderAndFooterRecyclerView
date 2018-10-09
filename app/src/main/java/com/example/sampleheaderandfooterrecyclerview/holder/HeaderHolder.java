package com.example.sampleheaderandfooterrecyclerview.holder;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sampleheaderandfooterrecyclerview.R;
/**
 * Created by 1013369768 on 2018/10/8.
 */

public class HeaderHolder extends RecyclerView.ViewHolder{

    private ImageView imageView;
    private TextView textView;
    private Context context;

    public HeaderHolder(final View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.tv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof Activity){
                    Activity activity = (Activity) context;
                    activity.finish();
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData(String str){
        textView.setText(str);
    }
}
