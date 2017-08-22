package com.jiupin.jiupinhui.presenter;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IFansActivityPresenter {
    /**
     * 获取推荐关注列表
     *
     * @param page 页数
     * @param rows 每页数量
     */
    void getRecommendList(String token, String page, String rows);

    /**
     * 关注用户
     * @param token
     * @param userId 用户id
     * @param concernStatus 关注状态
     */
    void cancelCondition(String token, String userId, String concernStatus, int position);
}
