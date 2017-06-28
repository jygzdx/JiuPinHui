package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.HotRecommentEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;

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


}
