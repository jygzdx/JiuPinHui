package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface ICommentActivityPresenter {

    /**
     * 获取评论信息
     * @param goodsId 商品id
     */
    void getAppraise(int goodsId,int page);
}
