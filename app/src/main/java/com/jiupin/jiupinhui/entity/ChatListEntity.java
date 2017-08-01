package com.jiupin.jiupinhui.entity;

import java.util.List;

/**
 * 作者：czb on 2017/8/1 11:28
 */

public class ChatListEntity {


    /**
     * list : [{"id":250,"content_side":1,"content":"hahhahhhhh","create_time":1500378036000,"parent_id":47},{"id":251,"content_side":1,"content":"再次问","create_time":1500378106000,"parent_id":47}]
     * hint : 客服稍后处理您的问题
     */

    private String hint;
    private List<ChatEntity> list;

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<ChatEntity> getList() {
        return list;
    }

    public void setList(List<ChatEntity> list) {
        this.list = list;
    }
}
