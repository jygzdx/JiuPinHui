package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IGoodsActivityModel {

    /**
     * 获取商品详情数据
     * @param id 商品id
     */
    void getGoodsInfo(int id, IModel.CallBack callBack);

    /**
     * 获取评论信息
     * @param goodsId 商品id
     */
    void getAppraise(int goodsId, IModel.CallBack callBack);
}
