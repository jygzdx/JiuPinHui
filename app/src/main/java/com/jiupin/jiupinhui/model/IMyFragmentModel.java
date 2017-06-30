package com.jiupin.jiupinhui.model;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IMyFragmentModel {
    /**
     * 获取用户token是否可用
     * @param token token
     * @param callBack 获取到数据之后的回调
     */
    void getTokenStatus(String token ,IModel.CallBack callBack);

}
