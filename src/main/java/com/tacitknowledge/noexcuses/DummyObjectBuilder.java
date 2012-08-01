package com.tacitknowledge.noexcuses;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Create this object with a set of instances to be mapped to classes.  A base set should be the primitive types (e.g.
 * int.class), but users of this object may want more interesting class mapping, such as when dealing with interfaces.
 * <p/>
 * Additionally, there is a separate handler chain of class type adapters that will create instances based on other
 * "interesting" Class attributes (for instance, if a class is an enumeration).
 * <p/>
 *  Originally created: Nov 1, 2006
 */
public class DummyObjectBuilder extends AbstractParamBuilder implements ParamBuilder {
    private Map<Class<?>, Object> instanceMappings;
    private ClassTypeHandlerChain chain;

    public DummyObjectBuilder(Map<Class<?>, Object> instanceMappings) {
        this.instanceMappings = instanceMappings;
    }

    public DummyObjectBuilder(Map<Class<?>, Object> instanceMappings, ClassTypeHandlerChain chain) {
        this.instanceMappings = instanceMappings;
        this.chain = chain;
    }

    public Object createObject(Class type) {
        Constructor[] constructors = type.getConstructors();
        Object instance = null;
        if (instanceMappings.get(type) != null) {
            instance = instanceMappings.get(type);
        } else {
            if (constructors != null) {
                if (constructors.length > 0) {
                    //just grab the first constructor, since we don't know any better
                    instance = createInstance(constructors[0]);
                } else {
                    if (chain != null) {
                        instance = chain.processChain(type);
                    }
                }
            }
        }
        return instance;
    }

    public <T> T createInstance(Constructor<T> constructor) {
        T instance = null;
        try {
            instance = constructor.newInstance(createParams(constructor.getParameterTypes()));
        } catch (Exception e) {
            System.out.println("Error creating dummy param:" + e.getMessage());
        }
        return instance;
    }

}
