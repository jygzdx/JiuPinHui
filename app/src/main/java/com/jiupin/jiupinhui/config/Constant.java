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
     * 根据token获取用户数据
     */
    public static final String GET_USER_INFO = TEST_URL+"/app/user/getUserInfo.json";

    /**
     * 根据token查找买家的各种状态订单的数量
     */
    public static final String GET_FORM_INFO = TEST_URL+"/app/order/getUserOrderStatusCount.json";

    /**
     * 获取绑定手机验证码
     */
    public static final String BINDING_PHONE_SECURITY_CODE_URL = TEST_URL+"/app/user/sendSmsMsg.json";

    /**
     * 修改手机号码
     */
    public static final String UPDATE_MOBILE_URL = TEST_URL+"/app/user/updateMobile.json";

    /**
     * 修改昵称
     */
    public static final String UPDATE_NICKNAME_URL = TEST_URL+"/app/user/updateNickName.json";
    /**
     * 上传图片
     */
    public static final String PUSH_HEAD_URL = TEST_URL+"/app/user/updateHeadImg.json";

    /**
     * 重置密码
     */
    public static final String RESET_PASSWORD_URL = TEST_URL+"/appLogin/app/resetPwd.json";

    /**
     * 获取重置密码验证码
     */
    public static final String RESET_CODE_URL = TEST_URL+"/appLogin/app/sendSmsMsg.json";

    /**
     * 获取用户地址列表
     */
    public static final String ADDRESS_LIST = TEST_URL+"/app/order/getUserAddress.json";

    /**
     * 获取用户默认地址
     */
    public static final String GET_DEFAULT_ADDRESS = TEST_URL+"/app/order/getDefaultAddress.json";

    /**
     * 获取省市区地址
     */
    public static final String SHENG_SHI_QU_AREA = TEST_URL+"/app/order/loadCascadeAreaList.json";


    /**
     * 上传地址
     */
    public static final String PUSH_ADDRESS = TEST_URL+"/app/order/saveOrUpdateAddress.json";

    /**
     * 获取商品信息
     */
    public static final String GOODS_INFO = TEST_URL+"/appGoods/home/getGoodsDetail.json";

    /**
     * 提交订单
     */
    public static final String SUBMIT_FORM = TEST_URL+"/app/order/submitOrder.json";

    /**
     * 获取用户所有的订单信息
     */
    public static final String GET_ALL_FORM_INFO = TEST_URL+"/app/order/getUserOrderListByCond.json";

    /**
     * 获取单个订单信息
     */
    public static final String GET_SINGLE_FORM_INFO = TEST_URL+"/app/order/getOrderInfoById.json";

    /**
     * 取消订单
     */
    public static final String CANCEL_FORM = TEST_URL+"/app/order/cancelOrder.json";

    /**
     * 确定收货
     */
    public static final String ENSURE_GAIN_GOODS = TEST_URL+"/app/order/confirmReceiving.json";

    /**
     * 删除订单
     */
    public static final String DELETE_FORM = TEST_URL+"/app/order/deleteOrder.json";

    /**
     * 保存用户评价
     */
    public static final String SEND_COMMENT = TEST_URL+"/app/order/userEvaluate.json";

    /**
     * 等待付款
     */
    public static final String WAIT_PAY = 10+"";
    /**
     * 交易关闭
     */
    public static final String TRANSACTION_CLOSED = 0+"";
    /**
     * 交易成功(有评论)
     */
    public static final String TRANSACTION_SUCCESS_HAS_COMMENT = 50+"";
    /**
     * 交易成功（还未评论）
     */
    public static final String TRANSACTION_SUCCESS_NO_COMMENT = 40+"";
    /**
     * 等待发货
     */
    public static final String WAIT_DELIVER_GOODS = 20+"";
    /**
     * 等待收货
     */
    public static final String WAIT_GAIN_GOODS = 30+"";

    /**
     * 退款和售后
     */
    public static final String SALE_AFTER = 45+"";



}
