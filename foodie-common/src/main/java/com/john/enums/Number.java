package com.john.enums;

/**
 * @author johnconstantine
 */

public enum Number {
    /**
     * 数字6
     */
    six(6, "6");

    public final Integer type;
    public final String value;

    Number(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
