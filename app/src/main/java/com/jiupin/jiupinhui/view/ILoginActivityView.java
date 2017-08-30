package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.RegisterEntity;

/**
 * 作者：czb on 2017/6/26 17:03
 */

public interface ILoginActivityView{

    /**
     * 登录成功
     *
     * @param registerEntity 返回实体类
     */
    void loginSuccess(RegisterEntity registerEntity);
    /**
     * 微信登录成功
     *
     * @param registerEntity 返回实体类
     */
    void wxLoginBack(RegisterEntity registerEntity);
}
