package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.HotRecommentEntity;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IHomeFragmentView {
    /**
     * 刷新热门推荐数据
     * @param hotRecommentEntity 热门推荐商品
     */
    void setHotRecommentData(HotRecommentEntity hotRecommentEntity);

}
