package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public class WineBrand {

    /**
     * status : 200
     * msg : OK
     * data : {"total":543,"info":[{"id":32,"name":"五粮液","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/a535947f282650e26d5d123ac99e9c1f.jpg","categoryid":2,"sort":0},{"id":33,"name":"茅台","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/8d1dd13918841614840ed05eb7adfdb5.jpg","categoryid":2,"sort":0},{"id":34,"name":"泸州老窖","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/cbfcdf0c0e5850686865667baa7c2687.jpg","categoryid":2,"sort":0},{"id":35,"name":"剑南春","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/4075c7520cb2add52344d95dd8a18ba9.jpg","categoryid":2,"sort":0},{"id":37,"name":"汾酒","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/5f6f6bf14bc45179c7dbfc8a2f366440.jpg","categoryid":2,"sort":0},{"id":39,"name":"郎酒","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/4f0a34d87e71a6ab95f273f1667308b8.jpg","categoryid":2,"sort":0},{"id":40,"name":"牛栏山","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/d1404fcdd6d663d0b3968c99ecf58768.jpg","categoryid":2,"sort":0},{"id":48,"name":"杜康","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/a3a07aaa771ea1416805969795476385.jpg","categoryid":2,"sort":0},{"id":54,"name":"河套","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/29fcd0ac8f596debca381fab5dd4c93d.jpg","categoryid":2,"sort":0},{"id":55,"name":"金六福","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/e128975921152a928f7d55a4c72f4f86.jpg","categoryid":2,"sort":0}]}
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
         * total : 543
         * info : [{"id":32,"name":"五粮液","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/a535947f282650e26d5d123ac99e9c1f.jpg","categoryid":2,"sort":0},{"id":33,"name":"茅台","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/8d1dd13918841614840ed05eb7adfdb5.jpg","categoryid":2,"sort":0},{"id":34,"name":"泸州老窖","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/cbfcdf0c0e5850686865667baa7c2687.jpg","categoryid":2,"sort":0},{"id":35,"name":"剑南春","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/4075c7520cb2add52344d95dd8a18ba9.jpg","categoryid":2,"sort":0},{"id":37,"name":"汾酒","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/5f6f6bf14bc45179c7dbfc8a2f366440.jpg","categoryid":2,"sort":0},{"id":39,"name":"郎酒","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/4f0a34d87e71a6ab95f273f1667308b8.jpg","categoryid":2,"sort":0},{"id":40,"name":"牛栏山","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/d1404fcdd6d663d0b3968c99ecf58768.jpg","categoryid":2,"sort":0},{"id":48,"name":"杜康","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/a3a07aaa771ea1416805969795476385.jpg","categoryid":2,"sort":0},{"id":54,"name":"河套","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/29fcd0ac8f596debca381fab5dd4c93d.jpg","categoryid":2,"sort":0},{"id":55,"name":"金六福","logoUrl":"http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/e128975921152a928f7d55a4c72f4f86.jpg","categoryid":2,"sort":0}]
         */

        private int total;
        private List<InfoBean> info;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * id : 32
             * name : 五粮液
             * logoUrl : http://jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/a535947f282650e26d5d123ac99e9c1f.jpg
             * categoryid : 2
             * sort : 0
             */

            private int id;
            private String name;
            private String logoUrl;
            private int categoryid;
            private int sort;

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

            public String getLogoUrl() {
                return logoUrl;
            }

            public void setLogoUrl(String logoUrl) {
                this.logoUrl = logoUrl;
            }

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
