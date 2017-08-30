package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IBindingPhoneByThirdActivityPresenter {

    /**
     * 获取手机验证码
     * @param mobile
     */
    void getSecurityCode(String mobile);

    /**
     * 修改手机号码
     * @param token token
     * @param moblie 手机号码
     * @param sms 验证码
     */
    void updateMobile(String token, String moblie, String sms);
}
