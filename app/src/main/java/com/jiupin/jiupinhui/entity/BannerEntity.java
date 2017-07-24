package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/7/21 15:32
 */

public class BannerEntity {


    /**
     * goods_id : 6440
     * img_url : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170511/f6288b2c-c027-4ac8-8697-25fc60ea73c9.png
     * type : 3
     * ad_url :
     */

    private int goods_id;
    private String img_url;
    private int type;
    private String ad_url;

    @Override
    public String toString() {
        return "BannerEntity{" +
                "goods_id=" + goods_id +
                ", img_url='" + img_url + '\'' +
                ", type=" + type +
                ", ad_url='" + ad_url + '\'' +
                '}';
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAd_url() {
        return ad_url;
    }

    public void setAd_url(String ad_url) {
        this.ad_url = ad_url;
    }
}
