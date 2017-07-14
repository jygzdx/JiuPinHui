package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.OrderSubmitEntity;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IOrderActivityView {
    /**
     * 设置数据
     * @param addressEntity 默认地址信息
     */
    void setData(AddressEntity addressEntity);

    /**
     * 提交订单成功
     * @param orderSubmitEntity 提交成功返回信息
     */
    void submitFormSuccess(OrderSubmitEntity orderSubmitEntity);

    /**
     * 提交订单失败
     */
    void submitFormFail();



}
