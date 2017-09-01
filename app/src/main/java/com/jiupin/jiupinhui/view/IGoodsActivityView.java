package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.AppraiseEntity;
import com.jiupin.jiupinhui.entity.GoodsEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IGoodsActivityView {
    /**
     * 设置数据
     * @param goodsEntity 商品详情
     */
    void setData(GoodsEntity goodsEntity);

    /**
     * 设置用户评论
     * @param appraiseList 评论信息
     */
    void setUserAppraise(List<AppraiseEntity> appraiseList);

    /**
     * 添加到购物车
     * @param data 用户购物车商品数量
     */
    void addGoodsToCar(String data);

    /**
     * .根据用户token返回用户购物车的商品种类数量（一种商品购买多件=1，N种商品=N）
     * @param data 用户购物车商品数量
     */
    void getCartGoodsCount(String data);
}
