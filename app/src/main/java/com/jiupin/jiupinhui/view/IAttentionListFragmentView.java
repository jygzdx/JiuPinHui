package com.jiupin.jiupinhui.view;

import com.jiupin.jiupinhui.entity.AttListEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface IAttentionListFragmentView {
    /**
     * 设置返回的推荐数据
     * @param attListEntities 用户推荐信息
     */
    void setRecommendListInfo(List<AttListEntity> attListEntities);

    /**
     * 关注达人成功
     * @param position
     */
    void concernExpert(String msg, int position);

}
