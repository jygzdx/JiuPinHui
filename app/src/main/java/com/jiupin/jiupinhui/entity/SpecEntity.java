package com.jiupin.jiupinhui.entity;

import java.io.Serializable;

/**
 * 作者：czb on 2017/8/31 15:43
 */

public class SpecEntity implements Serializable{
    /**
     * spec_info : 月度会员（3次）
     * spec_id : 32775
     */

    private String spec_info;
    private String spec_id;

    public String getSpec_info() {
        return spec_info;
    }

    public void setSpec_info(String spec_info) {
        this.spec_info = spec_info;
    }

    public String getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(String spec_id) {
        this.spec_id = spec_id;
    }
}
