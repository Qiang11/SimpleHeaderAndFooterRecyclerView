package com.example.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.Container;
import com.example.library.ContainerDelegateImpl;
import com.example.library.holder.SugarHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1013369768 on 2018/10/4.
 */

public class SugarAdapter extends RecyclerView.Adapter<SugarHolder> {

    private List<?> mList;
    private Map<Class,Container> map;
    private Container container;

    public static final class Builder{
        private List<?> mList;
        private ContainerDelegateImpl mImpl;
        private Map<Class,Container> map;

        public static Builder with(List<?> mList){
            return new Builder(mList);
        }

        public Builder(List<?> mList){
            this.mList = mList;
            map = new HashMap<>();
        }

        public Builder loadImpl(ContainerDelegateImpl mImpl){
            this.mImpl = mImpl;
            return this;
        }

        public <sh extends SugarHolder> Builder add(Class<sh> holder, SugarHolder.CreatedHolderCallback<sh> callback){
             Class dataClass = mImpl.getDataClass(holder);
             int layoutRes = mImpl.getLayoutRes(holder);
             this.map.put(dataClass,new Container(layoutRes,holder,callback));
             return this;
        }

        public SugarAdapter builder(){
            return new SugarAdapter(mList,map);
        }
    }


    private SugarAdapter(List<?> mList,Map<Class,Container> map){
        this.mList = mList;
        this.map = map;
    }


    @Override
    public int getItemViewType(int position) {
        Class dataClass = mList.get(position).getClass();
        if (map.containsKey(dataClass)){
            container = map.get(dataClass);
            int layoutRes = container.getLayoutRes();
            return layoutRes;
        }else {
            throw new RuntimeException("Can't find layoutRes for " + dataClass.getSimpleName());
        }
    }

    @Override
    public SugarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
            SugarHolder holder = container.getHolderClass()
                    .getDeclaredConstructor(View.class)
                    .newInstance(view);
            SugarHolder.CreatedHolderCallback callback = container.getCallback();
            if (callback!=null){
                callback.onHolderCallback(holder);
            }
            return holder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(SugarHolder holder, int position) {
        holder.onBindData(mList.get(position),position);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
}
