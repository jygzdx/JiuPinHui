package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.ResponseBase;

/**
 * 作者：czb on 2017/6/26 17:03
 */

public interface IResetActivityView{

    /**
     * 重置密码成功
     *
     * @param responseBase 返回实体类
     */
    void resetPwdSuccess(ResponseBase responseBase);

    /**
     * 获取验证码成功
     * @param msg 返回信息
     */
    void getSecurityCodeSuccess(String msg);
}
