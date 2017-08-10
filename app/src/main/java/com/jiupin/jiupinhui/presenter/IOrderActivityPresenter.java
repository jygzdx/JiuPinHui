package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IOrderActivityPresenter {

    /**
     * 获取用户默认地址
     * @param token token
     */
    void getDefaultAddress(String token);

    /**
     * 提交订单
     * @param token token
     */
    void submitForm(String userId,String storeId,String token,String msg,
                    String couponInfoId,String order_type,String addressId,String goodList);

    /**
     * 获取alipay支付信息
     * @param token token
     *              @param orderId 订单id
     */
    void getAlipayInfo(String token,String orderId);

    /**
     * 获取微信支付信息
     * @param token token
     *              @param orderId 订单id
     */
    void getWeChatPayInfo(String token,String orderId);
}
