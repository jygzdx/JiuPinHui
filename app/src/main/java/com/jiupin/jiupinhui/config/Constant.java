package com.jiupin.jiupinhui.config;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Constant {

    public static final String MAIN_URL = "http://napp.9pin.com";

    public static final String TEST_URL = "http://192.168.0.112:8080";

    public static final String APP_ID = "wx420947e7a04ec1df";
    /**
     * 获取轮播图图片
     */
    public static final String CAROUSEL_URL = MAIN_URL+"/display/getCarouselList";

    /**
     * 通过id查询所有酒的详细数据
     */
    public static final String WINE_URL = MAIN_URL+"/display/getWinesByCid";
    /**
     * 获取热门品牌
     */
    public static final String WINE_BRAND_URL = MAIN_URL+"/display/getHotBrands";

    /**
     * 获取验证码
     */
    public static final String SECURITY_CODE_URL = TEST_URL+"/appRegister/app/sendSmsMsg.json";

    /**
     * 注册用户
     */
    public static final String REGISTER_USER_URL = TEST_URL+"/appRegister/app/registerUser.json";

    /**
     * 用户登录
     */
    public static final String LOGIN_USER_URL = TEST_URL+"/appLogin/app/loginByMobilePwd.json";

    /**
     * 校验手机号码是否注册过
     */
    public static final String IS_MOBILE_UNIQUE_URL = TEST_URL+"/appRegister/app/isMobileUnique.json";

    /**
     * 热门推荐商品
     */
    public static final String HOT_RECOMMENT_URL = TEST_URL+"/appGoods/home/getGoodsList.json";

    /**
     * 推荐套餐
     */
    public static final String MAIN_SHOW_URL = TEST_URL+"/appGoods/home/getMealsList.json";

    /**
     * 猜你喜欢
     */
    public static final String GUESS_LOVE_URL = TEST_URL+"/appGoods/home/getYourFavorite.json";

    /**
     * 检查token是否可用
     */
    public static final String CHECK_TOKEN = TEST_URL+"/appLogin/app/isTokenAvailable.json";

    /**
     * 获取商品信息
     */
    public static final String GOODS_INFO = TEST_URL+"/appGoods/home/getGoodsDetail.json";

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
