package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IRegisterActivityModel {
    /**
     * 获取注册验证码
     * @param mobile 手机号码
     * @param callBack 获取到数据之后的回调
     */
    void getSecurityCode(String mobile, IModel.CallBack callBack);

    /**
     * 用户注册
     * @param mobile 电话号码
     * @param code 验证码
     * @param pwd 密码
     */
    void registerUser(String mobile, String code, String pwd, IModel.CallBack callBack);

    /**
     * 校验手机号码是否注册过
     * @param mobile 电话号码
     */
    void isMobileUnique(String mobile, IModel.CallBack callBack);
}
