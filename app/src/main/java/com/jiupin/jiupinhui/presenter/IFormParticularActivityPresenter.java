package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IFormParticularActivityPresenter {

    /**
     * 获取订单详情
     *
     * @param orderId 订单id
     * @param token   token
     */
    void getFormInfo(String orderId, String token);

    /**
     * 取消订单
     *
     * @param orderId 订单id
     * @param token   token
     */
    void cancelForm(String orderId, String token);

    /**
     * 确定收货
     *
     * @param orderId 订单id
     * @param token   token
     */
    void ensureGainGoods(String orderId, String token);

    /**
     * 删除订单
     *
     * @param orderId 订单id
     * @param token   token
     */
    void deleteForm(String orderId, String token);
}
