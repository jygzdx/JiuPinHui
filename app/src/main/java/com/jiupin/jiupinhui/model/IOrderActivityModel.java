package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IOrderActivityModel {

    /**
     * 获取用户默认地址
     * @param token token
     */
    void getDefaultAddress(String token, IModel.CallBack callBack);

    /**
     * 提交订单
     * @param userId 用户id
     * @param storeId 店铺id
     * @param token 用户token
     * @param msg 订单预计送达时间
     * @param couponInfoId 优惠券ID
     * @param order_type 订单类型，固定写 app商城
     * @param addressId 买家选择的地址ID
     * @param goodList 商品列表，可以有多件
     */
    void submitForm(String userId, String storeId, String token, String msg,
                    String couponInfoId, String order_type, String addressId, String goodList, IModel.CallBack callBack);
}
