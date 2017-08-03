package com.jiupin.jiupinhui.config;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Constant {

//    public static final String MAIN_URL = "http://nappt.9pin.com";
//测试ip
    public static final String MAIN_URL = "http://192.168.0.112:8080";
//服务器ip
//    public static final String MAIN_URL = "http://192.168.0.161:8080";
    //宇明
//    public static final String MAIN_URL = "http://192.168.0.103:8080";

    public static final String APP_ID = "wx420947e7a04ec1df";

    /**
     * 获取首页轮播图图片
     */
    public static final String BANNER_URL = MAIN_URL+"/appGoods/home/getBannerList.json";
    /**
     * 获取热门文章
     */
    public static final String ARTICLE_URL = MAIN_URL+"/app/article/getHotArticle.json";

    /**
     * 商城顶部轮播图
     */
    public static final String STORE_BANNER_URL = MAIN_URL+"/appGoods/mall/getBannerList.json";

    /**
     * 获取套餐分类
     */
    public static final String MEAL_TYPE_URL = MAIN_URL+"/appGoods/mall/getMealClass.json";

    /**
     * 获取套餐信息
     */
    public static final String MEAL_INFO_URL = MAIN_URL+"/appGoods/mall/getMealByClassId.json";

    /**
     * 获取热门品牌
     */
    public static final String WINE_BRAND_URL = MAIN_URL+"/appGoods/wine/getHotBrand.json";

    /**
     * 获取美酒商品列表
     */
    public static final String WINE_INFO_URL = MAIN_URL+"/appGoods/wine/getGoodWineList.json";
    /**
     * 根据品牌id获取商品列表
     */
    public static final String WINE_INFO_BY_ID_URL = MAIN_URL+"/appGoods/wine/getGoodsByBrandId.json";

    /**
     * 获取验证码
     */
    public static final String SECURITY_CODE_URL = MAIN_URL+"/appRegister/app/sendSmsMsg.json";

    /**
     * 注册用户
     */
    public static final String REGISTER_USER_URL = MAIN_URL+"/appRegister/app/registerUser.json";

    /**
     * 用户登录
     */
    public static final String LOGIN_USER_URL = MAIN_URL+"/appLogin/app/loginByMobilePwd.json";

    /**
     * 校验手机号码是否注册过
     */
    public static final String IS_MOBILE_UNIQUE_URL = MAIN_URL+"/appRegister/app/isMobileUnique.json";

    /**
     * 热门推荐商品
     */
    public static final String HOT_RECOMMENT_URL = MAIN_URL+"/appGoods/home/getGoodsList.json";

    /**
     * 推荐套餐
     */
    public static final String MAIN_SHOW_URL = MAIN_URL+"/appGoods/home/getMealsList.json";

    /**
     * 首页主推套餐与热门推荐整合
     */
    public static final String GOODS_AND_MEALS_LIST = MAIN_URL+"/appGoods/home/getGoodsAndMealsList.json";

    /**
     * 猜你喜欢
     */
    public static final String GUESS_LOVE_URL = MAIN_URL+"/appGoods/home/getYourFavorite.json";

    /**
     * 用户意见反馈
     */
    public static final String IDEA_BACK_URL = MAIN_URL+"/app/saveFeedback.json";

    /**
     * 检查token是否可用
     */
    public static final String CHECK_TOKEN = MAIN_URL+"/appLogin/app/isTokenAvailable.json";

    /**
     * 根据token获取用户数据
     */
    public static final String GET_USER_INFO = MAIN_URL+"/app/user/getUserInfo.json";

    /**
     * 根据token查找买家的各种状态订单的数量
     */
    public static final String GET_FORM_INFO = MAIN_URL+"/app/order/getUserOrderStatusCount.json";

    /**
     * 获取绑定手机验证码
     */
    public static final String BINDING_PHONE_SECURITY_CODE_URL = MAIN_URL+"/app/user/sendSmsMsg.json";

    /**
     * 修改手机号码
     */
    public static final String UPDATE_MOBILE_URL = MAIN_URL+"/app/user/updateMobile.json";

    /**
     * 修改昵称
     */
    public static final String UPDATE_NICKNAME_URL = MAIN_URL+"/app/user/updateNickName.json";
    /**
     * 上传图片
     */
    public static final String PUSH_HEAD_URL = MAIN_URL+"/app/user/updateHeadImg.json";

    /**
     * 重置密码
     */
    public static final String RESET_PASSWORD_URL = MAIN_URL+"/appLogin/app/resetPwd.json";

    /**
     * 获取重置密码验证码
     */
    public static final String RESET_CODE_URL = MAIN_URL+"/appLogin/app/sendSmsMsg.json";

    /**
     * 获取用户地址列表
     */
    public static final String ADDRESS_LIST = MAIN_URL+"/app/order/getUserAddress.json";

    /**
     * 获取用户默认地址
     */
    public static final String GET_DEFAULT_ADDRESS = MAIN_URL+"/app/order/getDefaultAddress.json";

    /**
     * 改变用户默认地址
     */
    public static final String CHANGE_DEFAULT_ADDRESS = MAIN_URL+"/app/order/setDefaultAddress.json";

    /**
     * 删除地址
     */
    public static final String DELETE_ADDRESS = MAIN_URL+"/app/order/deleteUserAddress.json";

    /**
     * 获取省市区地址
     */
    public static final String SHENG_SHI_QU_AREA = MAIN_URL+"/app/order/loadCascadeAreaList.json";


    /**
     * 上传地址
     */
    public static final String PUSH_ADDRESS = MAIN_URL+"/app/order/saveOrUpdateAddress.json";

    /**
     * 获取商品信息
     */
    public static final String GOODS_INFO = MAIN_URL+"/appGoods/home/getGoodsDetail.json";

    /**
     * 获取评论列表
     */
    public static final String APPRAISE_INFO = MAIN_URL+"/app/order/getEvaluate.json";

    /**
     * 提交订单
     */
    public static final String SUBMIT_FORM = MAIN_URL+"/app/order/submitOrder.json";

    /**
     * 获取用户所有的订单信息
     */
    public static final String GET_ALL_FORM_INFO = MAIN_URL+"/app/order/getUserOrderListByCond.json";

    /**
     * 获取单个订单信息
     */
    public static final String GET_SINGLE_FORM_INFO = MAIN_URL+"/app/order/getOrderInfoById.json";

    /**
     * 取消订单
     */
    public static final String CANCEL_FORM = MAIN_URL+"/app/order/cancelOrder.json";

    /**
     * 确定收货
     */
    public static final String ENSURE_GAIN_GOODS = MAIN_URL+"/app/order/confirmReceiving.json";

    /**
     * 删除订单
     */
    public static final String DELETE_FORM = MAIN_URL+"/app/order/deleteOrder.json";

    /**
     * 保存用户评价
     */
    public static final String SEND_COMMENT = MAIN_URL+"/app/order/userEvaluate.json";


    /************************售后客服**************************/
    /**
     * 买家查找某个售后订单的所有记录
     */
    public static final String AFTER_CONSULT_HISTORY = MAIN_URL+"/app/app/aftersale/getAfterConsultHistory.json";

    /**
     * 用户关闭咨询
     */
    public static final String CLOSE_AFTER_CONSULT = MAIN_URL+"/app/app/aftersale/closeAfterConsult.json";
    /**
     * 买家对某个订单的售后资讯追加发问（继续提问）
     */
    public static final String AGAIN_AFTER_CONSULT = MAIN_URL+"/app/app/aftersale/anotherAfterConsult.json";

    /**
     * 检查一个用户有否未读的售后回复
     */
    public static final String AFTER_IS_HAS_UNREAD = MAIN_URL+"/app/app/aftersale/isUnreadAfterConsultMsg.json";
    /**
     * 用户查找售后咨询列表
     */
    public static final String AFTER_CONSULT_LIST = MAIN_URL+"/app/app/aftersale/getAfterConsultList.json";


    /************************售前客服**************************/
    /**
     * 用户查找所有工单列表---售前
     */
    public static final String BEFORE_CONSULT_LIST = MAIN_URL+"/app/app/presale/getMainConsultList.json";
    /**
     * 查找某个工单的所有问答记录---售前
     */
    public static final String BEFORE_CONSULT_HISTORY = MAIN_URL+"/app/app/presale/getConsultHistory.json";

    /**
     * 用户关闭咨询---售前
     */
    public static final String CLOSE_BEFORE_CONSULT = MAIN_URL+"/app/app/presale/closeConsult.json";
    /**
     * 潜在买家对某个工号的资讯追加发问---售前
     */
    public static final String AGAIN_BEFORE_CONSULT = MAIN_URL+"/app/app/presale/anotherQuestion.json";

    /**
     * 潜在买家首次发起咨询--售前
     */
    public static final String FIRST_BEFORE_CONSULT = MAIN_URL+"/app/app/presale/launchConsult.json";

    /**
     * 检查一个用户有否未读的售前回复
     */
    public static final String BEFORE_IS_HAS_UNREAD = MAIN_URL+"/app/app/presale/isUnreadConsultExist.json";

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
