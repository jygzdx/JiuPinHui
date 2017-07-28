package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IMyFormActivityModel {

    /**
     * 获取订单信息
     *
     * @param token       token
     * @param orderStatus 订单状态码（见详细说明），如不填写则表示包含所有订单
     *                    0 交易关闭 10 待付款 20 待发货 30 待收货 40 待评价 45 退款/售后 50 交易完成
     * @param page        页数
     * @param rows        条目数
     * @param callBack    callback
     */
    void getFormInfo(String token, String orderStatus, String page, String rows, IModel.CallBack callBack);

    /**
     * 删除订单
     *
     * @param orderId 订单id
     * @param token   token
     */
    void deleteForm(String orderId, String token, IModel.CallBack callBack);
}
