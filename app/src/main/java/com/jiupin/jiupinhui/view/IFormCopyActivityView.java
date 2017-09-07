package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.ResponseBase;
import com.jiupin.jiupinhui.entity.WeChatPayEntity;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IFormCopyActivityView {

    /**
     * alipay成功
     * @param responseBase 返回成功参数
     */
    void alipaySuccess(ResponseBase responseBase);

    /**
     * 微信支付成功
     * @param weChatPayEntity 返回成功参数
     */
    void weChatPaySuccess(WeChatPayEntity weChatPayEntity);
}
