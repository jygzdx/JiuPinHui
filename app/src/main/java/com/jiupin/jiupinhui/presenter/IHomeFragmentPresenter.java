package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IHomeFragmentPresenter {

    /**
     * 获取热门推荐
     */
    void getHotRecomment();

    /**
     * 获取推荐套餐
     */
    void getMainShow();
    /**
     * 获取猜你喜欢商品
     * @param pager 商品页数
     */
    void getHomeLove(int pager);

    /**
     * 获取广告位数据
     */
    void getBanner();
    /**
     * 获取热门文章
     */
    void getArticle();
}
