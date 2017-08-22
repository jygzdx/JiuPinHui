package com.jiupin.jiupinhui.model;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IPersonInfoFragmentModel {

    /**
     * 获取用户数据
     * @param userId
     */
    void getUserInfo(String userId, IModel.CallBack callBack);
}
