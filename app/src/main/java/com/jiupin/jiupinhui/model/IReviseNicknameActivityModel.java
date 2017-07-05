package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IReviseNicknameActivityModel {

    /**
     * 修改昵称
     * @param token token
     * @param nickName 昵称
     */
    void updateNickname(String token, String nickName, IModel.CallBack callBack);
}
