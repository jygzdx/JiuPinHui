package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.CommunityEntity;
import com.jiupin.jiupinhui.entity.UserEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IAttentionFragmentView {
    /**
     * 设置关注数据
     * @param communityList 动态信息
     */
    void setAttentionInfo(List<CommunityEntity> communityList);

    /**
     * 给动态信息点赞
     * @param position
     */
    void thumbDynamic(String msg, int position);

    /**
     * 关注达人成功
     * @param userId
     */
    void concernExpert(String msg, String userId);

    /**
     * 获取用户信息
     * @param userEntity
     */
    void setUserInfo(UserEntity userEntity);

}
