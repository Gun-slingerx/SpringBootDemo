package org.example.msg.context;

public enum BaseUser {

    LR("lr","lr");

    final String code;
    final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private BaseUser(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getValue() {
        return code + "";
    }
}
