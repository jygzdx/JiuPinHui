package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/7/31 15:50
 */

public class AppraiseEntity {

    /**
     * id : 11
     * addTime : 1501124549000
     * evaluate_buyer_val : 1
     * evaluate_info : 不好
     * evaluate_seller_info :
     * evaluate_seller_time :
     * evaluate_seller_val : 0
     * goods_spec : 购买会员:季度会员（9次）
     * evaluate_goods_id : 6443
     * evaluate_seller_user_id : 0
     * evaluate_user_id : 32792
     * userName : Rare
     * imageUrl : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/user_logo_image/96076fd0-f7ff-4fbe-bebe-5e9ae64bdb6f.jpg
     * of_id : 229685
     * description_evaluate : 3
     * service_evaluate : 3
     * ship_evaluate : 3
     * image_list : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/eva_imgs16a77755-fbb3-415c-b6e8-e918a27706bb.jpg
     * rating : 3
     */

    private int id;
    private long addTime;
    private int evaluate_buyer_val;
    private String evaluate_info;
    private String evaluate_seller_info;
    private String evaluate_seller_time;
    private int evaluate_seller_val;
    private String goods_spec;
    private int evaluate_goods_id;
    private int evaluate_seller_user_id;
    private int evaluate_user_id;
    private String userName;
    private String imageUrl;
    private int of_id;
    private int description_evaluate;
    private int service_evaluate;
    private int ship_evaluate;
    private String image_list;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public int getEvaluate_buyer_val() {
        return evaluate_buyer_val;
    }

    public void setEvaluate_buyer_val(int evaluate_buyer_val) {
        this.evaluate_buyer_val = evaluate_buyer_val;
    }

    public String getEvaluate_info() {
        return evaluate_info;
    }

    public void setEvaluate_info(String evaluate_info) {
        this.evaluate_info = evaluate_info;
    }

    public String getEvaluate_seller_info() {
        return evaluate_seller_info;
    }

    public void setEvaluate_seller_info(String evaluate_seller_info) {
        this.evaluate_seller_info = evaluate_seller_info;
    }

    public String getEvaluate_seller_time() {
        return evaluate_seller_time;
    }

    public void setEvaluate_seller_time(String evaluate_seller_time) {
        this.evaluate_seller_time = evaluate_seller_time;
    }

    public int getEvaluate_seller_val() {
        return evaluate_seller_val;
    }

    public void setEvaluate_seller_val(int evaluate_seller_val) {
        this.evaluate_seller_val = evaluate_seller_val;
    }

    public String getGoods_spec() {
        return goods_spec;
    }

    public void setGoods_spec(String goods_spec) {
        this.goods_spec = goods_spec;
    }

    public int getEvaluate_goods_id() {
        return evaluate_goods_id;
    }

    public void setEvaluate_goods_id(int evaluate_goods_id) {
        this.evaluate_goods_id = evaluate_goods_id;
    }

    public int getEvaluate_seller_user_id() {
        return evaluate_seller_user_id;
    }

    public void setEvaluate_seller_user_id(int evaluate_seller_user_id) {
        this.evaluate_seller_user_id = evaluate_seller_user_id;
    }

    public int getEvaluate_user_id() {
        return evaluate_user_id;
    }

    public void setEvaluate_user_id(int evaluate_user_id) {
        this.evaluate_user_id = evaluate_user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getOf_id() {
        return of_id;
    }

    public void setOf_id(int of_id) {
        this.of_id = of_id;
    }

    public int getDescription_evaluate() {
        return description_evaluate;
    }

    public void setDescription_evaluate(int description_evaluate) {
        this.description_evaluate = description_evaluate;
    }

    public int getService_evaluate() {
        return service_evaluate;
    }

    public void setService_evaluate(int service_evaluate) {
        this.service_evaluate = service_evaluate;
    }

    public int getShip_evaluate() {
        return ship_evaluate;
    }

    public void setShip_evaluate(int ship_evaluate) {
        this.ship_evaluate = ship_evaluate;
    }

    public String getImage_list() {
        return image_list;
    }

    public void setImage_list(String image_list) {
        this.image_list = image_list;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
