package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/7/26 10:24
 */

public class WineInfoEntity{


    /**
     * id : 92
     * goods_name : 38度古贝春五粮窖藏3号
     * good_subtitle :
     * goods_intro :
     * begin_date :
     * goods_price : 0.0
     * store_price : 98.0
     * goods_store_id : 32769
     * path : http://jiupin-static.img-cn-hangzhou.aliyuncs.com/goods/cover_img/3962687a39329fababddf497e0774944.jpg
     */

    private int id;
    private String goods_name;
    private String good_subtitle;
    private String goods_intro;
    private String begin_date;
    private double goods_price;
    private double store_price;
    private int goods_store_id;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGood_subtitle() {
        return good_subtitle;
    }

    public void setGood_subtitle(String good_subtitle) {
        this.good_subtitle = good_subtitle;
    }

    public String getGoods_intro() {
        return goods_intro;
    }

    public void setGoods_intro(String goods_intro) {
        this.goods_intro = goods_intro;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public double getStore_price() {
        return store_price;
    }

    public void setStore_price(double store_price) {
        this.store_price = store_price;
    }

    public int getGoods_store_id() {
        return goods_store_id;
    }

    public void setGoods_store_id(int goods_store_id) {
        this.goods_store_id = goods_store_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
