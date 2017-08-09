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
}
