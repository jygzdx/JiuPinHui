package com.jiupin.jiupinhui.model;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IPersonConditionFragmentModel {

    /**
     * 获取个人动态数据
     *@param token token
     * @param page 页数
     * @param rows 每页数量
     */
    void getPersonConditionList(String token, String userId, String page, String rows, IModel.CallBack callBack);

    /**
     * 为动态点赞
     * @param token
     * @param communityId
     */
    void setThumbDynamic(String token, String communityId, IModel.CallBack callBack);

    /**
     * 删除动态
     * @param token
     * @param dynamicId 动态id
     * @param callBack
     */
    void deleteCondition(String token, String dynamicId, IModel.CallBack callBack);

    /**
     * 是否仅自己可见
     * @param token
     * @param dynamicId
     * @param isVisible 是否仅自己可见
     */
    void isOnlyMyselfLook(String token, String dynamicId, String isVisible, IModel.CallBack callBack);

    /**
     * 置顶
     * @param token
     * @param dynamicId
     */
    void moveConditionToTop(String token, String dynamicId, IModel.CallBack callBack);

}
