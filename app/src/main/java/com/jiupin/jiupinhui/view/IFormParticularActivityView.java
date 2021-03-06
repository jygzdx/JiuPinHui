package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.FormParticularEntity;
import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.WeChatPayEntity;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IFormParticularActivityView {
    /**
     * 获取订单成功
     *
     * @param formParticularEntity 返回的参数
     */
    void getFormSuccess(FormParticularEntity formParticularEntity);

    /**
     * 取消订单
     */
    void cancelFormSuccess();

    /**
     * 确定收货
     */
    void ensureGainGoodsSuccess();

    /**
     * 删除订单
     */
    void deleteFormSuccess();

    /**
     * alipay成功
     *
     * @param responseBase 返回成功参数
     */
    void alipaySuccess(ResponseBase responseBase);

    /**
     * 微信支付成功
     *
     * @param weChatPayEntity 返回成功参数
     */
    void weChatPaySuccess(WeChatPayEntity weChatPayEntity);

    /**
     * 获取优惠券url
     *
     * @param url 优惠券url
     */
    void getCouponUrlSuccess(String url);
}
