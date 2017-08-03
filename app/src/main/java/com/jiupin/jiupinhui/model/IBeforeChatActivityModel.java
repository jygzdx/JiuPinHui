package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IBeforeChatActivityModel {

    /**
     * 获取售后聊天信息
     *
     * @param token    token
     * @param consultId 工单
     */
    void getChatInfo(String token, String consultId, IModel.CallBack callBack);
    /**
     * 买家对某个订单的售后资讯追加发问（继续提问）
     *
     * @param token    token
     * @param consultId 工单
     * @param content  追加的文本
     */
    void getAgainAppraise( String token, String consultId, String content, IModel.CallBack callBack);
    /**
     * 用户关闭咨询
     *
     * @param token    token
     * @param consultId 工单
     */
    void closeChat(String token, String consultId, IModel.CallBack callBack);
}
