package com.jiupin.jiupinhui.model;

import java.io.File;

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
    /**
     * 买家对某个订单的售后资讯追加发问（继续提问）
     *
     * @param photo    图片文件
     * @param token    token
     * @param orderNum 订单号
     * @param content  追加的文本
     */
    void getAgainAppraise(File photo, String token, String orderNum, String content, IModel.CallBack callBack);
    /**
     * 用户关闭咨询
     *
     * @param token    token
     * @param orderNum 订单号
     */
    void closeChat( String token, String orderNum, IModel.CallBack callBack);
}
