package com.jiupin.jiupinhui.entity;

import java.io.Serializable;

/**
 * 作者：czb on 2017/7/10 14:23
 */

public class AddressEntity implements Serializable{


    /**
     * id : 32778
     * trueName : 啦啦啦
     * area :
     * area_main : 广东省 广州市 海珠区
     * area_info : 穷山恶水123
     * zip :
     * telephone :
     * mobile : 13560301401
     * is_default : 0
     * user :
     * area_id : 4522006
     */

    private int id;
    private String trueName;
    private String area;
    private String area_main;
    private String area_info;
    private String zip;
    private String telephone;
    private String mobile;
    private int is_default;
    private String user;
    private int area_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", trueName='" + trueName + '\'' +
                ", area='" + area + '\'' +
                ", area_main='" + area_main + '\'' +
                ", area_info='" + area_info + '\'' +
                ", zip='" + zip + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", is_default=" + is_default +
                ", user='" + user + '\'' +
                ", area_id=" + area_id +
                '}';
    }
}
