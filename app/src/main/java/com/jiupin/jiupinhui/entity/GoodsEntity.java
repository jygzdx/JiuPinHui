package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/7/3 15:57
 */

public class GoodsEntity {


    /**
     * status : 200
     * msg : OK
     * data : {"id":6451,"goods_name":"葡萄酒类中级会员","good_subtitle":"3次零售价299-499区间的精品葡萄酒","goods_price":399,"store_price":399,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/34546f7c-5f2b-439e-aec6-fd327f264588.jpg","goods_inventory":400000,"goods_inventory_detail":[{"id":"32775_","price":"399.0","count":"100000","memberPrice":"399.0"},{"id":"32776_","price":"1173","count":"100000","memberPrice":"1173"},{"id":"32777_","price":"2299","count":"100000","memberPrice":"2299"},{"id":"32778_","price":"4506","count":"100000","memberPrice":"4506"}],"goods_intro":"","goods_transfee":1,"goods_store_id":32771,"store_name":"酒品会","goods_click":24,"is_meal":1,"specifications":[{"id":32769,"name":"购买会员","properties":[{"id":32775,"value":"月度会员（3次）","path":"upload/advert0e706092-9910-4522-9613-6331e170933e.jpg"},{"id":32776,"value":"季度会员（9次）","path":null},{"id":32777,"value":"半年会员（18次）","path":null},{"id":32778,"value":"全年会员（36次）","path":null}]}],"goods_details":"<p> 红酒中档 <\/p> <p> 中档红酒 <\/p>","prodPhoto":[{"id":432044,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/2686d97a-b717-46b7-8b35-def35f691c7b.jpg"}]}
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
        /**
         * id : 6451
         * goods_name : 葡萄酒类中级会员
         * good_subtitle : 3次零售价299-499区间的精品葡萄酒
         * goods_price : 399.0
         * store_price : 399.0
         * path : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/34546f7c-5f2b-439e-aec6-fd327f264588.jpg
         * goods_inventory : 400000
         * goods_inventory_detail : [{"id":"32775_","price":"399.0","count":"100000","memberPrice":"399.0"},{"id":"32776_","price":"1173","count":"100000","memberPrice":"1173"},{"id":"32777_","price":"2299","count":"100000","memberPrice":"2299"},{"id":"32778_","price":"4506","count":"100000","memberPrice":"4506"}]
         * goods_intro :
         * goods_transfee : 1
         * goods_store_id : 32771
         * store_name : 酒品会
         * goods_click : 24
         * is_meal : 1
         * specifications : [{"id":32769,"name":"购买会员","properties":[{"id":32775,"value":"月度会员（3次）","path":"upload/advert0e706092-9910-4522-9613-6331e170933e.jpg"},{"id":32776,"value":"季度会员（9次）","path":null},{"id":32777,"value":"半年会员（18次）","path":null},{"id":32778,"value":"全年会员（36次）","path":null}]}]
         * goods_details : <p> 红酒中档 </p> <p> 中档红酒 </p>
         * prodPhoto : [{"id":432044,"path":"http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/2686d97a-b717-46b7-8b35-def35f691c7b.jpg"}]
         */

        private int id;
        private String goods_name;
        private String good_subtitle;
        private double goods_price;
        private double store_price;
        private String path;
        private int goods_inventory;
        private String goods_intro;
        private int goods_transfee;
        private int goods_store_id;
        private String store_name;
        private int goods_click;
        private int is_meal;
        private String goods_details;
        private String goods_inventory_detail;
        private List<SpecificationsBean> specifications;
        private List<ProdPhotoBean> prodPhoto;
        private List<Detail> details;

        public List<Detail> getDetails() {
            return details;
        }

        public void setDetails(List<Detail> details) {
            this.details = details;
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

        public int getGoods_inventory() {
            return goods_inventory;
        }

        public void setGoods_inventory(int goods_inventory) {
            this.goods_inventory = goods_inventory;
        }

        public String getGoods_intro() {
            return goods_intro;
        }

        public void setGoods_intro(String goods_intro) {
            this.goods_intro = goods_intro;
        }

        public int getGoods_transfee() {
            return goods_transfee;
        }

        public void setGoods_transfee(int goods_transfee) {
            this.goods_transfee = goods_transfee;
        }

        public int getGoods_store_id() {
            return goods_store_id;
        }

        public void setGoods_store_id(int goods_store_id) {
            this.goods_store_id = goods_store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public int getGoods_click() {
            return goods_click;
        }

        public void setGoods_click(int goods_click) {
            this.goods_click = goods_click;
        }

        public int getIs_meal() {
            return is_meal;
        }

        public void setIs_meal(int is_meal) {
            this.is_meal = is_meal;
        }

        public String getGoods_details() {
            return goods_details;
        }

        public void setGoods_details(String goods_details) {
            this.goods_details = goods_details;
        }

        public String getGoods_inventory_detail() {
            return goods_inventory_detail;
        }

        public void setGoods_inventory_detail(String goods_inventory_detail) {
            this.goods_inventory_detail = goods_inventory_detail;
        }

        public List<SpecificationsBean> getSpecifications() {
            return specifications;
        }

        public void setSpecifications(List<SpecificationsBean> specifications) {
            this.specifications = specifications;
        }

        public List<ProdPhotoBean> getProdPhoto() {
            return prodPhoto;
        }

        public void setProdPhoto(List<ProdPhotoBean> prodPhoto) {
            this.prodPhoto = prodPhoto;
        }

        public static class Detail {


            /**
             * id : 32775_
             * price : 178
             * count : 100000
             * memberPrice : 178
             */

            private String id;
            private String price;
            private String count;
            private String memberPrice;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getMemberPrice() {
                return memberPrice;
            }

            public void setMemberPrice(String memberPrice) {
                this.memberPrice = memberPrice;
            }
        }

        public static class SpecificationsBean {
            /**
             * id : 32769
             * name : 购买会员
             * properties : [{"id":32775,"value":"月度会员（3次）","path":"upload/advert0e706092-9910-4522-9613-6331e170933e.jpg"},{"id":32776,"value":"季度会员（9次）","path":null},{"id":32777,"value":"半年会员（18次）","path":null},{"id":32778,"value":"全年会员（36次）","path":null}]
             */

            private int id;
            private String name;
            private List<PropertiesBean> properties;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<PropertiesBean> getProperties() {
                return properties;
            }

            public void setProperties(List<PropertiesBean> properties) {
                this.properties = properties;
            }

            public static class PropertiesBean {
                /**
                 * id : 32775
                 * value : 月度会员（3次）
                 * path : upload/advert0e706092-9910-4522-9613-6331e170933e.jpg
                 */

                private int id;
                private String value;
                private String path;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }
            }
        }

        public static class ProdPhotoBean {
            /**
             * id : 432044
             * path : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/app/store/20170601/2686d97a-b717-46b7-8b35-def35f691c7b.jpg
             */

            private int id;
            private String path;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
