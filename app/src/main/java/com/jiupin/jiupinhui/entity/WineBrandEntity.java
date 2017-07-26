package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/7/26 10:23
 */

public class WineBrandEntity {


    /**
     * id : 5
     * name : 古贝春
     * cover_img : {"large_img":"http: //jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/02edf5c6a5db58e3479f0bdbe2804c35.jpg","thumb_img":"http: //jiupin-static.img-cn-hangzhou.aliyuncs.com/brand/cover_img/02edf5c6a5db58e3479f0bdbe2804c35.jpg"}
     */

    private int id;
    private String name;
    private String cover_img;
    private String large_img;
    private String thumb_img;

    public String getLarge_img() {
        return large_img;
    }

    public void setLarge_img(String large_img) {
        this.large_img = large_img;
    }

    public String getThumb_img() {
        return thumb_img;
    }

    public void setThumb_img(String thumb_img) {
        this.thumb_img = thumb_img;
    }

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

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }
}
