package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IManageAddressActivityPresenter {

    /**
     * 获取用户地址列表
     * @param token 用户token
     */
    void getAddressList(String token);
}