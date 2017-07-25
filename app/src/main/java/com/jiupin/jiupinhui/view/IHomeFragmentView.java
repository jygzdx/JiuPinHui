package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.ArticleEntity;
import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.HomeLoveEntity;
import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IHomeFragmentView {
    /**
     * 获取热门推荐数据
     * @param hotRecommentEntity 热门推荐商品
     */
    void setHotRecommentData(HotRecommentEntity hotRecommentEntity);

    /**
     * 获取主推套餐
     * @param mainShowEntity 热门推荐商品
     */
    void setMainShow(MainShowEntity mainShowEntity);

    /**
     * 获取猜你喜欢
     * @param homeLoveEntity 获取猜你喜欢
     */
    void setHomeLove(HomeLoveEntity homeLoveEntity);

    /**
     * 获取banner数据
     * @param bannerList 广告位实体类数组
     */
    void setBannerData(List<BannerEntity> bannerList);

    /**
     * 获取热门文章数据
     * @param articleList 热门文章实体类数组
     */
    void setArticleData(List<ArticleEntity> articleList);

}
