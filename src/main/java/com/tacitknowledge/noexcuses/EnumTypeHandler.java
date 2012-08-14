package com.tacitknowledge.noexcuses;

import org.apache.commons.lang.ArrayUtils;

/**
 * Handles creation of the {@link Enum} types.
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public class EnumTypeHandler implements ClassTypeHandler
{
    @Override
    @SuppressWarnings("unchecked")
    public <T> T createInstance(Class<T> type)
    {
        T instance = null;
        if (type.isEnum())
        {
            Object[] enums = type.getEnumConstants();
            if (ArrayUtils.isNotEmpty(enums))
            {
                instance = (T) enums[0];
            }
        }
        return instance;
    }
}
