package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IIdeaBackActivityPresenter {

    /**
     * 获取商品详情
     * @param token 用户token
     * @param content 反馈内容
     * @param way 反馈途径：1.微信商城，2.App商城，3.待加
     */
    void submitIdea(String token,String content,String way);
}
