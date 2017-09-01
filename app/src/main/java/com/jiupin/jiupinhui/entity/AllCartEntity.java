package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/8/31 15:14
 */

public class AllCartEntity {


    /**
     * onSale : [{"storeId":32771,"storeName":"酒品会","storeLogo":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/eva_imgs4574820b-d1b5-4145-a667-c781901d13b5.jpg","goodList":[{"id":6453,"is_meal":1,"goods_name":"烈酒|白酒大师套餐","goods_price":999,"store_price":999,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170511/1f28809e-776a-45d7-9bde-4e0e1d2998d6.png","specArray":[{"spec_info":"月度会员（3次）","spec_id":"32775"},{"spec_info":"男神专属","spec_id":"32779"}],"count":2,"specIdsString":"32775_32779_"},{"id":6482,"is_meal":0,"goods_name":"芝华士12年 700ml","goods_price":260,"store_price":260,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/2d7ba1b5-f6b8-40df-a1b9-a68878f4e086.png","specArray":null,"count":1,"specIdsString":null}]},{"storeId":32769,"storeName":"宏达通信","storeLogo":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/eva_imgs4574820b-d1b5-4145-a667-c781901d13b5.jpg","goodList":[{"id":6374,"goods_name":"嘉士伯冰纯330ml","goods_price":null,"store_price":6,"path":null,"specArray":null,"count":4,"specIdsString":null}]}]
     * invalidGoods : 0
     * onHold : []
     */

    private int invalidGoods;
    private List<OnSaleBean> onSale;
    private List<?> onHold;

    public int getInvalidGoods() {
        return invalidGoods;
    }

    public void setInvalidGoods(int invalidGoods) {
        this.invalidGoods = invalidGoods;
    }

    public List<OnSaleBean> getOnSale() {
        return onSale;
    }

    public void setOnSale(List<OnSaleBean> onSale) {
        this.onSale = onSale;
    }

    public List<?> getOnHold() {
        return onHold;
    }

    public void setOnHold(List<?> onHold) {
        this.onHold = onHold;
    }

    public static class OnSaleBean {
        /**
         * storeId : 32771
         * storeName : 酒品会
         * storeLogo : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/eva_imgs4574820b-d1b5-4145-a667-c781901d13b5.jpg
         * goodList : [{"id":6453,"is_meal":1,"goods_name":"烈酒|白酒大师套餐","goods_price":999,"store_price":999,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170511/1f28809e-776a-45d7-9bde-4e0e1d2998d6.png","specArray":[{"spec_info":"月度会员（3次）","spec_id":"32775"},{"spec_info":"男神专属","spec_id":"32779"}],"count":2,"specIdsString":"32775_32779_"},{"id":6482,"is_meal":0,"goods_name":"芝华士12年 700ml","goods_price":260,"store_price":260,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/2d7ba1b5-f6b8-40df-a1b9-a68878f4e086.png","specArray":null,"count":1,"specIdsString":null}]
         */

        private int storeId;
        private String storeName;
        private String storeLogo;
        private List<GoodListBean> goodList;

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

        public List<GoodListBean> getGoodList() {
            return goodList;
        }

        public void setGoodList(List<GoodListBean> goodList) {
            this.goodList = goodList;
        }

        public static class GoodListBean {
            /**
             * id : 6453
             * is_meal : 1
             * goods_name : 烈酒|白酒大师套餐
             * goods_price : 999
             * store_price : 999
             * path : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170511/1f28809e-776a-45d7-9bde-4e0e1d2998d6.png
             * specArray : [{"spec_info":"月度会员（3次）","spec_id":"32775"},{"spec_info":"男神专属","spec_id":"32779"}]
             * count : 2
             * specIdsString : 32775_32779_
             */

            private int id;
            private int is_meal;
            private String goods_name;
            private double goods_price;
            private double store_price;
            private String path;
            private int count;
            private String specIdsString;
            private List<SpecEntity> specArray;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_meal() {
                return is_meal;
            }

            public void setIs_meal(int is_meal) {
                this.is_meal = is_meal;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
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

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getSpecIdsString() {
                return specIdsString;
            }

            public void setSpecIdsString(String specIdsString) {
                this.specIdsString = specIdsString;
            }

            public List<SpecEntity> getSpecArray() {
                return specArray;
            }

            public void setSpecArray(List<SpecEntity> specArray) {
                this.specArray = specArray;
            }
        }
    }
}
