package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface ITranConditionActivityPresenter {

    /**
     * 发布转发动态
     * @param token token
     * @param content 转发的评论动态内容
     * @param dynamicId 动态id
     */
    void sendTranCondition(String token, String content, String dynamicId);
}
