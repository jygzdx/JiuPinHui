package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/8/22 14:15
 * 推荐列表实体
 */

public class AttListEntity {

    /**
     * id : 32815
     * nickName : dsa
     * imageUrl : http://jiupin-pic.oss-cn-zhangjiakou.aliyuncs.com/appshop/user_logo_image/51fb13c4-a969-4199-9fc3-1a796b2ea3ab.jpg
     * fans_num : 2
     * intro : “”
     * concern_status : true
     */

    private int id;
    private String nickName;
    private String imageUrl;
    private int fans_num;
    private String intro;
    private boolean concern_status;

    @Override
    public String toString() {
        return "AttListEntity{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", fans_num=" + fans_num +
                ", intro='" + intro + '\'' +
                ", concern_status=" + concern_status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getFans_num() {
        return fans_num;
    }

    public void setFans_num(int fans_num) {
        this.fans_num = fans_num;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public boolean isConcern_status() {
        return concern_status;
    }

    public void setConcern_status(boolean concern_status) {
        this.concern_status = concern_status;
    }
}
