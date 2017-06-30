package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.ResponseBase;

/**
 * 作者：czb on 2017/6/30 16:02
 */

public interface IMyFragmentView {


    /**
     * 检查token是否可用之后回调
     */
    void checkTokenBack(ResponseBase responseBase);

}
