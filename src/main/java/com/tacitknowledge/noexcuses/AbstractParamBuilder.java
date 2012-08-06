package com.tacitknowledge.noexcuses;

/**
 * A super class to build object instances, given class types. Returned object instances are used as
 * method or constructor parameters.
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public abstract class AbstractParamBuilder implements ParamBuilder
{
    @Override
    public Object[] createParams(Class<?>[] paramTypes)
    {
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < params.length; i++)
        {
            params[i] = createObject(paramTypes[i]);
        }
        return params;
    }

    /**
     * Creates an object of a given type initialized with a default value.
     * 
     * @param <T>
     *            the type of the passed object.
     * @param type
     *            the class of the object to create.
     * @return the primitive type object with a custom default value or a mock of the passed type
     */
    protected abstract <T> T createObject(Class<T> type);
}
