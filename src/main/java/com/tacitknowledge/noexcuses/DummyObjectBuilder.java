package com.tacitknowledge.noexcuses;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Create this object with a set of instances to be mapped to classes. A base set should be the
 * primitive types (e.g. int.class), but users of this object may want more interesting class
 * mapping, such as when dealing with interfaces.
 * <p/>
 * Additionally, there is a separate handler chain of class type adapters that will create instances
 * based on other "interesting" Class attributes (for instance, if a class is an enumeration).
 * <p/>
 * 
 * @author Matthew Short (mshort@tacitknowledge.com)
 */
public class DummyObjectBuilder extends AbstractParamBuilder implements ParamBuilder
{
    /** {@link Class} to {@link Object} instance mapping */
    private Map<Class<?>, Object> instanceMappings;

    /** Class types handlers chain */
    private ClassTypeHandlerChain chain;

    /**
     * Constructs {@link DummyObjectBuilder}
     * 
     * @param instanceMappings
     *            predefined {@link Class} to {@link Object} instance map
     */
    public DummyObjectBuilder(Map<Class<?>, Object> instanceMappings)
    {
        this.instanceMappings = instanceMappings;
    }

    /**
     * Constructs {@link DummyObjectBuilder}
     * 
     * @param instanceMappings
     *            predefined {@link Class} to {@link Object} instance map
     * @param chain
     *            handler chain of class type adapters that will create instances based on
     *            "interesting" {@link Class} attributes
     */
    public DummyObjectBuilder(Map<Class<?>, Object> instanceMappings, ClassTypeHandlerChain chain)
    {
        this.instanceMappings = instanceMappings;
        this.chain = chain;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T createObject(Class<T> type)
    {
        Constructor<T>[] constructors = (Constructor<T>[]) type.getConstructors();
        T instance = null;
        if (instanceMappings.get(type) != null)
        {
            instance = (T) instanceMappings.get(type);
        }
        else
        {
            if (constructors != null)
            {
                if (constructors.length > 0)
                {
                    // just grab the first constructor, since we don't know any better
                    instance = createInstance(constructors[0]);
                }
                else
                {
                    if (chain != null)
                    {
                        instance = chain.processChain(type);
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Creates instances given the constructor.
     * @param <T> Class type
     * @param constructor {@link Constructor} to be called
     * @return concrete {@link Object} instance
     */
    public <T> T createInstance(Constructor<T> constructor)
    {
        T instance = null;
        try
        {
            instance = constructor.newInstance(createParams(constructor.getParameterTypes()));
        }
        catch (Exception e)
        {
            System.out.println("Error creating dummy param:" + e.getMessage());
        }
        return instance;
    }

}
