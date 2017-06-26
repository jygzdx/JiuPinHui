package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.RegisterEntity;

/**
 * 作者：czb on 2017/6/26 17:03
 */

public interface ILoginActivityView {

    /**
     * 注册成功
     *
     * @param registerEntity 返回实体类
     */
    void registerSuccess(RegisterEntity registerEntity);

    /**
     * 注册失败
     */
    void registerFail();
}
