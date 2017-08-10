package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/7/18 11:26
 */

public class FormParticularEntity {

    @Override
    public String toString() {
        return "FormParticularEntity{" +
                "store=" + store +
                ", order=" + order +
                ", address=" + address +
                ", deadline='" + deadline + '\'' +
                ", cart=" + cart +
                '}';
    }

    /**
     * store : {"store_name":"酒品会","store_address":""}
     * order : {"id":229462,"order_id":"0000003279820170507101556","goods_amount":40.98,"totalPrice":40.98,"msg":"","transport":"平邮","order_status":10,"addTime":1494123356000,"shipTime":"","ship_price":0}
     * address : {"id":32778,"addTime":1493975853000,"deleteStatus":false,"trueName":"啦啦啦","area":"","area_main":"广东省广州市海珠区","area_info":"穷山恶水123","zip":"510261","telephone":"","mobile":"13560301401","user":""}
     * cart : [{"count":2,"price":10.5,"goods_name":"精酿啤酒入门套餐","main_photo":"","spec_info":"购买月份: 6月","goods_id":"6442"}]
     * deadline : 剩下-49天-6小时自动关闭
     * "shareUrl":null,优惠券分享地址
     * "delivery":null,
     */

    private StoreBean store;
    private OrderBean order;
    private AddressBean address;
    private String deadline;
    private List<CartBean> cart;
    private String shareUrl;

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public StoreBean getStore() {
        return store;
    }

    public void setStore(StoreBean store) {
        this.store = store;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class StoreBean {
        /**
         * store_name : 酒品会
         * store_address :
         */

        private String store_name;
        private String store_address;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }
    }

    public static class OrderBean {
        @Override
        public String toString() {
            return "OrderBean{" +
                    "id=" + id +
                    ", order_id='" + order_id + '\'' +
                    ", goods_amount=" + goods_amount +
                    ", totalPrice=" + totalPrice +
                    ", msg='" + msg + '\'' +
                    ", transport='" + transport + '\'' +
                    ", order_status=" + order_status +
                    ", addTime=" + addTime +
                    ", shipTime='" + shipTime + '\'' +
                    ", ship_price=" + ship_price +
                    '}';
        }

        /**
         * id : 229462
         * order_id : 0000003279820170507101556
         * goods_amount : 40.98
         * totalPrice : 40.98
         * msg :
         * transport : 平邮
         * order_status : 10
         * addTime : 1494123356000
         * shipTime :
         * ship_price : 0
         */



        private int id;
        private String order_id;
        private double goods_amount;
        private double totalPrice;
        private String msg;
        private String transport;
        private int order_status;
        private long addTime;
        private String shipTime;
        private int ship_price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public double getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(double goods_amount) {
            this.goods_amount = goods_amount;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getTransport() {
            return transport;
        }

        public void setTransport(String transport) {
            this.transport = transport;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getShipTime() {
            return shipTime;
        }

        public void setShipTime(String shipTime) {
            this.shipTime = shipTime;
        }

        public int getShip_price() {
            return ship_price;
        }

        public void setShip_price(int ship_price) {
            this.ship_price = ship_price;
        }
    }

    public static class AddressBean {
        /**
         * id : 32778
         * addTime : 1493975853000
         * deleteStatus : false
         * trueName : 啦啦啦
         * area :
         * area_main : 广东省广州市海珠区
         * area_info : 穷山恶水123
         * zip : 510261
         * telephone :
         * mobile : 13560301401
         * user :
         */

        private int id;
        private long addTime;
        private boolean deleteStatus;
        private String trueName;
        private String area;
        private String area_main;
        private String area_info;
        private String zip;
        private String telephone;
        private String mobile;
        private String user;

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

        public boolean isDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(boolean deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public String getTrueName() {
            return trueName;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getArea_main() {
            return area_main;
        }

        public void setArea_main(String area_main) {
            this.area_main = area_main;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }

    public static class CartBean {
        /**
         * count : 2
         * price : 10.5
         * origin_price:原价格
         * goods_name : 精酿啤酒入门套餐
         * main_photo :
         * spec_info : 购买月份: 6月
         * goods_id : 6442
         */

        private int count;
        private double price;
        private double origin_price;

        private String goods_name;
        private String main_photo;
        private String spec_info;
        private String goods_id;

        public double getOrigin_price() {
            return origin_price;
        }

        public void setOrigin_price(double origin_price) {
            this.origin_price = origin_price;
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

        public String getSpec_info() {
            return spec_info;
        }

        public void setSpec_info(String spec_info) {
            this.spec_info = spec_info;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }
    }
}
