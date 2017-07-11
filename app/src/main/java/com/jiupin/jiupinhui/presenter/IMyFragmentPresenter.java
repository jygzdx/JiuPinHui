package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/30 16:20
 */

public interface IMyFragmentPresenter {
    /**
     * 获取用户token是否可用
     * @param token token
     */
    void getTokenStatus(String token);

    /**
     * 根据token获取用户数据
     * @param token token
     */
    void getUserInfoByToken(String token);
    /**
     * 根据token获取用户所有的订单信息
     * @param token token
     */
    void getformInfoByToken(String token);
}
