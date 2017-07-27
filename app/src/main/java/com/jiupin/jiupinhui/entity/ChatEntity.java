package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/7/27 10:35
 */

public class ChatEntity {


    /**
     * id : 250
     * content_side : 1
     * content : hahhahhhhh
     * create_time : 1500378036000
     * parent_id : 47
     */

    private int id;
    private int content_side;
    private String content;
    private long create_time;
    private int parent_id;
    private String hint;

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContent_side() {
        return content_side;
    }

    public void setContent_side(int content_side) {
        this.content_side = content_side;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
