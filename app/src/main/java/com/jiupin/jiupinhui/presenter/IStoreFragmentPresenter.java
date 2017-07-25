package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */
public interface IStoreFragmentPresenter {

    /**
     * 获取广告位数据
     */
    void getBanner();

    /**
     * 获取套餐分类
     */
    void getMealType();
    /**
     * 获取套餐信息
     * @param cid 套餐id
     */
    void getMealInfo(String cid);
}
