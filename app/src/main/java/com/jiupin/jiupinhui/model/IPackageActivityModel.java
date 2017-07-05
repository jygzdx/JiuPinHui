package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IPackageActivityModel {

    /**
     * 获取商品详情数据
     * @param id 商品id
     */
    void getGoodsInfo(int id, IModel.CallBack callBack);
}
