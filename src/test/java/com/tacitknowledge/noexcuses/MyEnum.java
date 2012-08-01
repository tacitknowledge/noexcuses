package com.tacitknowledge.noexcuses;

/**
 *  Originally created: Nov 1, 2006
 */
public enum MyEnum {
    Whatever("Whatever"),
    Yeah("Yeah");

    private String description;

    MyEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
