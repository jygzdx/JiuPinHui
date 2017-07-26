package com.jiupin.jiupinhui.presenter;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IWineFragmentPresenter {
    /**
     * 获取美酒详细数据
     *
     * @param page 页数
     * @param rows 每页数量
     */
    void getWineList(String page, String rows);

    /**
     * 获取美酒详细数据
     *
     * @param brandId 品牌id
     * @param page    页数
     * @param rows    每页数量
     */
    void getWineListByBrandId(String brandId, String page, String rows);

    /**
     * 获取品牌数据
     */
    void getBrandData();
}
