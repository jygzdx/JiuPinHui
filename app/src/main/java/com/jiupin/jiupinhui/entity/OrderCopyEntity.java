package com.jiupin.jiupinhui.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：czb on 2017/9/5 09:58
 */

public class OrderCopyEntity implements Serializable{


    /**
     * cartPaymentId : 000000328222017082606335044
     * cartPaymentAmount : 12195.3
     * couponAmount : 0.0
     * address : {"id":32866,"addTime":1503629242000,"deleteStatus":false,"trueName":"石乐志","area":"","area_main":"广东省 广州市 海珠区 ","area_info":"滴滴滴路","zip":"","telephone":"","mobile":"13560301401","is_default":1,"user":""}
     * storeListInCart : [{"storeId":32771,"storeName":"酒品会","storeLogo":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/eva_imgs4574820b-d1b5-4145-a667-c781901d13b5.jpg","orderList":[{"id":"229791","order_id":"0000003282220170826063350","addTime":1503700430759,"order_status":"10","goods_amount":168,"ship_price":0,"totalPrice":168,"company_mark":"","shipCode":"","orderDetailList":[{"id":6456,"prod_name":"酒神入门套餐","prod_image":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170524/a489b98e-02d0-40d0-9fa8-4a87d1c3bd7e.png","prod_count":1,"prod_price":168,"prod_spec_text":"男神专属月度会员（3次）","begin_time":13132131321321321,"end_time":13132131321321321,"is_renewable":true,"receiver_name":""}]},{"id":"229792","order_id":"0000003282220170826063350","addTime":1503700430799,"order_status":"10","goods_amount":11988,"ship_price":0,"totalPrice":11988,"company_mark":null,"shipCode":null,"orderDetailList":[{"id":6453,"prod_name":"烈酒|白酒大师套餐","prod_image":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170511/1f28809e-776a-45d7-9bde-4e0e1d2998d6.png","prod_count":1,"prod_price":11988,"prod_spec_text":"全年会员（36次）女神专属","begin_time":null,"end_time":null,"is_renewable":null,"receiver_name":null}]},{"id":"229793","order_id":"0000003282220170826063350","addTime":1503700430809,"order_status":"10","goods_amount":39.2,"ship_price":0,"totalPrice":39.2,"company_mark":null,"shipCode":null,"orderDetailList":[{"id":6465,"prod_name":"墨西哥原瓶进口 科罗娜啤酒 Corona 啤酒 瓶装330ml","prod_image":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170531/40d3f23a-378e-4d74-b2a6-a5e356a2a6f8.png","prod_count":4,"prod_price":9.8,"prod_spec_text":null,"begin_time":null,"end_time":null,"is_renewable":null,"receiver_name":null}]}]},{"storeId":32769,"storeName":"宏达通信","storeLogo":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/eva_imgs4574820b-d1b5-4145-a667-c781901d13b5.jpg","orderList":[{"id":"229794","order_id":"0000003282220170826063350","addTime":1503700430985,"order_status":"10","goods_amount":0.1,"ship_price":0,"totalPrice":0.1,"company_mark":"","shipCode":"","orderDetailList":[{"id":6438,"prod_name":"路易之花百香果味葡萄酒","prod_image":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/goods/cover_img/d03e21e34e57e80a97d5bf1e9d9c62b1.jpg","prod_count":5,"prod_price":0.02,"prod_spec_text":null,"begin_time":null,"end_time":null,"is_renewable":null,"receiver_name":null}]}]}]
     * invoiceType : 2
     * invoice : 好极有限公司
     * invoiceCode :
     * invoiceDesc : 葡萄酒
     */

    private String cartPaymentId;
    private double cartPaymentAmount;
    private double couponAmount;
    private AddressBean address;
    private int invoiceType;
    private String invoice;
    private String invoiceCode;
    private String invoiceDesc;
    private List<StoreListInCartBean> storeListInCart;

    public String getCartPaymentId() {
        return cartPaymentId;
    }

    public void setCartPaymentId(String cartPaymentId) {
        this.cartPaymentId = cartPaymentId;
    }

    public double getCartPaymentAmount() {
        return cartPaymentAmount;
    }

    public void setCartPaymentAmount(double cartPaymentAmount) {
        this.cartPaymentAmount = cartPaymentAmount;
    }

    public double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public int getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceDesc() {
        return invoiceDesc;
    }

    public void setInvoiceDesc(String invoiceDesc) {
        this.invoiceDesc = invoiceDesc;
    }

    public List<StoreListInCartBean> getStoreListInCart() {
        return storeListInCart;
    }

    public void setStoreListInCart(List<StoreListInCartBean> storeListInCart) {
        this.storeListInCart = storeListInCart;
    }

