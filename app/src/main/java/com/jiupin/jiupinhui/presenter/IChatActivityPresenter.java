package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IChatActivityPresenter {

    /**
     * 获取售后聊天信息
     *
     * @param token    token
     * @param orderNum 订单号
     */
    void getChatInfo(String token, String orderNum);
}
