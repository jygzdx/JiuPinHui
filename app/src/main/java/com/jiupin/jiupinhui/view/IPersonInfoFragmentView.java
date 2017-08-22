package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.UserEntity;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IPersonInfoFragmentView {
    /**
     * 获取用户信息
     * @param userEntity
     */
    void setUserInfo(UserEntity.DataBean userEntity);

}
