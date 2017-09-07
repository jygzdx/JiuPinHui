package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface IOrderCopyActivityModel {

    /**
     * 获取用户默认地址
     * @param token token
     */
    void getDefaultAddress(String token, IModel.CallBack callBack);

    /**
     * 提交订单
     * @param token 用户token
     * @param msg 备注
     * @param couponInfoId 优惠券ID
     * @param order_type app商城
     * @param addressId 买家选择的地址ID
     * @param goodList 商品列表字符串
     * @param invoiceType 发票抬头（1.个人发票 2.公司发票），只能填1,2以内的数字！
     * @param invoice 发票内容（公司名称，当发票抬头为2时，不能为空，要检查非空）
     * @param invoiceCode 组织机构代码
     * @param invoiceDesc 发票商品种类（啤酒，葡萄酒），只能是啤酒，葡萄酒其中之一
     *                    @param callBack 回调
     */
    void submitForm(String token, String msg, String couponInfoId, String order_type,
                    String addressId, String goodList, String invoiceType, String invoice,
                    String invoiceCode, String invoiceDesc, IModel.CallBack callBack);


//    /**
//     * 获取alipay支付信息
//     * @param token token
//     *              @param orderId 订单id
//     */
//    void getAlipayInfo(String token, String orderId, IModel.CallBack callBack);
//
//    /**
//     * 获取微信支付信息
//     * @param token token
//     *              @param orderId 订单id
//     */
//    void getWeChatPayInfo(String token, String orderId, IModel.CallBack callBack);

}
