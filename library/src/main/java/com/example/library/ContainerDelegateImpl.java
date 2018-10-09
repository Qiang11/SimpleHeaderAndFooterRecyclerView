package com.example.library;

import com.example.library.holder.SugarHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1013369768 on 2018/10/4.
 */

public class ContainerDelegateImpl implements ContainerDelegate{

    private Map<Class<? extends SugarHolder>, Class> mDataClassMap;
    private Map<Class<? extends SugarHolder>, Integer> mLayoutResMap;

    public ContainerDelegateImpl(Map<Class<? extends SugarHolder>, Class> mDataClassMap,Map<Class<? extends SugarHolder>, Integer> mLayoutResMap){
        this.mDataClassMap = mDataClassMap;
        this.mLayoutResMap = mLayoutResMap;
    }

    @Override
    public Class getDataClass(Class<? extends SugarHolder> cls) {
        if (mDataClassMap.containsKey(cls)){
            return mDataClassMap.get(cls);
        }
        return null;
    }

    @Override
    public int getLayoutRes(Class<? extends SugarHolder> cls) {
        if (mLayoutResMap.containsKey(cls)){
            return mLayoutResMap.get(cls);
        }
        return 0;
    }
}
