package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IConditionComActivityModel {

    /**
     * 发布动态
     * @param token token
     * @param content 动态内容
     * @param dynamicId 动态类型

     */
    void sendCondition(String token, String content, String dynamicId, IModel.CallBack callBack);

}
