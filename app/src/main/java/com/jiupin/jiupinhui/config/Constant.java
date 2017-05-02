package com.jiupin.jiupinhui.config;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Constant {
    /**
     * 获取轮播图图片
     */
    public static final String CAROUSEL_URL = "https://napp.9pin.com/display/getCarouselList";

    /**
     * 通过id查询所有酒的详细数据
     */
    public static final String WINE_URL = "https://napp.9pin.com/display/getWinesByCid";
    /**
     * 获取热门品牌
     */
    public static final String WINE_Brand_URL = "https://napp.9pin.com/display/getHotBrands";



    /**
     * 等待付款
     */
    public static final int WAIT_PAY = 101;
    /**
     * 交易关闭
     */
    public static final int TRANSACTION_CLOSED = 102;
    /**
     * 交易成功(有评论)
     */
    public static final int TRANSACTION_SUCCESS_HAS_COMMENT = 103;
    /**
     * 交易成功（还未评论）
     */
    public static final int TRANSACTION_SUCCESS_NO_COMMENT = 104;
    /**
     * 等待发货
     */
    public static final int WAIT_DELIVER_GOODS = 105;
    /**
     * 等待收货
     */
    public static final int WAIT_GAIN_GOODS = 106;



}
