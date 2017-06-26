package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface ILoginActivityPresenter {

    /**
     * mobile:手机号码
     * 获取美酒详细数据
     */
    void getSecurityCode(String mobile);

    /**
     * 用户注册
     * @param mobile 电话号码
     * @param code 验证码
     * @param pwd 密码
     */
    void registerUser(String mobile,String code,String pwd);
}
