package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.MyFormEntity;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.UserEntity;

/**
 * 作者：czb on 2017/6/30 16:02
 */

public interface IMyFragmentView {


    /**
     * 检查token是否可用之后回调
     */
    void checkTokenBack(ResponseBase responseBase);

    /**
     * 根据token获取用户信息
     */
    void setUserInfo(UserEntity userEntity);

    /**
     * 设置订单的数量
     * @param myFormEntity
     */
    void setFormNumber(MyFormEntity myFormEntity);
}
