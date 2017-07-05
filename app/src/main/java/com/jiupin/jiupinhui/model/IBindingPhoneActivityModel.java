package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IBindingPhoneActivityModel {
    /**
     * 获取验证码
     * @param mobile 手机号码
     * @param callBack 获取到数据之后的回调
     */
    void getSecurityCode(String mobile, IModel.CallBack callBack);

    /**
     * 修改手机号码
     * @param token token
     * @param moblie 手机号码
     * @param sms 验证码
     * @param callBack 获取到数据之后的回调
     */
    void updateMobile(String token,String moblie,String sms, IModel.CallBack callBack);

}
