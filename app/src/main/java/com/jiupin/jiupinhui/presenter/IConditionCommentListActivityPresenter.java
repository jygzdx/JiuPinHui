package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IConditionCommentListActivityPresenter {

    /**
     * 获取动态评论信息
     * @param dynamicId 动态id
     */
    void getCommentList(String dynamicId, String page);
}
