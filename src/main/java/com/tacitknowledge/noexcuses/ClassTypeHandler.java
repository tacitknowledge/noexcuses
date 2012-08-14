package com.tacitknowledge.noexcuses;

/**
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public interface ClassTypeHandler
{
    /**
     * @param <T> 
     * @param type
     * @return
     */
    <T> T createInstance(Class<T> type);
}
