package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/7/11 14:32
 */

public class AreaEntity {

    /**
     * id : 4522003
     * addTime :
     * deleteStatus : false
     * areaName : 天津市
     * parent :
     * sequence : 0
     * level : 0
     * common : false
     */

    private int id;
    private String addTime;
    private boolean deleteStatus;
    private String areaName;
    private String parent;
    private int sequence;
    private int level;
    private boolean common;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    @Override
    public String toString() {
        return "AreaEntity{" +
                "id=" + id +
                ", addTime='" + addTime + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", areaName='" + areaName + '\'' +
                ", parent='" + parent + '\'' +
                ", sequence=" + sequence +
                ", level=" + level +
                ", common=" + common +
                '}';
    }
}
