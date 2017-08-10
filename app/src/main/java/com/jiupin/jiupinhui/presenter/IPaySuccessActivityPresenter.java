package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IPaySuccessActivityPresenter {

    /**
     * 获取订单详情
     *
     * @param orderId 订单id
     * @param token   token
     */
    void getFormInfo(String orderId, String token);
}
