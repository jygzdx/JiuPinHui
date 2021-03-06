package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IFormParticularActivityModel {

    /**
     * 获取订单详情
     *
     * @param orderId  订单id
     * @param token    token
     * @param callBack callback
     */
    void getFormInfo(String orderId, String token, IModel.CallBack callBack);

    /**
     * 取消订单
     *
     * @param orderId 订单id
     * @param token   token
     * @param callBack callback
     */
    void cancelForm(String orderId, String token, IModel.CallBack callBack);

    /**
     * 确定收货
     *
     * @param orderId 订单id
     * @param token   token
     */
    void ensureGainGoods(String orderId, String token, IModel.CallBack callBack);

    /**
     * 删除订单
     *
     * @param orderId 订单id
     * @param token   token
     */
    void deleteForm(String orderId, String token, IModel.CallBack callBack);

    /**
     * 获取alipay支付信息
     * @param token token
     *              @param orderId 订单id
     */
    void getAlipayInfo(String token,String orderId, IModel.CallBack callBack);

    /**
     * 获取微信支付信息
     * @param token token
     *              @param orderId 订单id
     */
    void getWeChatPayInfo(String token,String orderId, IModel.CallBack callBack);

    /**
     * 获取优惠券url
     * @param token
     * @param orderId 订单id
     */
    void getCouponUrl(String token , String orderId, IModel.CallBack callBack);
}
