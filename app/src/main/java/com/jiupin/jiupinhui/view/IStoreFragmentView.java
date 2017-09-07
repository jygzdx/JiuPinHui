package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.BannerEntity;
import com.jiupin.jiupinhui.entity.MainShowEntity;
import com.jiupin.jiupinhui.entity.MealTypeEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IStoreFragmentView {
    /**
     * 获取轮播图数据
     * @param bannerList 广告位实体类数组
     */
    void setBannerData(List<BannerEntity> bannerList);

    /**
     * 获取套餐分类
     * @param mealTypeList 套餐分类
     */
    void setMealTypeData(List<MealTypeEntity> mealTypeList);

    /**
     * 获取套餐信息
     * @param mainShowList 套餐信息
     */
    void setMealInfoData(List<MainShowEntity.DataBean.ListBean> mainShowList);

    /**
     * .根据用户token返回用户购物车的商品种类数量（一种商品购买多件=1，N种商品=N）
     * @param data 用户购物车商品数量
     */
    void getCartGoodsCount(String data);
}
