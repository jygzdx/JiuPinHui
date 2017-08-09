package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/6/30 14:31
 */

public class HomeLoveEntity {


    /**
     * status : 200
     * msg : OK
     * data : {"list":[{"id":6451,"goods_name":"葡萄酒类中级会员","good_subtitle":"3次零售价299-499区间的精品葡萄酒","goods_intro":"","begin_date":"","goods_price":399,"store_price":399,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/34546f7c-5f2b-439e-aec6-fd327f264588.jpg"},{"id":6443,"goods_name":"葡萄酒类高级会员","good_subtitle":"3次零售价599-1199区间的精品葡萄酒","goods_intro":null,"begin_date":null,"goods_price":999,"store_price":999,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/2c571e49-c650-4a79-a87b-1a0842ea377a.jpg"},{"id":6455,"goods_name":"烈酒|白酒入门套餐","good_subtitle":"每周一次零售价60以上单价的烈酒","goods_intro":null,"begin_date":null,"goods_price":168,"store_price":168,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170511/f3953f91-9cbe-4d7e-9abb-20c111f6c3ed.png"},{"id":6454,"goods_name":"烈酒|白酒珍藏套餐","good_subtitle":"每周一次零售价135以上单价的烈酒","goods_intro":null,"begin_date":null,"goods_price":399,"store_price":399,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170511/4dae7599-3a25-4562-823c-da10c2502788.png"}]}
     */

    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 6451
             * goods_name : 葡萄酒类中级会员
             * good_subtitle : 3次零售价299-499区间的精品葡萄酒
             * goods_intro :
             * begin_date :
             * goods_price : 399.0
             * store_price : 399.0
             * goods_store_id : 32771
             * path : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/34546f7c-5f2b-439e-aec6-fd327f264588.jpg
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
            private int payment_num;

            public int getPayment_num() {
                return payment_num;
            }

            public void setPayment_num(int payment_num) {
                this.payment_num = payment_num;
            }

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
    }
}
