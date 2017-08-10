package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IPaySuccessActivityModel {

    /**
     * 获取订单详情
     *
     * @param orderId  订单id
     * @param token    token
     * @param callBack callback
     */
    void getFormInfo(String orderId, String token, IModel.CallBack callBack);

}
