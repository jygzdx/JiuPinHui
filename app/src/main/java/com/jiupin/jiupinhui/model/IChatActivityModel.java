package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IChatActivityModel {

    /**
     * 获取售后聊天信息
     *
     * @param token    token
     * @param orderNum 订单号
     */
    void getChatInfo(String token, String orderNum, IModel.CallBack callBack);
}
