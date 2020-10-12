package org.example.msg.context;

import lombok.Data;

public enum SuccessEnum {

    SUCCESS(0, "成功"),

    FAIL(1,"失败");

    final int code;

    final String content;

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    SuccessEnum(int code, String content) {
        this.code = code;
        this.content = content;
    }
}
