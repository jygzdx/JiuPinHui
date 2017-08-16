package com.jiupin.jiupinhui.presenter;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IRecommendFragmentPresenter {
    /**
     * 获取推荐动态数据
     *@param token token
     * @param page 页数
     * @param rows 每页数量
     */
    void getRecommendList(String token,String page, String rows);

    /**
     * 为动态点赞
     * @param token
     * @param communityId
     * @param position
     */
    void setThumbDynamic(String token,String communityId , int position);

    /**
     * 关注用户
     * @param token
     * @param userId 用户id
     * @param concernStatus 关注状态
     * @param position
     */
    void cancelCondition(String token,String userId,String concernStatus, int position);

    /**
     * 获取用户数据
     * @param token
     */
    void getUserInfo(String token);
}
