package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.CommunityEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IRecommendFragmentView {
    /**
     * 设置推荐数据
     * @param communityList 动态信息
     */
    void setRecommendInfo(List<CommunityEntity> communityList);

    /**
     * 给动态信息点赞
     * @param position
     */
    void thumbDynamic(String msg,int position);

    /**
     * 关注达人成功
     * @param position
     */
    void concernExpert(String msg,int position);

}
