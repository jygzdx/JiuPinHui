package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:32
 */

public class HotRecommentEntity {

    /**
     * status : 200
     * msg : OK
     * data : {"list":[{"id":6448,"goods_name":"西班牙桃乐丝公牛血金标干红葡萄酒 750ml","good_subtitle":"适合与各种卤汁料理，野味和辣菜、酱鸭、鹅肝、红烧肉搭配。","goods_intro":"","begin_date":"1997","goods_price":399,"store_price":399,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170510/d565e805-0f81-4c83-8a5c-61bc3f55986f.jpg"},{"id":6449,"goods_name":"意大利奇雅酒庄布鲁奈罗红葡萄酒 2010年 布鲁奈罗DOCG","good_subtitle":"不是圆形也不是椭圆形","goods_intro":null,"begin_date":"2010","goods_price":777,"store_price":499,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170510/f921cf97-0464-4ed0-b69f-eed8703247e9.jpg"},{"id":6450,"goods_name":"法国十字木桐城堡2015 750ml ","good_subtitle":"拥有优雅柔和的黑莓果香伴随着新鲜的花香","goods_intro":null,"begin_date":"1957","goods_price":777,"store_price":555,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170510/2c3d1136-9f03-4d2d-8ea9-5d0ba42936e7.jpg"},{"id":6464,"goods_name":"莎力啤酒 250ml","good_subtitle":"马爹利（Martell）洋酒 名士（名仕）干邑白兰地 50ml","goods_intro":null,"begin_date":"","goods_price":6,"store_price":6,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170525/98e4b167-0458-4bdf-afad-8b12d4dde814.png"},{"id":6465,"goods_name":"墨西哥原瓶进口 科罗娜啤酒 Corona 啤酒 瓶装330ml","good_subtitle":"","goods_intro":null,"begin_date":"","goods_price":9.8,"store_price":9.8,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170531/40d3f23a-378e-4d74-b2a6-a5e356a2a6f8.png"},{"id":6466,"goods_name":"福佳白啤酒 比利时精酿啤酒原装福嘉小麦啤酒330ml","good_subtitle":"","goods_intro":null,"begin_date":"","goods_price":9.8,"store_price":9.8,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170531/f0017bf3-0a13-4485-8540-743b640686b1.png"},{"id":6467,"goods_name":"奔富BIN2 750ml","good_subtitle":"","goods_intro":null,"begin_date":"","goods_price":158,"store_price":158,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/ef178b16-38dc-423a-af4c-980d1b5ec469.jpg"},{"id":6468,"goods_name":"拉菲传奇-波尔多 750ml","good_subtitle":"","goods_intro":null,"begin_date":"","goods_price":99,"store_price":99,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/6e9cbb1c-17c3-4dda-8f4d-73f4fff6e085.jpg"},{"id":6469,"goods_name":"桃乐丝公牛血 750ml","good_subtitle":"","goods_intro":null,"begin_date":"","goods_price":148,"store_price":148,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/751505a3-fd8e-4205-80d1-f55a786ce21a.jpg"},{"id":6470,"goods_name":"奔富BIN28 750ml","good_subtitle":"","goods_intro":null,"begin_date":"","goods_price":296,"store_price":296,"goods_store_id":32771,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/d4edb4a9-3e2e-498c-af99-f8e11cf50cb5.jpg"}]}
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
             * id : 6448
             * goods_name : 西班牙桃乐丝公牛血金标干红葡萄酒 750ml
             * good_subtitle : 适合与各种卤汁料理，野味和辣菜、酱鸭、鹅肝、红烧肉搭配。
             * goods_intro :
             * begin_date : 1997
             * goods_price : 399.0
             * store_price : 399.0
             * goods_store_id : 32771
             * path : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170510/d565e805-0f81-4c83-8a5c-61bc3f55986f.jpg
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
    }
}
