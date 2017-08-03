package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface ICommentActivityModel {

    /**
     * 获取评论信息
     * @param goodsId 商品id
     */
    void getAppraise(int goodsId,int page, IModel.CallBack callBack);
}
