package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.UserEntity;

/**
 * 作者：czb on 2017/6/30 16:02
 */

public interface IPersonInfoActivityView {


    /**
     * 根据token获取用户信息
     */
    void setUserInfo(UserEntity userEntity);
    /**
     * 上传图片
     * @param responseBase 返回数据
     */
    void pushPicture(ResponseBase responseBase);
}
