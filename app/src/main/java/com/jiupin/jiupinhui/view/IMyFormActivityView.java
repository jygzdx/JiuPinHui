package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.FormEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IMyFormActivityView {
    /**
     * 获取订单数据成功
     * @param forms 订单信息
     */
    void getFormInfoSuccess(List<FormEntity> forms);

    /**
     * 删除订单
     */
    void deleteFormSuccess(int position);
}
