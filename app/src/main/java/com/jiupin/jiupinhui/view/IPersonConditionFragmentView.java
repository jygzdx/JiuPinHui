package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.CommunityEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IPersonConditionFragmentView {
    /**
     * 设置个人数据
     * @param communityList 动态信息
     */
    void setPersonConditionInfo(List<CommunityEntity> communityList);

    /**
     * 给动态信息点赞
     * @param position
     */
    void thumbDynamic(String msg, int position);

    /**
     * 删除动态
     * @param msg
     * @param position 位置
     */
    void deleteCondition(String msg,int position);

    /**
     * 动态置顶
     * @param communityList
     */
    void moveConditionToTop(List<CommunityEntity> communityList);

    /**
     * 尽自己可看
     * @param communityList
     */
    void onlymyselflook(List<CommunityEntity> communityList);
}
