package com.jiupin.jiupinhui.presenter;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IAttentionFragmentPresenter {
    /**
     * 获取关注动态数据
     *@param token token
     * @param page 页数
     * @param rows 每页数量
     */
    void getAttentionList(String token, String page, String rows);

    /**
     * 为动态点赞
     * @param token
     * @param communityId
     * @param position
     */
    void setThumbDynamic(String token, String communityId, int position);

    /**
     * 关注用户
     * @param token
     * @param userId 用户id
     * @param concernStatus 关注状态
     * @param position
     */
    void cancelCondition(String token, String userId, String concernStatus, int position);

    /**
     * 获取用户数据
     * @param token
     */
    void getUserInfo(String token);
}
