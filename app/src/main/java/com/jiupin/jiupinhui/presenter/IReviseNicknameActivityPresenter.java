package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IReviseNicknameActivityPresenter {

    /**
     * 修改昵称
     * @param token token
     * @param nickName 昵称
     */
    void updateNickname(String token, String nickName);
}
