package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IResetActivityModel {

    /**
     * 获取重置密码验证码
     * @param mobile 手机号码
     * @param callBack 获取到数据之后的回调
     */
    void getResetSecurityCode(String mobile, IModel.CallBack callBack);

    /**
     * 用户重置密码
     * @param mobile 电话号码
     * @param code 验证码
     * @param pwd 密码
     */
    void resetPwd(String mobile, String code, String pwd, IModel.CallBack callBack);
}
