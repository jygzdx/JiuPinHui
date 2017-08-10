package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.FormParticularEntity;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IPaySuccessActivityView {
    /**
     * 获取订单成功
     * @param formParticularEntity 返回的参数
     */
    void getFormSuccess(FormParticularEntity formParticularEntity);
}
