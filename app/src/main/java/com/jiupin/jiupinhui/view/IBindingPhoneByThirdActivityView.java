package com.jiupin.jiupinhui.view;

/**
 * 作者：czb on 2017/7/5 15:37
 */

public interface IBindingPhoneByThirdActivityView{

    /**
     * 修改手机号码成功
     * @param responseBase 返回的信息
     */
    void updatePhone(String responseBase);

    /**
     * 发送修改手机号码验证码成功
     * @param responseBase 返回的信息
     */
    void getSms(String responseBase);

}
