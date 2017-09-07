package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/28 11:16
 */

public interface IStoreFragmentModel {

    /**
     * 获取广告位数据
     */
    void getBanner(IModel.CallBack callBack);
    /**
     * 获取套餐分类
     */
    void getMealType(IModel.CallBack callBack);
    /**
     * 获取套餐信息
     * @param cid 套餐id
     */
    void getMealInfo(String cid,IModel.CallBack callBack);
    /**
     * 根据用户token返回用户购物车的商品种类数量（一种商品购买多件=1，N种商品=N）
     * @param token token
     */
    void getCartGoodsCount(String token, IModel.CallBack callBack);
}
