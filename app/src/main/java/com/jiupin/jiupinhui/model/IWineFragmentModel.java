package com.jiupin.jiupinhui.model;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IWineFragmentModel {
    /**
     * 获取美酒详细数据
     * @param callBack 获取到数据之后的回调
     */
    void getData(IModel.CallBack callBack);

    /**
     * 获取品牌数据
     * @param callBack 获取到数据之后的回调
     */
    void getBrandData(IModel.CallBack callBack);
}
