package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.OrderCopyEntity;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IOrderCopyActivityView {
    /**
     * 设置数据
     * @param addressEntity 默认地址信息
     */
    void setDefaultAddress(AddressEntity addressEntity);

    /**
     * 提交订单成功
     * @param orderCopyEntity 提交成功返回信息
     */
    void submitFormSuccess(OrderCopyEntity orderCopyEntity);

}
