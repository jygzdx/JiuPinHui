package com.jiupin.jiupinhui.model;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IMyFragmentModel {
    /**
     * 获取用户token是否可用
     *
     * @param token    token
     * @param callBack 获取到数据之后的回调
     */
    void getTokenStatus(String token, IModel.CallBack callBack);

    /**
     * 获取用户信息
     *
     * @param token    token
     * @param callBack 获取到数据之后的回调
     */
    void getUserInfoByToken(String token, IModel.CallBack callBack);

    /**
     * 根据token获取用户所有的订单信息
     *
     * @param token    token
     * @param callBack 获取到数据之后的回调
     */
    void getformInfoByToken(String token, IModel.CallBack callBack);
}
