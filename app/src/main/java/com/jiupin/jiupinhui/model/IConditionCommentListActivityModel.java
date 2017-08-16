package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IConditionCommentListActivityModel {

    /**
     * 获取动态评论信息
     * @param dynamicId 动态id
     */
    void getCommentList(String dynamicId, String page, IModel.CallBack callBack);
}
