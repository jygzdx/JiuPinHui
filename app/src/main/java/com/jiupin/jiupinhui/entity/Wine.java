package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 * 美酒实体类
 */

public class Wine {

    /**
     * status : 200
     * msg : OK
     * data : {"total":846,"info":[{"id":15,"name":"德国玉泉葡萄酒500ml","categoryid":1,"typeid":41,"brandid":129,"originid":0,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":145,"jiuxianid":14,"jiuxianjson":"","sort":15,"status":0,"renlingnum":3,"createtime":1453459826000,"details":null},{"id":16,"name":"【清仓】葡萄牙金碧宫干红葡萄酒750ml","categoryid":1,"typeid":38,"brandid":130,"originid":11,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":138,"jiuxianid":15,"jiuxianjson":"","sort":16,"status":0,"renlingnum":3,"createtime":1453459826000,"details":null},{"id":17,"name":"法国德尔菲娜干红葡萄酒750ml","categoryid":1,"typeid":38,"brandid":26,"originid":1,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":128,"jiuxianid":16,"jiuxianjson":"","sort":17,"status":0,"renlingnum":1,"createtime":1453459826000,"details":null},{"id":18,"name":"法国宝娜干红葡萄酒750ml","categoryid":1,"typeid":38,"brandid":131,"originid":1,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":99,"jiuxianid":17,"jiuxianjson":"","sort":18,"status":0,"renlingnum":1,"createtime":1453459826000,"details":null},{"id":20,"name":"【清仓】法国酒星干红葡萄酒","categoryid":1,"typeid":1,"brandid":132,"originid":0,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":105,"jiuxianid":19,"jiuxianjson":"","sort":20,"status":0,"renlingnum":1,"createtime":1453459826000,"details":null},{"id":24,"name":"（清仓）国色天香甜红葡萄酒","categoryid":1,"typeid":0,"brandid":119,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":40,"jiuxianid":23,"jiuxianjson":"","sort":24,"status":0,"renlingnum":0,"createtime":1453459826000,"details":null},{"id":26,"name":"（清仓）中国长城八度干红葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":35,"jiuxianid":25,"jiuxianjson":"","sort":26,"status":0,"renlingnum":1,"createtime":1453459826000,"details":null},{"id":29,"name":"（清仓）中国长城桑干雷司令干白葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":35,"jiuxianid":28,"jiuxianjson":"","sort":29,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":31,"name":"中国长城桑干酒庄赤霞珠干红葡萄酒750ml","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":35,"jiuxianid":30,"jiuxianjson":"","sort":31,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":32,"name":"中国长城桑干酒庄珍藏级干红葡萄酒","categoryid":1,"typeid":1,"brandid":10,"originid":60,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":41,"jiuxianid":31,"jiuxianjson":"","sort":32,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":33,"name":"中国长城桑干酒庄西拉干红葡萄酒","categoryid":1,"typeid":1,"brandid":10,"originid":60,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":48,"jiuxianid":32,"jiuxianjson":"","sort":33,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":34,"name":"（清仓）中国长城霞多丽干白葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":21,"jiuxianid":33,"jiuxianjson":"","sort":34,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":36,"name":"（清仓）中国长城海岸葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":34,"jiuxianid":35,"jiuxianjson":"","sort":36,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":37,"name":"（清仓）中国长城五星干红葡萄酒木盒装","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":27,"jiuxianid":36,"jiuxianjson":"","sort":37,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":39,"name":"（清仓）中国长城华夏精选解百纳干红葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":33,"jiuxianid":38,"jiuxianjson":"","sort":39,"status":0,"renlingnum":1,"createtime":1453459827000,"details":null},{"id":41,"name":"（清仓）中国长城华夏葡园小产区s区7年干红葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":28,"jiuxianid":40,"jiuxianjson":"","sort":41,"status":0,"renlingnum":1,"createtime":1453459827000,"details":null},{"id":42,"name":"（清仓）中国长城华夏葡园小产区S区5年干红葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":24,"jiuxianid":41,"jiuxianjson":"","sort":42,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":44,"name":"中国长城华夏九二干红葡萄酒木盒酒750ml","categoryid":1,"typeid":38,"brandid":10,"originid":0,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":34,"jiuxianid":43,"jiuxianjson":"","sort":44,"status":0,"renlingnum":1,"createtime":1453459827000,"details":null},{"id":45,"name":"（清仓）中国华夏长城葡园 精英之选木盒酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":22,"jiuxianid":44,"jiuxianjson":"","sort":45,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":46,"name":"（清仓）中国长城华夏葡园小产区A区长城赤霞珠干红木盒装","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":22,"jiuxianid":45,"jiuxianjson":"","sort":46,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null}]}
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
         * total : 846
         * info : [{"id":15,"name":"德国玉泉葡萄酒500ml","categoryid":1,"typeid":41,"brandid":129,"originid":0,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":145,"jiuxianid":14,"jiuxianjson":"","sort":15,"status":0,"renlingnum":3,"createtime":1453459826000,"details":null},{"id":16,"name":"【清仓】葡萄牙金碧宫干红葡萄酒750ml","categoryid":1,"typeid":38,"brandid":130,"originid":11,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":138,"jiuxianid":15,"jiuxianjson":"","sort":16,"status":0,"renlingnum":3,"createtime":1453459826000,"details":null},{"id":17,"name":"法国德尔菲娜干红葡萄酒750ml","categoryid":1,"typeid":38,"brandid":26,"originid":1,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":128,"jiuxianid":16,"jiuxianjson":"","sort":17,"status":0,"renlingnum":1,"createtime":1453459826000,"details":null},{"id":18,"name":"法国宝娜干红葡萄酒750ml","categoryid":1,"typeid":38,"brandid":131,"originid":1,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":99,"jiuxianid":17,"jiuxianjson":"","sort":18,"status":0,"renlingnum":1,"createtime":1453459826000,"details":null},{"id":20,"name":"【清仓】法国酒星干红葡萄酒","categoryid":1,"typeid":1,"brandid":132,"originid":0,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":105,"jiuxianid":19,"jiuxianjson":"","sort":20,"status":0,"renlingnum":1,"createtime":1453459826000,"details":null},{"id":24,"name":"（清仓）国色天香甜红葡萄酒","categoryid":1,"typeid":0,"brandid":119,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":40,"jiuxianid":23,"jiuxianjson":"","sort":24,"status":0,"renlingnum":0,"createtime":1453459826000,"details":null},{"id":26,"name":"（清仓）中国长城八度干红葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":35,"jiuxianid":25,"jiuxianjson":"","sort":26,"status":0,"renlingnum":1,"createtime":1453459826000,"details":null},{"id":29,"name":"（清仓）中国长城桑干雷司令干白葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":35,"jiuxianid":28,"jiuxianjson":"","sort":29,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":31,"name":"中国长城桑干酒庄赤霞珠干红葡萄酒750ml","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":35,"jiuxianid":30,"jiuxianjson":"","sort":31,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":32,"name":"中国长城桑干酒庄珍藏级干红葡萄酒","categoryid":1,"typeid":1,"brandid":10,"originid":60,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":41,"jiuxianid":31,"jiuxianjson":"","sort":32,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":33,"name":"中国长城桑干酒庄西拉干红葡萄酒","categoryid":1,"typeid":1,"brandid":10,"originid":60,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":48,"jiuxianid":32,"jiuxianjson":"","sort":33,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":34,"name":"（清仓）中国长城霞多丽干白葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":21,"jiuxianid":33,"jiuxianjson":"","sort":34,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":36,"name":"（清仓）中国长城海岸葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":34,"jiuxianid":35,"jiuxianjson":"","sort":36,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":37,"name":"（清仓）中国长城五星干红葡萄酒木盒装","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":27,"jiuxianid":36,"jiuxianjson":"","sort":37,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":39,"name":"（清仓）中国长城华夏精选解百纳干红葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":33,"jiuxianid":38,"jiuxianjson":"","sort":39,"status":0,"renlingnum":1,"createtime":1453459827000,"details":null},{"id":41,"name":"（清仓）中国长城华夏葡园小产区s区7年干红葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":28,"jiuxianid":40,"jiuxianjson":"","sort":41,"status":0,"renlingnum":1,"createtime":1453459827000,"details":null},{"id":42,"name":"（清仓）中国长城华夏葡园小产区S区5年干红葡萄酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":24,"jiuxianid":41,"jiuxianjson":"","sort":42,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":44,"name":"中国长城华夏九二干红葡萄酒木盒酒750ml","categoryid":1,"typeid":38,"brandid":10,"originid":0,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":34,"jiuxianid":43,"jiuxianjson":"","sort":44,"status":0,"renlingnum":1,"createtime":1453459827000,"details":null},{"id":45,"name":"（清仓）中国华夏长城葡园 精英之选木盒酒","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":22,"jiuxianid":44,"jiuxianjson":"","sort":45,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null},{"id":46,"name":"（清仓）中国长城华夏葡园小产区A区长城赤霞珠干红木盒装","categoryid":1,"typeid":0,"brandid":10,"originid":6,"year":"","winepercent":0,"isforeign":0,"code":"","baozhuangguige":"","baozhuangfangshi":"","putaopinzhong":"","visitnum":22,"jiuxianid":45,"jiuxianjson":"","sort":46,"status":0,"renlingnum":0,"createtime":1453459827000,"details":null}]
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
             * id : 15
             * name : 德国玉泉葡萄酒500ml
             * categoryid : 1
             * typeid : 41
             * brandid : 129
             * originid : 0
             * year :
             * winepercent : 0
             * isforeign : 0
             * code :
             * baozhuangguige :
             * baozhuangfangshi :
             * putaopinzhong :
             * visitnum : 145
             * jiuxianid : 14
             * jiuxianjson :
             * sort : 15
             * status : 0
             * renlingnum : 3
             * createtime : 1453459826000
             * details : null
             */

