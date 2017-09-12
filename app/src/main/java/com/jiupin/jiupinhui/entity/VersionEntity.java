package com.jiupin.jiupinhui.entity;

/**
 * 作者：czb on 2017/9/7 11:12
 */

public class VersionEntity {

    /**
     * versionInNumber : 100
     * maxUpdateVersion : 100
     * versionInString : 1.0.0
     */

    private int versionInNumber;
    private int maxUpdateVersion;
    private String versionInString;

    public int getVersionInNumber() {
        return versionInNumber;
    }

    public void setVersionInNumber(int versionInNumber) {
        this.versionInNumber = versionInNumber;
    }

    public int getMaxUpdateVersion() {
        return maxUpdateVersion;
    }

    public void setMaxUpdateVersion(int maxUpdateVersion) {
        this.maxUpdateVersion = maxUpdateVersion;
    }

    public String getVersionInString() {
        return versionInString;
    }

    public void setVersionInString(String versionInString) {
        this.versionInString = versionInString;
    }
}
