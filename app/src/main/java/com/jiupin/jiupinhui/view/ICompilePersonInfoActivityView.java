package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.UserEntity;

/**
 * 作者：czb on 2017/6/26 17:03
 */

public interface ICompilePersonInfoActivityView {

    /**
     * 保存用户信息
     *
     */
    void savePersonInfo(UserEntity.DataBean userEntity);
}
