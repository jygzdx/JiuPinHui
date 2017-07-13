package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IOrderActivityModel {

    /**
     * 获取用户默认地址
     * @param token token
     */
    void getDefaultAddress(String token, IModel.CallBack callBack);
}
