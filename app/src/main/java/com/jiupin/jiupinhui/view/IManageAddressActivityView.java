package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.AddressEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IManageAddressActivityView {
    /**
     * 设置数据
     * @param adds 地址列表
     */
    void setData(List<AddressEntity> adds);

    /**
     * 改变默认地址成功
     */
    void changeDefaultAddressSuccess();

    /**
     * 删除地址
     */
    void deleteAddressSuccess(List<AddressEntity> adds);
}
