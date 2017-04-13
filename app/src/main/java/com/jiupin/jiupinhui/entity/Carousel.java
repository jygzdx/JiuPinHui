package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Carousel {

    /**
     * status : 200
     * msg : OK
     * data : {"info":[{"id":1,"imageUrl":"https://image.9pin.com/banner2x.png","type":0,"imageTitle":"葡萄酒","imageAlt":"葡萄酒","title":"葡萄酒","descText":"好喝","sort":0,"isDel":0},{"id":2,"imageUrl":"https://image.9pin.com/banner2x.png","type":0,"imageTitle":"葡萄酒","imageAlt":"葡萄酒","title":"葡萄酒","descText":"好喝好喝","sort":1,"isDel":0},{"id":3,"imageUrl":"https://image.9pin.com/banner2x.png","type":0,"imageTitle":"葡萄酒","imageAlt":"葡萄酒","title":"葡萄酒","descText":"葡萄酒","sort":2,"isDel":0}]}
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
        private List<InfoBean> info;

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * id : 1
             * imageUrl : https://image.9pin.com/banner2x.png
             * type : 0
             * imageTitle : 葡萄酒
             * imageAlt : 葡萄酒
             * title : 葡萄酒
             * descText : 好喝
             * sort : 0
             * isDel : 0
             */

            private int id;
            private String imageUrl;
            private int type;
            private String imageTitle;
            private String imageAlt;
            private String title;
            private String descText;
            private int sort;
            private int isDel;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getImageTitle() {
                return imageTitle;
            }

            public void setImageTitle(String imageTitle) {
                this.imageTitle = imageTitle;
            }

            public String getImageAlt() {
                return imageAlt;
            }

            public void setImageAlt(String imageAlt) {
                this.imageAlt = imageAlt;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescText() {
                return descText;
            }

            public void setDescText(String descText) {
                this.descText = descText;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }
        }
    }
}