    public static class AddressBean implements Serializable{
        /**
         * id : 32866
         * addTime : 1503629242000
         * deleteStatus : false
         * trueName : 石乐志
         * area :
         * area_main : 广东省 广州市 海珠区
         * area_info : 滴滴滴路
         * zip :
         * telephone :
         * mobile : 13560301401
         * is_default : 1
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
        private int is_default;
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

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }

    public static class StoreListInCartBean implements Serializable{
        /**
         * storeId : 32771
         * storeName : 酒品会
         * storeLogo : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/eva_imgs4574820b-d1b5-4145-a667-c781901d13b5.jpg
         * orderList : [{"id":"229791","order_id":"0000003282220170826063350","addTime":1503700430759,"order_status":"10","goods_amount":168,"ship_price":0,"totalPrice":168,"company_mark":"","shipCode":"","orderDetailList":[{"id":6456,"prod_name":"酒神入门套餐","prod_image":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170524/a489b98e-02d0-40d0-9fa8-4a87d1c3bd7e.png","prod_count":1,"prod_price":168,"prod_spec_text":"男神专属月度会员（3次）","begin_time":13132131321321321,"end_time":13132131321321321,"is_renewable":true,"receiver_name":""}]},{"id":"229792","order_id":"0000003282220170826063350","addTime":1503700430799,"order_status":"10","goods_amount":11988,"ship_price":0,"totalPrice":11988,"company_mark":null,"shipCode":null,"orderDetailList":[{"id":6453,"prod_name":"烈酒|白酒大师套餐","prod_image":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170511/1f28809e-776a-45d7-9bde-4e0e1d2998d6.png","prod_count":1,"prod_price":11988,"prod_spec_text":"全年会员（36次）女神专属","begin_time":null,"end_time":null,"is_renewable":null,"receiver_name":null}]},{"id":"229793","order_id":"0000003282220170826063350","addTime":1503700430809,"order_status":"10","goods_amount":39.2,"ship_price":0,"totalPrice":39.2,"company_mark":null,"shipCode":null,"orderDetailList":[{"id":6465,"prod_name":"墨西哥原瓶进口 科罗娜啤酒 Corona 啤酒 瓶装330ml","prod_image":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170531/40d3f23a-378e-4d74-b2a6-a5e356a2a6f8.png","prod_count":4,"prod_price":9.8,"prod_spec_text":null,"begin_time":null,"end_time":null,"is_renewable":null,"receiver_name":null}]}]
         */

        private int storeId;
        private String storeName;
        private String storeLogo;
        private List<OrderListBean> orderList;

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreLogo() {
            return storeLogo;
        }

        public void setStoreLogo(String storeLogo) {
            this.storeLogo = storeLogo;
        }

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean implements Serializable{
            /**
             * id : 229791
             * order_id : 0000003282220170826063350
             * addTime : 1503700430759
             * order_status : 10
             * goods_amount : 168.0
             * ship_price : 0.0
             * totalPrice : 168.0
             * company_mark :
             * shipCode :
             * orderDetailList : [{"id":6456,"prod_name":"酒神入门套餐","prod_image":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170524/a489b98e-02d0-40d0-9fa8-4a87d1c3bd7e.png","prod_count":1,"prod_price":168,"prod_spec_text":"男神专属月度会员（3次）","begin_time":13132131321321321,"end_time":13132131321321321,"is_renewable":true,"receiver_name":""}]
             */

            private String id;
            private String order_id;
            private long addTime;
            private String order_status;
            private double goods_amount;
            private double ship_price;
            private double totalPrice;
            private String company_mark;
            private String shipCode;
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

            public String getCompany_mark() {
                return company_mark;
            }

            public void setCompany_mark(String company_mark) {
                this.company_mark = company_mark;
            }

            public String getShipCode() {
                return shipCode;
            }

            public void setShipCode(String shipCode) {
                this.shipCode = shipCode;
            }

            public List<OrderDetailListBean> getOrderDetailList() {
                return orderDetailList;
            }

            public void setOrderDetailList(List<OrderDetailListBean> orderDetailList) {
                this.orderDetailList = orderDetailList;
            }

            public static class OrderDetailListBean implements Serializable{
                /**
                 * id : 6456
                 * prod_name : 酒神入门套餐
                 * prod_image : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170524/a489b98e-02d0-40d0-9fa8-4a87d1c3bd7e.png
                 * prod_count : 1
                 * prod_price : 168.0
                 * prod_spec_text : 男神专属月度会员（3次）
                 * begin_time : 13132131321321321
                 * end_time : 13132131321321321
                 * is_renewable : true
                 * receiver_name :
                 */

                private int id;
                private String prod_name;
                private String prod_image;
                private int prod_count;
                private double prod_price;
                private String prod_spec_text;
                private long begin_time;
                private long end_time;
                private boolean is_renewable;
                private String receiver_name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getProd_name() {
                    return prod_name;
                }

                public void setProd_name(String prod_name) {
                    this.prod_name = prod_name;
                }

                public String getProd_image() {
                    return prod_image;
                }

                public void setProd_image(String prod_image) {
                    this.prod_image = prod_image;
                }

                public int getProd_count() {
                    return prod_count;
                }

                public void setProd_count(int prod_count) {
                    this.prod_count = prod_count;
                }

                public double getProd_price() {
                    return prod_price;
                }

                public void setProd_price(double prod_price) {
                    this.prod_price = prod_price;
                }

                public String getProd_spec_text() {
                    return prod_spec_text;
                }

                public void setProd_spec_text(String prod_spec_text) {
                    this.prod_spec_text = prod_spec_text;
                }

                public long getBegin_time() {
                    return begin_time;
                }

                public void setBegin_time(long begin_time) {
                    this.begin_time = begin_time;
                }

                public long getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(long end_time) {
                    this.end_time = end_time;
                }

                public boolean isIs_renewable() {
                    return is_renewable;
                }

                public void setIs_renewable(boolean is_renewable) {
                    this.is_renewable = is_renewable;
                }

                public String getReceiver_name() {
                    return receiver_name;
                }

                public void setReceiver_name(String receiver_name) {
                    this.receiver_name = receiver_name;
                }
            }
        }
    }
}
