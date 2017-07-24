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

    /**
     * 获取猜你喜欢
     * @param pager 商品页数
     */
    void getHomeLove(int pager,IModel.CallBack callBack);
    /**
     * 获取广告位数据
     */
    void getBanner(IModel.CallBack callBack);
}
