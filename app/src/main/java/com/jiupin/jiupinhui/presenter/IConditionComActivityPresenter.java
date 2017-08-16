package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IConditionComActivityPresenter {

    /**
     * 发布动态
     * @param token token
     * @param content 动态内容
     * @param dynamicId 动态id
     */
    void sendCondition(String token, String content, String dynamicId);
}
