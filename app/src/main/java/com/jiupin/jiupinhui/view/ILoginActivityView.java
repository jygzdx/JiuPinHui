package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.RegisterEntity;

/**
 * 作者：czb on 2017/6/26 17:03
 */

public interface ILoginActivityView {

    /**
     * 注册成功
     *
     * @param registerEntity 返回实体类
     */
    void registerSuccess(RegisterEntity registerEntity);

    /**
     * 注册失败
     * @param errorMsg 错误信息
     */
    void registerFail(String errorMsg);
    /**
     * 登录成功
     *
     * @param userBean 返回实体类
     */
    void loginSuccess(RegisterEntity.DataBean.UserBean userBean);

    /**
     * 登录失败
     * @param errorMsg 错误信息
     */
    void loginFail(String errorMsg);

    /**
     * 手机号码是否注册过
     *
     * @param data 手机号码是否注册过：0->注册过   1->没注册过
     */
    void isMobileUnique(String data);

    /**
     * 网络错误
     */
    void isMobileUniqueFail();
}
