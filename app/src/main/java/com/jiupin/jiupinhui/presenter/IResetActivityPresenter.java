package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IResetActivityPresenter {

    /**
     * mobile:手机号码
     * 获取重置密码验证码
     */
    void getResetSecurityCode(String mobile);
    /**
     * 用户重置密码
     * @param mobile 电话号码
     * @param code 验证码
     * @param pwd 密码
     */
    void resetPwd(String mobile, String code, String pwd);
}
