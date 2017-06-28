package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/28 11:16
 */

public interface IHomeFragmentModel {

    /**
     * 获取热门推荐
     */
    void getHotRecomment(IModel.CallBack callBack);

    /**
     * 获取推荐套餐
     */
    void getMainShow(IModel.CallBack callBack);
}
