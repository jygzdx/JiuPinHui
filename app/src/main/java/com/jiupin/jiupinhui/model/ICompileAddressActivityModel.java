package com.jiupin.jiupinhui.model;

/**
 * 作者：czb on 2017/6/26 14:26
 */
public interface ICompileAddressActivityModel {

    /**
     * 修改或新增用户地址
     * @param token 用户token
     * @param area_id 三级区域id（必须是第三级的区域id）
     * @param zip  邮箱
     * @param trueName 收货人姓名
     * @param area_info 详细地址
     * @param id 地址ID（如果不填代表新增地址，如果填写代表修改已有地址信息）
     * @param mobile 手机号
     * @param area_main 三级地区拼起来的字符串，每级用空格隔开
     * @param isDefault 是否默认地址（true：是，false：不是）
     * @param callback  回调
     */
    void saveAddress(String token,String area_id,String zip,String trueName,
                     String area_info,String id,String mobile,String area_main,boolean isDefault,
                    IModel.CallBack callback
    );

    /**
     * 删除地址
     * @param token 用户token
     *              @param id
     */
    void deleteAddress(int id, String token, IModel.CallBack callBack);
}
