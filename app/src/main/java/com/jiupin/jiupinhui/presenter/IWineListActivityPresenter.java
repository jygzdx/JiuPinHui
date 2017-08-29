package com.jiupin.jiupinhui.presenter;

/**
 * 作者：czb on 2017/6/26 14:20
 */

public interface IWineListActivityPresenter {
    /**
     * 获取美酒详细数据
     *
     * @param brandId 品牌id
     * @param page    页数
     * @param rows    每页数量
     */
    void getWineListByBrandId(String brandId, String page, String rows);
}
