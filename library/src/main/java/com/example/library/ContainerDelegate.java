package com.example.library;

import com.example.library.holder.SugarHolder;

/**
 * Created by 1013369768 on 2018/10/4.
 */

public interface ContainerDelegate{
    Class getDataClass(Class<? extends SugarHolder> cls);

    int getLayoutRes(Class<? extends SugarHolder> cls);
}
