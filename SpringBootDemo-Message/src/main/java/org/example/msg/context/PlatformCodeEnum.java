package org.example.msg.context;

public enum PlatformCodeEnum {

    COMMONPLAT(1,"公共平台");

    final int platCode;

    final String platName;

    public int getPlatCode() {
        return platCode;
    }

    public String getPlatName() {
        return platName;
    }

    PlatformCodeEnum(int platCode, String platName) {
        this.platCode = platCode;
        this.platName = platName;
    }
}
