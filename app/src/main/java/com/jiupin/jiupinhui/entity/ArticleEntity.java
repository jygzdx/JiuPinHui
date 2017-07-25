package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/7/25 09:57
 */

public class ArticleEntity {

    /**
     * id : 29
     * imageUrl : http://wineimg.oss-cn-hangzhou.aliyuncs.com/185%2Fuploads%2F150828%2F8496-150RQ02042941.png
     * title : 美国这11个风景如画的酒庄，你值得一看！
     * createTime : 1441898969000
     * content : /app/page/goHotArticleDetail.htm?id=
     */

    private int id;
    private String imageUrl;
    private String title;
    private long createTime;
    private String content;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
