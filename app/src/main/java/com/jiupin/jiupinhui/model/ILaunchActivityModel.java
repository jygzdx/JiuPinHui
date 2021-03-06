package com.jiupin.jiupinhui.model;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface ILaunchActivityModel {
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
}
