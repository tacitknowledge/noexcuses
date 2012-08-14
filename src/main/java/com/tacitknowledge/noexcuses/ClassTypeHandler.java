package com.tacitknowledge.noexcuses;

/**
 * Contract that handles creation of instances of a given type
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public interface ClassTypeHandler
{
    /**
     * Creates instance of the given type
     * 
     * @param <T> type of the instance to be created
     * @param type class type of the to be created object
     * @return the corresponding object
     */
    <T> T createInstance(Class<T> type);
}
