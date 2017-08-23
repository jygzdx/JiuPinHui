package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.RegisterEntity;

/**
 * 作者：czb on 2017/6/26 17:03
 */

public interface IRegisterActivityView{

    /**
     * 注册成功
     *
     * @param registerEntity 返回实体类
     */
    void registerSuccess(RegisterEntity registerEntity);

    /**
     * 获取验证码成功
     * @param msg 返回信息
     */
    void getSecurityCodeSuccess(String msg);
    /**
     * 手机号码是否注册过
     *
     * @param data 手机号码是否注册过：0->注册过   1->没注册过
     */
    void isMobileUnique(String data);


}