            private int id;
            private String name;
            private int categoryid;
            private int typeid;
            private int brandid;
            private int originid;
            private String year;
            private int winepercent;
            private int isforeign;
            private String code;
            private String baozhuangguige;
            private String baozhuangfangshi;
            private String putaopinzhong;
            private int visitnum;
            private int jiuxianid;
            private String jiuxianjson;
            private int sort;
            private int status;
            private int renlingnum;
            private long createtime;
            private Object details;

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

            public int getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(int categoryid) {
                this.categoryid = categoryid;
            }

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }

            public int getBrandid() {
                return brandid;
            }

            public void setBrandid(int brandid) {
                this.brandid = brandid;
            }

            public int getOriginid() {
                return originid;
            }

            public void setOriginid(int originid) {
                this.originid = originid;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public int getWinepercent() {
                return winepercent;
            }

            public void setWinepercent(int winepercent) {
                this.winepercent = winepercent;
            }

            public int getIsforeign() {
                return isforeign;
            }

            public void setIsforeign(int isforeign) {
                this.isforeign = isforeign;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getBaozhuangguige() {
                return baozhuangguige;
            }

            public void setBaozhuangguige(String baozhuangguige) {
                this.baozhuangguige = baozhuangguige;
            }

            public String getBaozhuangfangshi() {
                return baozhuangfangshi;
            }

            public void setBaozhuangfangshi(String baozhuangfangshi) {
                this.baozhuangfangshi = baozhuangfangshi;
            }

            public String getPutaopinzhong() {
                return putaopinzhong;
            }

            public void setPutaopinzhong(String putaopinzhong) {
                this.putaopinzhong = putaopinzhong;
            }

            public int getVisitnum() {
                return visitnum;
            }

            public void setVisitnum(int visitnum) {
                this.visitnum = visitnum;
            }

            public int getJiuxianid() {
                return jiuxianid;
            }

            public void setJiuxianid(int jiuxianid) {
                this.jiuxianid = jiuxianid;
            }

            public String getJiuxianjson() {
                return jiuxianjson;
            }

            public void setJiuxianjson(String jiuxianjson) {
                this.jiuxianjson = jiuxianjson;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getRenlingnum() {
                return renlingnum;
            }

            public void setRenlingnum(int renlingnum) {
                this.renlingnum = renlingnum;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public Object getDetails() {
                return details;
            }

            public void setDetails(Object details) {
                this.details = details;
            }
        }
    }
}
