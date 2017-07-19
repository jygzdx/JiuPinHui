package com.jiupin.jiupinhui.model;

import java.io.File;
import java.util.List;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface ISendCommentActivityModel {

    /**
     * 提交用户评价信息
     * @param token token
     * @param orderId 订单id
     * @param eval_info 评价信息
     * @param desc_evaluate 描述相符
     * @param service_evaluate 服务态度
     * @param ship_evaluate 物流服务
     * @param rating 评论等级（1：好评，2：中评，3：差评）
     * @param files 上传的图片文件
     *
     */
    void sendComment(String token, String orderId, String eval_info, String desc_evaluate,
                     String service_evaluate, String ship_evaluate, String rating, List<File> files, IModel.CallBack callBack);
}
