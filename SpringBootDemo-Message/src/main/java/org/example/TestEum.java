package org.example;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TestEum {

    mappping_1 ("A","B"),

    mappping_2 ("A","B"),

    mappping_3 ("A","B");

    private String fieldOld;

    private String fieldNew;

    TestEum(String fieldOld, String fieldNew) {
        this.fieldOld = fieldOld;
        this.fieldNew = fieldNew;
    }

    public String getFieldOld() {
        return fieldOld;
    }

    public String getFieldNew() {
        return fieldNew;
    }

    /**
     * 返回list
     * @return
     */
    public static List toList() {
        return null;
    }

}
