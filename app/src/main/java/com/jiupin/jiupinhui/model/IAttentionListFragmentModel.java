package com.jiupin.jiupinhui.model;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IAttentionListFragmentModel {

    /**
     * 获取推荐关注列表
     *
     * @param page 页数
     * @param rows 每页数量
     */
    void getRecommendList(String token, String page, String rows, IModel.CallBack callBack);

    /**
     * 关注用户
     * @param token
     * @param userId 用户id
     * @param concernStatus 关注状态
     */
    void cancelCondition(String token, String userId, String concernStatus, IModel.CallBack callBack);
}
