package com.jiupin.jiupinhui.model;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IWineFragmentModel {

    /**
     * 获取品牌数据
     */
    void getBrandData(IModel.CallBack callBack);

    /**
     * 获取美酒种类
     */
    void getBrandKind(IModel.CallBack callBack);

    /**
     * 根据分类id获取品牌列表
     * @param cid 分类id
     */
    void getwineBrandKind(String cid, IModel.CallBack callBack);
}
