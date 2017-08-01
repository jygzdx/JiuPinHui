package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class FormEntity {

    /**
     * id : 229655
     * order_id : 0000003279220170706095014
     * addTime : 1499305814000
     * order_status : 0
     * goods_amount : 178.0
     * ship_price : 0.0
     * totalPrice : 178.0
     * storeInfo : {"id":32771,"store_name":"酒品会","store_img":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/eva_imgs4574820b-d1b5-4145-a667-c781901d13b5.jpg","attention_num":0,"goods_num":0,"dynamic_num":0}
     * orderDetailList : [{"goods_id":6452,"goods_name":"VIP会员·精酿葡萄酒入门尝鲜定制","main_photo":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/e0698b2c-3b7b-4a12-babc-934aee705bf7.jpg","count":1,"price":178,"spec_info":"购买会员:月度会员（3次） "}]
     */

    private String id;
    private String order_id;
    private long addTime;
    private String order_status;
    private double goods_amount;
    private double ship_price;
    private double totalPrice;
    private StoreInfoBean storeInfo;
    private List<OrderDetailListBean> orderDetailList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public double getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(double goods_amount) {
        this.goods_amount = goods_amount;
    }

    public double getShip_price() {
        return ship_price;
    }

    public void setShip_price(double ship_price) {
        this.ship_price = ship_price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public StoreInfoBean getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfoBean storeInfo) {
        this.storeInfo = storeInfo;
    }

    public List<OrderDetailListBean> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailListBean> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public static class StoreInfoBean {
        /**
         * id : 32771
         * store_name : 酒品会
         * store_img : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/eva_imgs4574820b-d1b5-4145-a667-c781901d13b5.jpg
         * attention_num : 0.0
         * goods_num : 0.0
         * dynamic_num : 0.0
         */

        private int id;
        private String store_name;
        private String store_img;
        private double attention_num;
        private double goods_num;
        private double dynamic_num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_img() {
            return store_img;
        }

        public void setStore_img(String store_img) {
            this.store_img = store_img;
        }

        public double getAttention_num() {
            return attention_num;
        }

        public void setAttention_num(double attention_num) {
            this.attention_num = attention_num;
        }

        public double getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(double goods_num) {
            this.goods_num = goods_num;
        }

        public double getDynamic_num() {
            return dynamic_num;
        }

        public void setDynamic_num(double dynamic_num) {
            this.dynamic_num = dynamic_num;
        }
    }

    public static class OrderDetailListBean {
        /**
         * goods_id : 6452
         * goods_name : VIP会员·精酿葡萄酒入门尝鲜定制
         * main_photo : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/e0698b2c-3b7b-4a12-babc-934aee705bf7.jpg
         * count : 1
         * price : 178.0
         * spec_info : 购买会员:月度会员（3次）
         */

        private int id;
        private String goods_name;
        private String main_photo;
        private int count;
        private double price;
        private String spec_info;

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

        public String getMain_photo() {
            return main_photo;
        }

        public void setMain_photo(String main_photo) {
            this.main_photo = main_photo;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getSpec_info() {
            return spec_info;
        }

        public void setSpec_info(String spec_info) {
            this.spec_info = spec_info;
        }

        @Override
        public String toString() {
            return "OrderDetailListBean{" +
                    "id=" + id +
                    ", goods_name='" + goods_name + '\'' +
                    ", main_photo='" + main_photo + '\'' +
                    ", count=" + count +
                    ", price=" + price +
                    ", spec_info='" + spec_info + '\'' +
                    '}';
        }
    }
}
