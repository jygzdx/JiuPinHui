package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface ICompilePersonInfoActivityPresenter {

    /**
     * 保存用户信息
     * @param token token
     * @param nickName 昵称
     * @param sex 性别 (1是男，0是女)
     * @param location 位置
     * @param intro 用户简介
     * @param education 用户教育信息
     */
    void savePersonInfo(String token,String nickName,String sex,String location,String intro,String education );
}
