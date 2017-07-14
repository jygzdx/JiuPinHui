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
}
