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

    /**
     * 添加到购物车
     *
     * @param token   token
     * @param id      商品ID
     * @param spec_id 商品规格
     * @param count   数量
     */
    void addGoodsToCar(String token, String id, String spec_id, String count, IModel.CallBack callBack);

    /**
     * 根据用户token返回用户购物车的商品种类数量（一种商品购买多件=1，N种商品=N）
     * @param token token
     */
    void getCartGoodsCount(String token, IModel.CallBack callBack);
}
