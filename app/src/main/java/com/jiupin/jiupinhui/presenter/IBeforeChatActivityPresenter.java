package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IBeforeChatActivityPresenter {

    /**
     * 获取售后聊天信息
     *
     * @param token    token
     * @param orderNum 订单号
     */
    void getChatInfo(String token, String orderNum);

    /**
     * 买家对某个订单的售后资讯追加发问（继续提问）
     *
     * @param token    token
     * @param orderNum 订单号
     * @param content  追加的文本
     */
    void getAgainAppraise(String token, String orderNum, String content);

    /**
     * 用户关闭咨询
     *
     * @param token    token
     * @param orderNum 订单号
     */
    void closeChat(String token, String orderNum);
}
