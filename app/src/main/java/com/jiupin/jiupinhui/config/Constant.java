package com.jiupin.jiupinhui.config;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Constant {

    //正式版本
    public static final String MAIN_URL = "https://napp.9pin.com";

    //https测试版本
//    public static final String MAIN_URL = "https://nappt.9pin.com";

//    public static final String MAIN_URL = "http://nappt.9pin.com";
    //测试ip
//        public static final String MAIN_URL = "http://192.168.0.131:8080";
    //服务器ip
//        public static final String MAIN_URL = "http://192.168.0.168:8080";
    //宇明
    //    public static final String MAIN_URL = "http://192.168.0.103:8080";

    /**
     * 商品详情页链接
     */
    public static final String GOODS_URL = MAIN_URL + "/app/page/goGoodsDetailInfo.htm?goodsId=";

    /**
     * 微信appid
     */
    public static final String APP_ID = "wx420947e7a04ec1df";

    /**
     * bugly_appid
     */
    public static final String BUGLY_APP_ID = "1d1e527e96";

    /**
     * 获取首页轮播图图片
     */
    public static final String BANNER_URL = MAIN_URL + "/appGoods/home/getBannerList.json";
    /**
     * 获取热门文章
     */
    public static final String ARTICLE_URL = MAIN_URL + "/app/article/getHotArticle.json";

    /**
     * 商城顶部轮播图
     */
    public static final String STORE_BANNER_URL = MAIN_URL + "/appGoods/mall/getBannerList.json";

    /**
     * 获取套餐分类
     */
    public static final String MEAL_TYPE_URL = MAIN_URL + "/appGoods/mall/getMealClass.json";

    /**
     * 获取套餐信息
     */
    public static final String MEAL_INFO_URL = MAIN_URL + "/appGoods/mall/getMealByClassId.json";

    /********************************美酒**************************************/

    /**
     * 获取热门品牌
     */
    public static final String WINE_BRAND_URL = MAIN_URL + "/appGoods/wine/getHotBrand.json";

    /**
     * 获取美酒商品列表
     */
    public static final String WINE_INFO_URL = MAIN_URL + "/appGoods/wine/getGoodWineList.json";
    /**
     * 根据品牌id获取商品列表
     */
    public static final String WINE_INFO_BY_ID_URL = MAIN_URL + "/appGoods/wine/getGoodsByBrandId.json";

    /**
     * 获取酒款分类id，分类名称
     */
    public static final String GET_BRAND_CLASS_LIST = MAIN_URL + "/appGoods/wine/getBrandClassList.json";

    /**
     * 根据分类id获取品牌列表
     */
    public static final String GET_BRAND_LIST_BY_CID = MAIN_URL + "/appGoods/wine/getBrandListByCid.json";

    /**
     * 获取美酒商品详情
     */
    public static final String GET_WINE_DETAIL_BY_CID = MAIN_URL + "/appGoods/wine/getWineDetailById.json";

    /**
     * 获取验证码
     */
    public static final String SECURITY_CODE_URL = MAIN_URL + "/appRegister/app/sendSmsMsg.json";

    /**
     * 注册用户
     */
    public static final String REGISTER_USER_URL = MAIN_URL + "/appRegister/app/registerUser.json";

    /**
     * 用户登录
     */
    public static final String LOGIN_USER_URL = MAIN_URL + "/appLogin/app/loginByMobilePwd.json";

    /**
     * 第三方注册/登录接口--微信登录
     */
    public static final String WX_LOGIN = MAIN_URL + "/appRegister/app/loginByThirdParty.json";

    /**
     * 校验手机号码是否注册过
     */
    public static final String IS_MOBILE_UNIQUE_URL = MAIN_URL + "/appRegister/app/isMobileUnique.json";

    /**
     * 热门推荐商品
     */
    public static final String HOT_RECOMMENT_URL = MAIN_URL + "/appGoods/home/getGoodsList.json";

    /**
     * 推荐套餐
     */
    public static final String MAIN_SHOW_URL = MAIN_URL + "/appGoods/home/getMealsList.json";

    /**
     * 首页主推套餐与热门推荐整合
     */
    public static final String GOODS_AND_MEALS_LIST = MAIN_URL + "/appGoods/home/getGoodsAndMealsList.json";

    /**
     * 猜你喜欢
     */
    public static final String GUESS_LOVE_URL = MAIN_URL + "/appGoods/home/getYourFavorite.json";

    /**
     * 用户意见反馈
     */
    public static final String IDEA_BACK_URL = MAIN_URL + "/app/saveFeedback.json";

    /**
     * 检查token是否可用
     */
    public static final String CHECK_TOKEN = MAIN_URL + "/appLogin/app/isTokenAvailable.json";

    /**
     * 根据token获取用户数据
     */
    public static final String GET_USER_INFO = MAIN_URL + "/app/user/getUserInfo.json";

    /**
     * 根据token查找买家的各种状态订单的数量
     */
    public static final String GET_FORM_INFO = MAIN_URL + "/app/order/getUserOrderStatusCount.json";

    /**
     * 获取绑定手机验证码
     */
    public static final String BINDING_PHONE_SECURITY_CODE_URL = MAIN_URL + "/app/user/sendSmsMsg.json";

    /**
     * 修改手机号码
     */
    public static final String UPDATE_MOBILE_URL = MAIN_URL + "/app/user/updateMobile.json";

    /**
     * 修改昵称
     */
    public static final String UPDATE_NICKNAME_URL = MAIN_URL + "/app/user/updateNickName.json";
    /**
     * 上传图片
     */
    public static final String PUSH_HEAD_URL = MAIN_URL + "/app/user/updateHeadImg.json";

    /**
     * 重置密码
     */
    public static final String RESET_PASSWORD_URL = MAIN_URL + "/appLogin/app/resetPwd.json";

    /**
     * 获取重置密码验证码
     */
    public static final String RESET_CODE_URL = MAIN_URL + "/appLogin/app/sendSmsMsg.json";

    /**
     * 获取用户地址列表
     */
    public static final String ADDRESS_LIST = MAIN_URL + "/app/order/getUserAddress.json";

    /**
     * 获取用户默认地址
     */
    public static final String GET_DEFAULT_ADDRESS = MAIN_URL + "/app/order/getDefaultAddress.json";

    /**
     * 改变用户默认地址
     */
    public static final String CHANGE_DEFAULT_ADDRESS = MAIN_URL + "/app/order/setDefaultAddress.json";

    /**
     * 删除地址
     */
    public static final String DELETE_ADDRESS = MAIN_URL + "/app/order/deleteUserAddress.json";

    /**
     * 获取省市区地址
     */
    public static final String SHENG_SHI_QU_AREA = MAIN_URL + "/app/order/loadCascadeAreaList.json";


    /**
     * 上传地址
     */
    public static final String PUSH_ADDRESS = MAIN_URL + "/app/order/saveOrUpdateAddress.json";

    /********************************商品详情页**************************************/
    /**
     * 获取商品信息
     */
    public static final String GOODS_INFO = MAIN_URL + "/appGoods/home/getGoodsDetail.json";

    /**
     * 获取评论列表
     */
    public static final String APPRAISE_INFO = MAIN_URL + "/app/order/getEvaluate.json";

    /**
     * 往购物车里面添加一种商品（sku为单位，可以有多件）
     */
    public static final String ADD_TO_CAR = MAIN_URL + "/app/cart/addItemToCart.json";

    /**
     * 根据用户token返回用户购物车的商品种类数量（一种商品购买多件=1，N种商品=N）
     */
    public static final String GET_CART_GOODS_COUNT = MAIN_URL + "/app/cart/getCartGoodsCount.json";

    /********************************购物车**************************************/
    /**
     * 根据用户token查找购物车商品列表
     */
    public static final String GET_CART_LIST = MAIN_URL + "/app/cart/getCartByUserId.json";

    /**
     * 用户选择指定商品后进入确认订单页面
     */
    public static final String SUBMIT_GOODS_INFO = MAIN_URL + "/app/order/getUserSpecifiedGoods.json";

    /**
     * 在购物车里面删除一种商品（sku为单位）
     */
    public static final String DELETE_ITEM_FROM_CART = MAIN_URL + "/app/cart/deleteItemFromCart.json";

    /**
     * 清空某个用户的购物车
     */
    public static final String EMPTY_CART = MAIN_URL + "/app/cart/emptyCartByUserId.json";

    /**
     * 修改购物车里某件商品的数量
     */
    public static final String NOTIFY_GOODS_COUNT = MAIN_URL + "/app/cart/modifyCart.json";

    /**
     * 提交购物车结算信息
     */
    public static final String SUBMIT_CART_ORDERS = MAIN_URL + "/app/order/submitCartOrders.json";

    /********************************订单详情页****************************************/
    /**
     * 提交订单
     */
    public static final String SUBMIT_FORM = MAIN_URL + "/app/order/submitOrder.json";

    /**
     * 获取用户所有的订单信息
     */
    public static final String GET_ALL_FORM_INFO = MAIN_URL + "/app/order/getUserOrderListByCond.json";

    /**
     * 获取单个订单信息
     */
    public static final String GET_SINGLE_FORM_INFO = MAIN_URL + "/app/order/getOrderInfoById.json";

    /**
     * 取消订单
     */
    public static final String CANCEL_FORM = MAIN_URL + "/app/order/cancelOrder.json";

    /**
     * 确定收货
     */
    public static final String ENSURE_GAIN_GOODS = MAIN_URL + "/app/order/confirmReceiving.json";

    /**
     * 删除订单
     */
    public static final String DELETE_FORM = MAIN_URL + "/app/order/deleteOrder.json";

    /**
     * 保存用户评价
     */
    public static final String SEND_COMMENT = MAIN_URL + "/app/order/userEvaluate.json";


    /************************售后客服**************************/
    /**
     * 买家查找某个售后订单的所有记录
     */
    public static final String AFTER_CONSULT_HISTORY = MAIN_URL + "/app/app/aftersale/getAfterConsultHistory.json";

    /**
     * 用户关闭咨询
     */
    public static final String CLOSE_AFTER_CONSULT = MAIN_URL + "/app/app/aftersale/closeAfterConsult.json";
    /**
     * 买家对某个订单的售后资讯追加发问（继续提问）
     */
    public static final String AGAIN_AFTER_CONSULT = MAIN_URL + "/app/app/aftersale/anotherAfterConsult.json";

    /**
     * 检查一个用户有否未读的售后回复
     */
    public static final String AFTER_IS_HAS_UNREAD = MAIN_URL + "/app/app/aftersale/isUnreadAfterConsultMsg.json";
    /**
     * 用户查找售后咨询列表
     */
    public static final String AFTER_CONSULT_LIST = MAIN_URL + "/app/app/aftersale/getAfterConsultList.json";


    /************************售前客服**************************/
    /**
     * 用户查找所有工单列表---售前
     */
    public static final String BEFORE_CONSULT_LIST = MAIN_URL + "/app/app/presale/getMainConsultList.json";
    /**
     * 查找某个工单的所有问答记录---售前
     */
    public static final String BEFORE_CONSULT_HISTORY = MAIN_URL + "/app/app/presale/getConsultHistory.json";

    /**
     * 用户关闭咨询---售前
     */
    public static final String CLOSE_BEFORE_CONSULT = MAIN_URL + "/app/app/presale/closeConsult.json";
    /**
     * 潜在买家对某个工号的资讯追加发问---售前
     */
    public static final String AGAIN_BEFORE_CONSULT = MAIN_URL + "/app/app/presale/anotherQuestion.json";

    /**
     * 潜在买家首次发起咨询--售前
     */
    public static final String FIRST_BEFORE_CONSULT = MAIN_URL + "/app/app/presale/launchConsult.json";

    /**
     * 检查一个用户有否未读的售前回复
     */
    public static final String BEFORE_IS_HAS_UNREAD = MAIN_URL + "/app/app/presale/isUnreadConsultExist.json";
    /**------------------------------------酒圈-------------------------------------------*/
    /**
     * 发布动态
     */
    public static final String SAVE_CONDITION = MAIN_URL + "/app/social/saveDynamic.json";
    /**
     * 获取推荐动态列表信息
     */
    public static final String RECOMMEND_CONDITION = MAIN_URL + "/app/social/getRecommendDynamicList.json";

    /**
     * 获取已关注的达人的动态列表
     */
    public static final String ATTENTION_CONDITION = MAIN_URL + "/app/social/getConcernedDynamicList.json";

    /**
     * 获取个人发布动态列表
     */
    public static final String PERSON_CONDITION = MAIN_URL + "/app/social/getDynamicListByUserId.json";

    /**
     * 为动态点赞
     */
    public static final String THUMB_DYNAMIC = MAIN_URL + "/app/social/thumbDynamic.json";

    /**
     * 删除动态
     */
    public static final String DELETE_CONDITION = MAIN_URL + "/app/social/deleteDynamicById.json";

    /**
     * 设置或取消仅自己可见
     */
    public static final String ONLY_MYSELF_LOOK = MAIN_URL + "/app/social/visibleOnlyByYourself.json";

    /**
     * 动态置顶
     */
    public static final String MOVE_CONDITION_TO_TOP = MAIN_URL + "/app/social/moveDynamicToTop.json";

    /**
     * 关注达人
     */
    public static final String CONCERN_EXPERT = MAIN_URL + "/app/social/concernExpert.json";
    /**
     * 转发动态信息
     */
    public static final String TRANSPORT_CONDITION = MAIN_URL + "/app/social/transportDynamic.json";

    /**
     * 评论动态信息
     */
    public static final String CONDITION_COM = MAIN_URL + "/app/social/commentDynamic.json";
    /**
     * 评论动态列表信息
     */
    public static final String CONDITION_COM_LIST = MAIN_URL + "/app/social/getCommentList.json";

    /**
     * 根据用户id获取用户信息
     */
    public static final String GET_USER_INFO_BY_ID = MAIN_URL + "/app/social/getUserInfoById.json";

    /**
     * 设置用户信息（返回最新的用户信息）
     */
    public static final String COMPILE_PERSON_INFO = MAIN_URL + "/app/social/setSocialUserInfo.json";

    /**
     * 获取推荐关注列表
     */
    public static final String RECOMMEND_LIST = MAIN_URL + "/app/social/getRecommendUserList.json";

    /**
     * 获取已关注列表
     */
    public static final String ATTENTION_LIST = MAIN_URL + "/app/social/getConcernList.json";

    /**
     * 获取粉丝列表
     */
    public static final String FANS_LIST = MAIN_URL + "/app/social/getFansList.json";

    /**------------------------------------支付接口-------------------------------------------*/
    /**
     * alipay支付接口
     */
    public static final String ALIPAY_SIGN = MAIN_URL + "/app/pay/alipay/getPaySign.json";

    /**
     * 微信支付接口
     */
    public static final String WEIXIN_SIGN = MAIN_URL + "/app/pay/weixin/getWeixinPayInfo.json";
    /**------------------------------------版本信息-------------------------------------------*/
    /**
     * 获取安卓最新的下载地址
     */
    public static final String GET_APK_DOWNLOAD = MAIN_URL + "/version/getAndroidDownloadAddress.json";

    /**
     * 获取版本信息
     */
    public static final String VERSION_INFO = MAIN_URL + "/version/getCurrentVersionInfo.json";

    /**------------------------------------优惠券-------------------------------------------*/
    /**
     * 根据订单ID获取分享优惠券地址
     */
    public static final String GET_COUPON_URL = MAIN_URL + "/app/order/getShareCouponUrlByOrderId.json";

    /**------------------------------------订单状态-------------------------------------------*/
    /**
     * 等待付款
     */
    public static final String WAIT_PAY = 10 + "";
    /**
     * 交易关闭
     */
    public static final String TRANSACTION_CLOSED = 0 + "";
    /**
     * 交易成功(有评论)
     */
    public static final String TRANSACTION_SUCCESS_HAS_COMMENT = 50 + "";
    /**
     * 交易成功（还未评论）
     */
    public static final String TRANSACTION_SUCCESS_NO_COMMENT = 40 + "";
    /**
     * 等待发货
     */
    public static final String WAIT_DELIVER_GOODS = 20 + "";
    /**
     * 等待收货
     */
    public static final String WAIT_GAIN_GOODS = 30 + "";

    /**
     * 退款和售后
     */
    public static final String SALE_AFTER = 45 + "";


}
