package com.tacitknowledge.noexcuses;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 *  Originally created: Oct 31, 2006
 *  
 *  @author Matthew Short (mshort@tacitknowledge.com)
 */
public class TestManager
{
    /**
     * Map holding type->value relationships. Used for determination
     * of the instance to be used for the given type. 
     */
    private static Map<Class<?>, Object> myInstances;

    /**
     * Initialization of instance map with some predefined type->value references
     */
    static
    {
        myInstances = new HashMap<Class<?>, Object>();
        myInstances.put(long.class, 1);
        myInstances.put(int.class, 1);
        myInstances.put(String.class, "1");
        myInstances.put(float.class, 1);
        myInstances.put(double.class, 1);
        myInstances.put(short.class, 1);
        myInstances.put(byte.class, 1);
        myInstances.put(boolean.class, true);
    }

    /**
     * The instance map will return an object of a particular instance once one is found
     * in a parameter.  For instance (NPI): if your object has a constructor that uses an
     * interface, you can supply in the instance map an implementation of that interface,
     * with the interface.class as key. Then when that interface is found the appropriate
     * object is created for the dummy.
     * 
     * @param <T> the type of class element to test constructor method on
     * @param toTest the subject of construction testing, i.e. class to undergo
     *      the process of constructor testing
     * @param instanceMap map with type -> value references
     * @return collection of constructed objects
     */
    public static <T> Collection<T> testConstruction(Class<T> toTest, Map<Class<?>, Object> instanceMap)
    {
        myInstances.putAll(instanceMap);
        return testConstruction(toTest);
    }

    /**
     * The internal instance map will return an object of a particular instance once one is found
     * in a parameter. For instance (NPI): if your object has a constructor that uses an
     * interface, you can supply in the instance map an implementation of that interface,
     * with the interface.class as key. Then when that interface is found the appropriate
     * object is created for the dummy.
     * 
     * This method, in contrast to the {@link TestManager#testConstruction(Class, Map)}, uses
     * already existing <code>instanceMap</code> entity.
     * 
     * @param <T> the type of class element to test constructor method on
     * @param toTest the subject of construction testing, i.e. class to undergo
     *      the process of constructor testing
     * @return collection of constructed objects
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> testConstruction(Class<T> toTest)
    {
        DummyObjectBuilder dummyObjectBuilder = new DummyObjectBuilder(myInstances,
                ClassTypeHandlerChain.defaultTypeChain());
        Collection<T> instances = new ArrayList<T>();
        Constructor<T>[] constructors = (Constructor<T>[]) toTest.getConstructors();

        for (Constructor<T> constructor : constructors)
        {
            instances.add(dummyObjectBuilder.createInstance(constructor));
        }

        return instances;
    }

    /**
     * 
     * @param <T> the type of object element whose methods to be tested
     * @param toTest object to undergo methods testing
     * @return {@link MethodTester} instance 
     */
    public static <T> MethodTester testMethods(T toTest)
    {
        MethodTester tester = new MethodTester(StringUtils.EMPTY);
        try
        {
            tester.performSilentTest(toTest);
        }
        catch (Exception e)
        {
            System.out.println("Error performing method tests:" + e.getMessage());
        }
        return tester;
    }

    /**
     * 
     * @param <T> the type of object element whose methods to be tested
     * @param toTest object to undergo methods testing
     * @param instanceMap map with type -> value references
     * @return 
     */
    public static <T> MethodTester testMethods(T toTest, Map<Class<?>, Object> instanceMap)
    {
        myInstances.putAll(instanceMap);
        return testMethods(toTest);
    }
}
