package com.tacitknowledge.noexcuses;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

/**
 *  Originally created: Oct 31, 2006
 */
public class TestManager {

    private static Map<Class<?>, Object> myInstances;

    static {
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
     * the instance map will return an object of a particular instance once one is found
     * in a parameter.  For instance (NPI): if your object has a constructor that uses an
     * interface, you can supply in the instance map an implementation of that interface,
     * with the interface.class as key.  Then when that interface is found the appropriate
     * object is created for the dummy.
     *
     * @param toTest
     * @param instanceMap
     * @return collection of constructed objects
     */
    public static <T> Collection<T> testConstruction(Class<T> toTest, Map<Class<?>, Object> instanceMap) {
        myInstances.putAll(instanceMap);
        return testConstruction(toTest);
    }

    /**
     * @param toTest
     */
    @SuppressWarnings("unchecked")
	public static <T> Collection<T> testConstruction(Class<T> toTest) {
        DummyObjectBuilder dummyObjectBuilder = new DummyObjectBuilder(myInstances,
                                                                       ClassTypeHandlerChain.defaultTypeChain());
        Collection<T> instances = new ArrayList<T>();
        Constructor<T>[] constructors = (Constructor<T>[]) toTest.getConstructors();
        for (Constructor<T> constructor : constructors) {
            instances.add(dummyObjectBuilder.createInstance(constructor));
        }
        return instances;
    }


    public static MethodTester testMethods(Object toTest) {
        MethodTester tester = new MethodTester("");
        try {
            tester.performSilentTest(toTest);
        } catch (Exception e) {
            System.out.println("Error performing method tests:" + e.getMessage());
        }
        return tester;
    }

    public static <T> MethodTester testMethods(T toTest, Map<Class<?>, Object> instanceMap){
        myInstances.putAll(instanceMap);
        return testMethods(toTest);
    }
}
