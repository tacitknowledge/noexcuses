package com.tacitknowledge.noexcuses;

/**
 *  Originally created: Nov 1, 2006
 */
public interface ClassTypeHandler {
    <T> T createInstance(Class<T> type);
}
