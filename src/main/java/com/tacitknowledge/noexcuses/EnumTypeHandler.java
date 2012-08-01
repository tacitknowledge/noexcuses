package com.tacitknowledge.noexcuses;

/**
 *  Originally created: Nov 1, 2006
 */
public class EnumTypeHandler implements ClassTypeHandler {
    public Object createInstance(Class type) {
        Object instance = null;
        if (type.isEnum()) {
            Object[] enums = type.getEnumConstants();
            if (enums != null && enums.length > 0) {
                instance = enums[0];
            }
        }
        return instance;
    }
}
