package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IGoodsActivityPresenter {

    /**
     * 获取商品详情
     * @param id 商品id
     */
    void getGoodsInfo(int id);

    /**
     * 获取评论信息
     * @param goodsId 商品id
     */
    void getAppraise(int goodsId);
}
