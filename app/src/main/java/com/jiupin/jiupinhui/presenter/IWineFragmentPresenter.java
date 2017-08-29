package com.jiupin.jiupinhui.presenter;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IWineFragmentPresenter {


    /**
     * 获取品牌数据
     */
    void getBrandData();

    /**
     * 获取美酒种类
     */
    void getBrandKind();

    /**
     * 根据分类id获取品牌列表
     * @param cid 分类id
     */
    void getwineBrandKind(String cid);
}
