package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.AppraiseEntity;

import java.util.List;

/**
 * 作者：czb on 2017/6/28 11:38
 */

public interface ICommentActivityView {
    /**
     * 评价列表信息
     *
     * @param appraiseList 评价信息
     */
   void setUserAppraise(List<AppraiseEntity> appraiseList);
}
