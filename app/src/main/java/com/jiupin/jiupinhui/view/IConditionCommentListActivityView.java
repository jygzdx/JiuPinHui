package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.ConditionCommentEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface IConditionCommentListActivityView {
    /**
     * 动态评价列表信息
     *
     * @param commentList 评价信息
     */
   void setCommentList(List<ConditionCommentEntity> commentList);
}
