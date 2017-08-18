package com.jiupin.jiupinhui.presenter;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IPersonConditionFragmentPresenter {
    /**
     * 获取个人动态数据
     *
     * @param token  token
     * @param userId userId
     * @param page   页数
     * @param rows   每页数量
     */
    void getPersonConditionList(String token, String userId, String page, String rows);

    /**
     * 为动态点赞
     *
     * @param token
     * @param communityId
     * @param position
     */
    void setThumbDynamic(String token, String communityId, int position);

    /**
     * 删除动态
     * @param token
     * @param dynamicId 动态id
     * @param position 位置
     */
    void deleteCondition(String token,String dynamicId,int position);

    /**
     * 是否仅自己可见
     * @param token
     * @param dynamicId
     * @param isVisible 是否仅自己可见
     */
    void isOnlyMyselfLook(String token,String dynamicId,String isVisible);

    /**
     * 置顶
     * @param token
     * @param dynamicId
     */
    void moveConditionToTop(String token,String dynamicId);
}
