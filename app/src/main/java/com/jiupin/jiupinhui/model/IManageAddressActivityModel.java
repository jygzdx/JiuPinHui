package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IManageAddressActivityModel {

    /**
     * 获取用户地址列表
     * @param token 用户token
     */
    void getAddressList(String token, IModel.CallBack callBack);
}
