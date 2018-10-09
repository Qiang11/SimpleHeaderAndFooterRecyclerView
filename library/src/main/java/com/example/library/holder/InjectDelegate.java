package com.example.library.holder;

import android.view.View;

/**
 * Created by 1013369768 on 2018/10/4.
 */

public interface InjectDelegate{
    <sh extends SugarHolder> void inJectView(sh holder,View view);
}
