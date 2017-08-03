package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IServerActivityPresenter {

    /**
     * 获取所有售前工单列表
     * @param token 用户token
     */
    void getChatHistory(String token,String page,String rows);

}
