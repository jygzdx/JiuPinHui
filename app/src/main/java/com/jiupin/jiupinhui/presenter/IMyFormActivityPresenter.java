package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IMyFormActivityPresenter {

    /**
     * 获取订单信息
     * @param token token
     * @param orderStatus 订单状态码（见详细说明），如不填写则表示包含所有订单
     * 0 交易关闭 10 待付款 20 待发货 30 待收货 40 待评价 45 退款/售后 50 交易完成
     * @param page 页数
     * @param rows 条目数
     */
    void getFormInfo(String token,String orderStatus,String page,String rows);
}
