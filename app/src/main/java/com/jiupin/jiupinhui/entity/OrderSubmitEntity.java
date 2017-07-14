package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/7/14 15:14
 */

public class OrderSubmitEntity {

    @Override
    public String toString() {
        return "OrderSubmitEntity{" +
                "coupon=" + coupon.toString() +
                ", store=" + store.toString() +
                ", address=" + address.toString() +
                ", order=" + order.toString() +
                ", cart=" + cart.toString() +
                '}';
    }

    /**
     * coupon : {"id":3,"addTime":"","deleteStatus":false,"coupon_name":"20元优惠券","coupon_amount":20,"coupon_begin_time":"","coupon_end_time":"","coupon_count":0,"coupon_order_amount":"","coupon_acc":"","couponinfos":[]}
     * store : {"store_name":"兔兔集中营","store_address":""}
     * address : {"id":32778,"addTime":1493975853000,"deleteStatus":false,"trueName":"啦啦啦","area":null,"area_info":"穷山恶水123","zip":"510261","telephone":"","mobile":"13560301401","user":""}
     * order : {"id":229476,"order_id":"0000003279820170507170011","goods_amount":646,"totalPrice":646,"msg":"周四送达","transport":"平邮","order_status":10,"addTime":1494147611000,"ship_price":0}
     * cart : [{"count":100,"price":6.66,"origin_price":2394,"goods_name":"陈钺测试套餐多属性","spec_info":"购买月份:3月 颜色:黑色 "}]
     */

    private CouponBean coupon;
    private StoreBean store;
    private AddressBean address;
    private OrderBean order;
    private List<CartBean> cart;

    public CouponBean getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponBean coupon) {
        this.coupon = coupon;
    }

    public StoreBean getStore() {
        return store;
    }

    public void setStore(StoreBean store) {
        this.store = store;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CouponBean {
        @Override
        public String toString() {
            return "CouponBean{" +
                    "id=" + id +
                    ", addTime='" + addTime + '\'' +
                    ", deleteStatus=" + deleteStatus +
                    ", coupon_name='" + coupon_name + '\'' +
                    ", coupon_amount=" + coupon_amount +
                    ", coupon_begin_time='" + coupon_begin_time + '\'' +
                    ", coupon_end_time='" + coupon_end_time + '\'' +
                    ", coupon_count=" + coupon_count +
                    ", coupon_order_amount='" + coupon_order_amount + '\'' +
                    ", coupon_acc='" + coupon_acc + '\'' +
                    ", couponinfos=" + couponinfos +
                    '}';
        }

        /**
         * id : 3
         * addTime :
         * deleteStatus : false
         * coupon_name : 20元优惠券
         * coupon_amount : 20
         * coupon_begin_time :
         * coupon_end_time :
         * coupon_count : 0
         * coupon_order_amount :
         * coupon_acc :
         * couponinfos : []
         */

        private int id;
        private String addTime;
        private boolean deleteStatus;
        private String coupon_name;
        private int coupon_amount;
        private String coupon_begin_time;
        private String coupon_end_time;
        private int coupon_count;
        private String coupon_order_amount;
        private String coupon_acc;
        private List<?> couponinfos;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public boolean isDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(boolean deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public int getCoupon_amount() {
            return coupon_amount;
        }

        public void setCoupon_amount(int coupon_amount) {
            this.coupon_amount = coupon_amount;
        }

        public String getCoupon_begin_time() {
            return coupon_begin_time;
        }

        public void setCoupon_begin_time(String coupon_begin_time) {
            this.coupon_begin_time = coupon_begin_time;
        }

        public String getCoupon_end_time() {
            return coupon_end_time;
        }

        public void setCoupon_end_time(String coupon_end_time) {
            this.coupon_end_time = coupon_end_time;
        }

        public int getCoupon_count() {
            return coupon_count;
        }

        public void setCoupon_count(int coupon_count) {
            this.coupon_count = coupon_count;
        }

        public String getCoupon_order_amount() {
            return coupon_order_amount;
        }

        public void setCoupon_order_amount(String coupon_order_amount) {
            this.coupon_order_amount = coupon_order_amount;
        }

        public String getCoupon_acc() {
            return coupon_acc;
        }

        public void setCoupon_acc(String coupon_acc) {
            this.coupon_acc = coupon_acc;
        }

        public List<?> getCouponinfos() {
            return couponinfos;
        }

        public void setCouponinfos(List<?> couponinfos) {
            this.couponinfos = couponinfos;
        }
    }

    public static class StoreBean {
        @Override
        public String toString() {
            return "StoreBean{" +
                    "store_name='" + store_name + '\'' +
                    ", store_address='" + store_address + '\'' +
                    '}';
        }

        /**
         * store_name : 兔兔集中营
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

    public static class AddressBean {
        @Override
        public String toString() {
            return "AddressBean{" +
                    "id=" + id +
                    ", addTime=" + addTime +
                    ", deleteStatus=" + deleteStatus +
                    ", trueName='" + trueName + '\'' +
                    ", area=" + area +
                    ", area_info='" + area_info + '\'' +
                    ", zip='" + zip + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", user='" + user + '\'' +
                    '}';
        }

        /**
         * id : 32778
         * addTime : 1493975853000
         * deleteStatus : false
         * trueName : 啦啦啦
         * area : null
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
        private Object area;
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

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
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
                    ", ship_price=" + ship_price +
                    '}';
        }

        /**
         * id : 229476
         * order_id : 0000003279820170507170011
         * goods_amount : 646.0
         * totalPrice : 646.0
         * msg : 周四送达
         * transport : 平邮
         * order_status : 10
         * addTime : 1494147611000
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

        public int getShip_price() {
            return ship_price;
        }

        public void setShip_price(int ship_price) {
            this.ship_price = ship_price;
        }
    }

    public static class CartBean {
        @Override
        public String toString() {
            return "CartBean{" +
                    "count=" + count +
                    ", price=" + price +
                    ", origin_price=" + origin_price +
                    ", goods_name='" + goods_name + '\'' +
                    ", spec_info='" + spec_info + '\'' +
                    '}';
        }

        /**
         * count : 100
         * price : 6.66
         * origin_price : 2394.0
         * goods_name : 陈钺测试套餐多属性
         * spec_info : 购买月份:3月 颜色:黑色
         */


        private int count;
        private double price;
        private double origin_price;
        private String goods_name;
        private String spec_info;

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

        public double getOrigin_price() {
            return origin_price;
        }

        public void setOrigin_price(double origin_price) {
            this.origin_price = origin_price;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getSpec_info() {
            return spec_info;
        }

        public void setSpec_info(String spec_info) {
            this.spec_info = spec_info;
        }
    }
}
