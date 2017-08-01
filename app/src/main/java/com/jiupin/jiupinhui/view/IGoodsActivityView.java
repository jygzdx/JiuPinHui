package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.AppraiseEntity;
import com.jiupin.jiupinhui.entity.GoodsEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IGoodsActivityView {
    /**
     * 设置数据
     * @param goodsEntity 商品详情
     */
    void setData(GoodsEntity goodsEntity);

    /**
     * 设置用户评论
     * @param appraiseList 评论信息
     */
    void setUserAppraise(List<AppraiseEntity> appraiseList);
}
