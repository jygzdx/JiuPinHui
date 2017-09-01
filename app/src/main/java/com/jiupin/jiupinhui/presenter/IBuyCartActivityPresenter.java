package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IBuyCartActivityPresenter {

    /**
     * 获取用户购物车列表
     * @param token 用户token
     */
    void getBuyCartList(String token);

    /**
     * 删除商品
     * @param token token
     * @param id 商品id
     * @param spec_id 规格id
     */
    void deleteGoods(String token,String id ,String spec_id,int position);

    /**
     * 清空购物车
     * @param token
     */
    void emptyCart(String token);

    /**
     * 刷新商品数量
     * @param token
     * @param id
     * @param spec_id
     * @param count
     * @param position
     */
    void notifyGoodsCount(String token,String id,String spec_id,int count,int position);


    /**
     * 提交商品信息到后台，返回商品的信息，不是提交订单
     * @param token
     * @param goodStr 商品列表，类似提交订单时的goodList
     */
    void submitGoodsInfo(String token,String goodStr);
}
