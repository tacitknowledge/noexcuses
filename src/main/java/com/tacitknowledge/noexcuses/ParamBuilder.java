package com.tacitknowledge.noexcuses;

/**
 * Builds object instances to be used as parameters in method and constructor invocations.
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public interface ParamBuilder
{

    /**
     * Creates object instances, given class types. This method is useful when calling methods or
     * constructors via reflection, because method invocation requires array of objects to pass as
     * parameters if any.
     * 
     * @param paramTypes
     *            array of types to be instantiated
     * @return corresponding array of object instances
     */
    Object[] createParams(Class<?>[] paramTypes);
}
