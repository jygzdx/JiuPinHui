package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IBuyCartActivityModel {

    /**
     * 获取用户购物车列表
     * @param token 用户token
     */
    void getBuyCartList(String token, IModel.CallBack callBack);

    /**
     * 删除商品
     * @param token token
     * @param id 商品id
     * @param spec_id 规格id
     */
    void deleteGoods(String token, String id , String spec_id, IModel.CallBack callBack);

    /**
     * 清空购物车
     * @param token
     */
    void emptyCart(String token, IModel.CallBack callBack);

    /**
     * 刷新商品数量
     * @param token
     * @param id
     * @param spec_id
     * @param count
     */
    void notifyGoodsCount(String token, String id, String spec_id, int count, IModel.CallBack callBack);

    /**
     * 提交商品信息到后台，返回商品的信息，不是提交订单
     * @param token
     * @param goodStr 商品列表，类似提交订单时的goodList
     */
    void submitGoodsInfo(String token, String goodStr, IModel.CallBack callBack);
}
