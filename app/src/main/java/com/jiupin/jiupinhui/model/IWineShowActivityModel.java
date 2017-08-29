package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IWineShowActivityModel {

    /**
     * 获取展示商品的详情数据
     * @param goodsId 商品id
     */
    void getGoodsInfo(String goodsId, IModel.CallBack callBack);
}
