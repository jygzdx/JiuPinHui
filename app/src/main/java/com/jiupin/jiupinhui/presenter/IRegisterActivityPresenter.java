package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IRegisterActivityPresenter {

    /**
     * mobile:手机号码
     * 获取注册验证码
     */
    void getSecurityCode(String mobile);

    /**
     * 用户注册
     * @param mobile 电话号码
     * @param code 验证码
     * @param pwd 密码
     */
    void registerUser(String mobile, String code, String pwd);

    /**
     * 校验手机号码是否注册过
     * @param mobile 电话号码
     */
    void isMobileUnique(String mobile);
}
