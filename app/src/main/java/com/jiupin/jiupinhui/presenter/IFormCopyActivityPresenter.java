package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IFormCopyActivityPresenter {

    /**
     * 获取alipay支付信息
     * @param token token
     *              @param orderId 订单id
     */
    void getAlipayInfo(String token, String orderId);

    /**
     * 获取微信支付信息
     * @param token token
     *              @param orderId 订单id
     */
    void getWeChatPayInfo(String token, String orderId);
}
